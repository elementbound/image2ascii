package com.github.elementbound.asciima.image2ascii.character.encoder.impl;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;

public class TrueColorCharacterEncoderImpl implements CharacterEncoder {
    @Override
    public String encode(char character, RGBColor foregroundColor, RGBColor backgroundColor) {
        return String.format("\u001B[38;2;%d;%d;%dm\u001B[48;2;%d;%d;%dm%c",
                (int) (foregroundColor.getRed() * 255),
                (int) (foregroundColor.getGreen() * 255),
                (int) (foregroundColor.getBlue() * 255),

                (int) (backgroundColor.getRed() * 255),
                (int) (backgroundColor.getGreen() * 255),
                (int) (backgroundColor.getBlue() * 255),

                character);
    }
}
