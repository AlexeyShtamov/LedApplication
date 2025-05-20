package ru.shtamov.led.converter;

import org.springframework.stereotype.Component;
import ru.shtamov.led.model.domain.FlashSettings;
import ru.shtamov.led.model.domain.RainbowSettings;
import ru.shtamov.led.model.domain.VolumeSettings;
import ru.shtamov.led.model.dto.FlashSettingsDTO;
import ru.shtamov.led.model.dto.RainbowSettingsDTO;
import ru.shtamov.led.model.dto.VolumeSettingsDTO;

@Component
public class SettingConverter {

    public VolumeSettingsDTO toVolumeDto(VolumeSettings volumeSettings){
        return new VolumeSettingsDTO(
                0,
                volumeSettings.getSensitivity(),
                volumeSettings.getBrightness(),
                volumeSettings.getBgBrightness(),
                volumeSettings.getSmoothing(),
                volumeSettings.getBgColor()
        );
    }

    public RainbowSettingsDTO toRainbowDto(RainbowSettings volumeSettings){
        return new RainbowSettingsDTO(
                1,
                volumeSettings.getSensitivity(),
                volumeSettings.getBrightness(),
                volumeSettings.getBgBrightness(),
                volumeSettings.getSmoothing(),
                volumeSettings.getBgColor()
        );
    }

    public FlashSettingsDTO toFlashDto(FlashSettings volumeSettings){
        return new FlashSettingsDTO(
                2,
                volumeSettings.getSensitivity(),
                volumeSettings.getBrightness(),
                volumeSettings.getSmoothing(),
                volumeSettings.getColor()
        );
    }

    public VolumeSettings toVolumeDomain(VolumeSettingsDTO dto){
        return new VolumeSettings(
                dto.getSensitivity(),
                dto.getBrightness(),
                dto.getSmoothing(),
                dto.getBgBrightness(),
                dto.getBgColor()
        );
    }

    public RainbowSettings toRainbowDomain(RainbowSettingsDTO dto){
        return new RainbowSettings(
                dto.getSensitivity(),
                dto.getBrightness(),
                dto.getSmoothing(),
                dto.getBgBrightness(),
                dto.getBgColor()
        );
    }

    public FlashSettings toFlashDomain(FlashSettingsDTO dto){
        return new FlashSettings(
                dto.getSensitivity(),
                dto.getBrightness(),
                dto.getSmoothing(),
                dto.getColor()
        );
    }
}
