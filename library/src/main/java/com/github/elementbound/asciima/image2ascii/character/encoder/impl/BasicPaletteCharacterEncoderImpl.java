package com.github.elementbound.asciima.image2ascii.character.encoder.impl;

import java.util.HashMap;
import java.util.Map;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.springframework.stereotype.Component;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;

@Component("basicCharacterEncoder")
public class BasicPaletteCharacterEncoderImpl implements CharacterEncoder {
    private static final Map<RGBColor, Integer> BACKGROUND_INDICES = new HashMap<>();
    private static final Map<RGBColor, Integer> FOREGROUND_INDICES = new HashMap<>();

    private static final int DEFAULT_BACKGROUND_INDEX = 40;
    private static final int DEFAULT_FOREGROUND_INDEX = 30;

    static {
        saveColor(30, 40, new RGBColor(0 / 255.f, 0 / 255.f, 0 / 255.f));
        saveColor(31, 41, new RGBColor(170 / 255.f, 0 / 255.f, 0 / 255.f));
        saveColor(32, 42, new RGBColor(0 / 255.f, 170 / 255.f, 0 / 255.f));
        saveColor(33, 43, new RGBColor(170 / 255.f, 85 / 255.f, 0 / 255.f));
        saveColor(34, 44, new RGBColor(0 / 255.f, 0 / 255.f, 170 / 255.f));
        saveColor(35, 45, new RGBColor(170 / 255.f, 0 / 255.f, 170 / 255.f));
        saveColor(36, 46, new RGBColor(0 / 255.f, 170 / 255.f, 170 / 255.f));
        saveColor(37, 47, new RGBColor(170 / 255.f, 170 / 255.f, 170 / 255.f));

        saveColor(90, 100, new RGBColor(85 / 255.f, 85 / 255.f, 85 / 255.f));
        saveColor(91, 101, new RGBColor(255 / 255.f, 85 / 255.f, 85 / 255.f));
        saveColor(92, 102, new RGBColor(85 / 255.f, 255 / 255.f, 85 / 255.f));
        saveColor(93, 103, new RGBColor(255 / 255.f, 255 / 255.f, 85 / 255.f));
        saveColor(94, 104, new RGBColor(85 / 255.f, 85 / 255.f, 255 / 255.f));
        saveColor(95, 105, new RGBColor(255 / 255.f, 85 / 255.f, 255 / 255.f));
        saveColor(96, 106, new RGBColor(85 / 255.f, 255 / 255.f, 255 / 255.f));
        saveColor(97, 107, new RGBColor(255 / 255.f, 255 / 255.f, 255 / 255.f));
    }

    @Override
    public String encode(char character, RGBColor foregroundColor, RGBColor backgroundColor) {
        int foregroundIndex = FOREGROUND_INDICES.getOrDefault(foregroundColor, DEFAULT_FOREGROUND_INDEX);
        int backgroundIndex = BACKGROUND_INDICES.getOrDefault(backgroundColor, DEFAULT_BACKGROUND_INDEX);

        return String.format("\u001B[%d;%dm%c",
                foregroundIndex,
                backgroundIndex,
                character
        );
    }

    private static void saveColor(int foregroundIndex, int backgroundIndex, RGBColor color) {
        BACKGROUND_INDICES.put(color, backgroundIndex);
        FOREGROUND_INDICES.put(color, foregroundIndex);
    }
}
