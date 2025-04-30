package ru.shtamov.led.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SettingsResponseDTO {
    private Integer mode;
    private BaseSettingsDTO vuGreenRed;
    private BaseSettingsDTO vuRainbow;
    private FlashSettingsDTO flash;
    private String message;
    private String status;
}
