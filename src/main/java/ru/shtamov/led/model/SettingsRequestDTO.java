package ru.shtamov.led.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SettingsRequestDTO {
    private Integer mode;
    private BaseSettingsDTO vuGreenRed;
    private BaseSettingsDTO vuRainbow;
    private FlashSettingsDTO flash;
}
