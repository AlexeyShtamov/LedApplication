package ru.shtamov.led.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shtamov.led.model.SettingsRequestDTO;
import ru.shtamov.led.model.SettingsResponseDTO;
import ru.shtamov.led.service.SettingsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping
    public ResponseEntity<SettingsResponseDTO> getSettings() {
        SettingsResponseDTO response = settingsService.getCurrentSettings();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SettingsResponseDTO> updateSettings(@RequestBody SettingsRequestDTO request) {
        SettingsResponseDTO response = settingsService.updateSettings(request);
        return ResponseEntity.ok(response);
    }
}