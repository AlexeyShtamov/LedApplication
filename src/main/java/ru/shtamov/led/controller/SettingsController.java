package ru.shtamov.led.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shtamov.led.converter.SettingConverter;
import ru.shtamov.led.model.dto.*;
import ru.shtamov.led.service.SettingsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SettingsController {

    private final SettingsService settingsService;
    private final SettingConverter settingConverter;

    @GetMapping("/volume")
    public ResponseEntity<VolumeSettingsDTO> getVolumeSettings() {
        return ResponseEntity
                .ok(settingConverter.toVolumeDto(settingsService.getVolumeSettings()));
    }

    @GetMapping("/rainbow")
    public ResponseEntity<RainbowSettingsDTO> getRainbowSettings() {
        return ResponseEntity
                .ok(settingConverter.toRainbowDto(settingsService.getRainbowSettings()));
    }

    @GetMapping("/flash")
    public ResponseEntity<FlashSettingsDTO> getFlashSettings() {
        return ResponseEntity
                .ok(settingConverter.toFlashDto(settingsService.getFlashSettings()));
    }

    @PatchMapping("/volume")
    public ResponseEntity<VolumeSettingsDTO> updateVolumeSettings(@RequestBody VolumeSettingsDTO request) {
        return ResponseEntity
                .ok(settingConverter.toVolumeDto(settingsService.updateVolumeSettings(settingConverter.toVolumeDomain(request))));
    }

    @PatchMapping("/rainbow")
    public ResponseEntity<RainbowSettingsDTO> updateRainbowSettings(@RequestBody RainbowSettingsDTO request) {
        return ResponseEntity
                .ok(settingConverter.toRainbowDto(settingsService.updateRainbowSettings(settingConverter.toRainbowDomain(request))));
    }

    @PatchMapping("/flash")
    public ResponseEntity<FlashSettingsDTO> updateFlashSettings(@RequestBody FlashSettingsDTO request) {
        return ResponseEntity
                .ok(settingConverter.toFlashDto(settingsService.updateFlashSettings(settingConverter.toFlashDomain(request))));
    }

    @GetMapping("/connection")
    public ResponseEntity<ConnectionGetDto> getConnection(){
        return ResponseEntity
                .ok(new ConnectionGetDto(settingsService.getConnection()));
    }

    @PatchMapping("/connection")
    public ResponseEntity<ConnectionGetDto> connect(@RequestBody ConnectionUpdateDto dto){
        return ResponseEntity
                .ok(new ConnectionGetDto(settingsService.connect(dto.url())));
    }

    @PatchMapping("/disconnection")
    public ResponseEntity<ConnectionGetDto> disconnect(){
        return ResponseEntity
                .ok(new ConnectionGetDto(settingsService.disconnect()));
    }
}