package ru.shtamov.led.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.shtamov.led.config.Esp32Config;
import ru.shtamov.led.exception.NoConnectionException;
import ru.shtamov.led.model.domain.FlashSettings;
import ru.shtamov.led.model.domain.RainbowSettings;
import ru.shtamov.led.model.domain.Setting;
import ru.shtamov.led.model.domain.VolumeSettings;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SettingsService {

    private final RestTemplate restTemplate;
    private final Esp32Config esp32Config;

    private final Map<Integer, Setting> settingMap = new HashMap<>();
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

        settingMap.put(0, volumeSettings);

        log.info("Volume settings are updated");
        return volumeSettings;
    }

    public RainbowSettings updateRainbowSettings(RainbowSettings rainbowSettings){
        if (!isConnected) throw new NoConnectionException();

        settingMap.put(1, rainbowSettings);

        log.info("Rainbow settings are updated");
        return rainbowSettings;
    }

    public FlashSettings updateFlashSettings(FlashSettings flashSettings){
        if (!isConnected) throw new NoConnectionException();

        settingMap.put(2, flashSettings);

        log.info("Flash settings are updated");
        return flashSettings;
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


//    public SettingsResponseDTO updateSettings(SettingsRequestDTO request) {
//        SettingsResponseDTO response = new SettingsResponseDTO();
//
//        try {
//            String url = "http://" + esp32Config.getIpAddress() + "/settings";
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//            HttpEntity<SettingsRequestDTO> entity = new HttpEntity<>(request, headers);
//
//            ResponseEntity<String> esp32Response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    entity,
//                    String.class
//            );
//
//            if (esp32Response.getStatusCode().is2xxSuccessful()) {
//                response.setStatus("success");
//                response.setMessage("Настройки успешно отправлены на ESP32");
//            } else {
//                response.setStatus("warning");
//                response.setMessage("ESP32 вернул статус: " + esp32Response.getStatusCode());
//            }
//
//        } catch (HttpClientErrorException e) {
//            response.setStatus("error");
//            response.setMessage("Ошибка HTTP при отправке на ESP32: " + e.getStatusCode());
//        } catch (ResourceAccessException e) {
//            response.setStatus("error");
//            response.setMessage("Не удалось подключиться к ESP32 по адресу " + esp32Config.getIpAddress() +
//                    ". Убедитесь, что он включен и находится в той же сети.");
//        } catch (Exception e) {
//            response.setStatus("error");
//            response.setMessage("Произошла внутренняя ошибка сервера: " + e.getMessage());
//        }
//
//        // Update the response with current settings
//        response.setMode(request.getMode());
//        response.setVuGreenRed(request.getVuGreenRed());
//        response.setVuRainbow(request.getVuRainbow());
//        response.setFlash(request.getFlash());
//
//        return response;
//    }
}