package ru.shtamov.led.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.shtamov.led.config.Esp32Config;
import ru.shtamov.led.model.BaseSettingsDTO;
import ru.shtamov.led.model.FlashSettingsDTO;
import ru.shtamov.led.model.SettingsRequestDTO;
import ru.shtamov.led.model.SettingsResponseDTO;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class SettingsService {

    private final RestTemplate restTemplate;
    private final Esp32Config esp32Config;

    public SettingsResponseDTO updateSettings(SettingsRequestDTO request) {
        SettingsResponseDTO response = new SettingsResponseDTO();

        // Имитация задержки сети
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        response.setStatus("success");
        response.setMessage("Настройки успешно отправлены на ESP32 (заглушка)");

        // Сохраняем переданные настройки в ответе
        response.setMode(request.getMode());

        if (request.getVuGreenRed() != null) {
            response.setVuGreenRed(request.getVuGreenRed());
        } else {
            BaseSettingsDTO vuGreenRed = new BaseSettingsDTO();
            vuGreenRed.setSensitivity(70);
            vuGreenRed.setBrightness(80);
            vuGreenRed.setBgBrightness(10);
            vuGreenRed.setSmoothing(30);
            vuGreenRed.setBgColor("#000000");
            response.setVuGreenRed(vuGreenRed);
        }

        if (request.getVuRainbow() != null) {
            response.setVuRainbow(request.getVuRainbow());
        } else {
            BaseSettingsDTO vuRainbow = new BaseSettingsDTO();
            vuRainbow.setSensitivity(70);
            vuRainbow.setBrightness(80);
            vuRainbow.setBgBrightness(10);
            vuRainbow.setSmoothing(30);
            vuRainbow.setBgColor("#000000");
            response.setVuRainbow(vuRainbow);
        }

        if (request.getFlash() != null) {
            response.setFlash(request.getFlash());
        } else {
            FlashSettingsDTO flash = new FlashSettingsDTO();
            flash.setSensitivity(80);
            flash.setBrightness(100);
            flash.setSmoothing(10);
            flash.setColor("#FFFFFF");
            response.setFlash(flash);
        }

        return response;
    }

    public SettingsResponseDTO getCurrentSettings() {
        SettingsResponseDTO response = new SettingsResponseDTO();

        response.setMode(0);

        BaseSettingsDTO vuGreenRed = new BaseSettingsDTO();
        vuGreenRed.setSensitivity(70);
        vuGreenRed.setBrightness(80);
        vuGreenRed.setBgBrightness(10);
        vuGreenRed.setSmoothing(30);
        vuGreenRed.setBgColor("#000000");
        response.setVuGreenRed(vuGreenRed);

        BaseSettingsDTO vuRainbow = new BaseSettingsDTO();
        vuRainbow.setSensitivity(70);
        vuRainbow.setBrightness(80);
        vuRainbow.setBgBrightness(10);
        vuRainbow.setSmoothing(30);
        vuRainbow.setBgColor("#000000");
        response.setVuRainbow(vuRainbow);

        FlashSettingsDTO flash = new FlashSettingsDTO();
        flash.setSensitivity(80);
        flash.setBrightness(100);
        flash.setSmoothing(10);
        flash.setColor("#FFFFFF");
        response.setFlash(flash);

        response.setStatus("success");
        response.setMessage("Текущие настройки успешно получены");

        return response;
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