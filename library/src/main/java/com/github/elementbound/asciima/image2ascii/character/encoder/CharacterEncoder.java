package com.github.elementbound.asciima.image2ascii.character.encoder;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

public interface CharacterEncoder {
    String encode(char character, RGBColor foregroundColor, RGBColor backgroundColor);
}
