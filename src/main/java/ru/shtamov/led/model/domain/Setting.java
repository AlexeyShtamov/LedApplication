package ru.shtamov.led.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class Setting {
    private Integer sensitivity;
    private Integer brightness;
    private Integer smoothing;
}
