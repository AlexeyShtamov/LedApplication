package ru.shtamov.led.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlashSettingsDTO {
    private Integer sensitivity;
    private Integer brightness;
    private Integer smoothing;
    private String color;
}
