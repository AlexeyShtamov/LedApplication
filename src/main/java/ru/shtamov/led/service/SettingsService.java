package ru.shtamov.led.service;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.shtamov.led.config.Esp32Config;
import ru.shtamov.led.exception.NoConnectionException;
import ru.shtamov.led.model.domain.FlashSettings;
import ru.shtamov.led.model.domain.RainbowSettings;
import ru.shtamov.led.model.domain.Setting;
import ru.shtamov.led.model.domain.VolumeSettings;
import ru.shtamov.led.model.esp.FlashEsp;
import ru.shtamov.led.model.esp.RainbowEsp;
import ru.shtamov.led.model.esp.VolumeEsp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SettingsService {

    private final RestTemplate restTemplate;
    private final Esp32Config esp32Config;

    private final Map<Integer, Setting> settingMap = new HashMap<>();
    private int currentMode = 0;
    private Boolean isConnected = false;

    public SettingsService(RestTemplate restTemplate, Esp32Config esp32Config) {
        this.restTemplate = restTemplate;
        this.esp32Config = esp32Config;

        VolumeSettings vuGreenRed = new VolumeSettings();
        vuGreenRed.setSensitivity(70);
        vuGreenRed.setBrightness(80);
        vuGreenRed.setBgBrightness(10);
        vuGreenRed.setSmoothing(30);
        vuGreenRed.setBgColor("#000000");

        RainbowSettings vuRainbow = new RainbowSettings();
        vuRainbow.setSensitivity(70);
        vuRainbow.setBrightness(80);
        vuRainbow.setBgBrightness(10);
        vuRainbow.setSmoothing(30);
        vuRainbow.setBgColor("#000000");

        FlashSettings flash = new FlashSettings();
        flash.setSensitivity(80);
        flash.setBrightness(100);
        flash.setSmoothing(10);
        flash.setColor("#FFFFFF");

        settingMap.put(0, vuGreenRed);
        settingMap.put(1, vuRainbow);
        settingMap.put(2, flash);
    }

    public VolumeSettings getVolumeSettings(){
        if (!isConnected) throw new NoConnectionException();

        VolumeSettings volumeSettings = (VolumeSettings) settingMap.get(0);

        log.info("Volume settings are gotten");
        return volumeSettings;
    }

    public RainbowSettings getRainbowSettings(){
        if (!isConnected) throw new NoConnectionException();

        RainbowSettings rainbowSettings = (RainbowSettings) settingMap.get(1);

        log.info("Rainbow settings are gotten");
        return rainbowSettings;
    }

    public FlashSettings getFlashSettings(){
        if (!isConnected) throw new NoConnectionException();

        FlashSettings flashSettings = (FlashSettings) settingMap.get(2);

        log.info("Flash settings are gotten");
        return flashSettings;
    }

    public VolumeSettings updateVolumeSettings(VolumeSettings volumeSettings){
        if (!isConnected) throw new NoConnectionException();

        updateSettings(0, volumeSettings);

        settingMap.put(0, volumeSettings);
        currentMode = 0;

        log.info("Volume settings are updated");
        return volumeSettings;
    }

    public RainbowSettings updateRainbowSettings(RainbowSettings rainbowSettings){
        if (!isConnected) throw new NoConnectionException();

        updateSettings(1, rainbowSettings);

        settingMap.put(1, rainbowSettings);
        currentMode = 1;

        log.info("Rainbow settings are updated");
        return rainbowSettings;
    }

    public FlashSettings updateFlashSettings(FlashSettings flashSettings){
        if (!isConnected) throw new NoConnectionException();

        updateSettings(2, flashSettings);

        settingMap.put(2, flashSettings);
        currentMode = 2;

        log.info("Flash settings are updated");
        return flashSettings;
    }

    public Setting getCurrentSettings(){
        Setting setting = settingMap.get(currentMode);

        log.info("Current settings is found");

        return setting;
    }

    public Integer getCurrentMode(){
        return this.currentMode;
    }

    public Boolean getConnection(){
        return isConnected;
    }

    public Boolean connect(String url){
        log.info("Bla bla bla {} bla bla", url);

        isConnected = true;

        log.info("Led is connected");
        return isConnected;
    }

    public Boolean disconnect(){
        isConnected = false;

        log.info("Led is disconnected");
        return isConnected;
    }


    private void updateSettings(int mode, Setting request) {

        try {
            String url = "http://" + esp32Config.getIpAddress() + "/settings";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<?> entity = switch (mode){
                case 0 -> new HttpEntity<>(new VolumeEsp(0, (VolumeSettings) request), headers);
                case 1 -> new HttpEntity<>(new RainbowEsp(1, (RainbowSettings) request), headers);
                case 2 -> new HttpEntity<>(new FlashEsp(2, (FlashSettings) request), headers);
                default -> throw new IllegalStateException("Unexpected value: " + mode);
            };

            ResponseEntity<String> esp32Response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            log.info("Status code from led: {}", esp32Response.getStatusCode());

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}