package ru.shtamov.led.model.esp;

import ru.shtamov.led.model.domain.RainbowSettings;

public record RainbowEsp(
        Integer mode,
        RainbowSettings vu_rainbow
) {
}
