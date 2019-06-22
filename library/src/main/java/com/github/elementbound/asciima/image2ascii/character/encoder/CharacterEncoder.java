package com.github.elementbound.asciima.image2ascii.character.encoder;

import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;

public interface CharacterEncoder {
    String getFormatString(CharacterCell cell);

    default String encode(CharacterCell cell) {
        return getFormatString(cell) + cell.getCharacter();
    }
}
