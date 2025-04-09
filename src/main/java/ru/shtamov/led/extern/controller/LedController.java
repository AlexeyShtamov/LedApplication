package ru.shtamov.led.extern.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shtamov.led.application.Esp32Service;

@RestController
@RequestMapping("/api/led")
public class LedController {
    private final Esp32Service esp32Service;

    public LedController(Esp32Service esp32Service) {
        this.esp32Service = esp32Service;
    }

    @PostMapping("/rainbow")
    public ResponseEntity<String> activateRainbow() {
        return esp32Service.sendCommand("rainbow");
    }

    @PostMapping("/blink")
    public ResponseEntity<String> activateBlink() {
        return esp32Service.sendCommand("blink");
    }

    @PostMapping("/volume")
    public ResponseEntity<String> setVolume(@RequestBody int volume) {
        return esp32Service.sendData("volume", volume);
    }
}
