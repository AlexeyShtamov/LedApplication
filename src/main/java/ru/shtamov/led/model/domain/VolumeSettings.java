package ru.shtamov.led.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class VolumeSettings extends Setting{
    private Integer bgBrightness;
    private String bgColor;

    public VolumeSettings(Integer sensitivity, Integer brightness, Integer smoothing, Integer bgBrightness, String bgColor) {
        super(sensitivity, brightness, smoothing);
        this.bgBrightness = bgBrightness;
        this.bgColor = bgColor;
    }

}
