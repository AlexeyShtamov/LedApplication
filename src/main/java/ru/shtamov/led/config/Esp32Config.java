package ru.shtamov.led.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "esp32")
public class Esp32Config {
    private String ipAddress = "music-leds.local";
    private int requestTimeout = 5;
}
