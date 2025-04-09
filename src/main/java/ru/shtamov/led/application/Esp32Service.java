package ru.shtamov.led.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Esp32Service {
    @Value("${esp32.url}")
    private String esp32Url;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> sendCommand(String command) {
        String url = esp32Url + "/" + command;
        try {
            return restTemplate.postForEntity(url, null, String.class);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка подключения к ESP32");
        }
    }

    public ResponseEntity<String> sendData(String volume, int volume1) {
        //TODO метод изменяющий громкость
        return null;
    }
}