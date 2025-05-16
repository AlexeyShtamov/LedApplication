package ru.shtamov.led.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class FlashSettingsDTO {
    private Integer sensitivity;
    private Integer brightness;
    private Integer smoothing;
    private String color;
}
