package ru.shtamov.led.model.dto;

import java.time.LocalDateTime;

public record ErrorDto(
        String status,
        String message,
        LocalDateTime date
) {
}
