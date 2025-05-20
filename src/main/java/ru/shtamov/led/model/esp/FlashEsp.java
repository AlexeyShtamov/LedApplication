package ru.shtamov.led.model.esp;

import ru.shtamov.led.model.domain.FlashSettings;

public record FlashEsp(
        Integer mode,
        FlashSettings flash
) {
}
