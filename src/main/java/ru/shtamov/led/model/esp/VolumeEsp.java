package ru.shtamov.led.model.esp;

import ru.shtamov.led.model.domain.VolumeSettings;

public record VolumeEsp(
        Integer mode,
        VolumeSettings vu_green_red
) {
}
