package ru.shtamov.led.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class VolumeSettingsDTO {
    private Integer sensitivity;
    private Integer brightness;
    private Integer bgBrightness;
    private Integer smoothing;
    private String bgColor;
}
