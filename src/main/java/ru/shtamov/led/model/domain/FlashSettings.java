package ru.shtamov.led.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlashSettings extends Setting{
    private String color;

    public FlashSettings(Integer sensitivity, Integer brightness, Integer smoothing, String color) {
        super(sensitivity, brightness, smoothing);
        this.color = color;
    }
}
