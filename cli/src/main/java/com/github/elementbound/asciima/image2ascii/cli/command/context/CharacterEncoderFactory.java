package com.github.elementbound.asciima.image2ascii.cli.command.context;

import org.springframework.stereotype.Component;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.cli.command.model.Palette;

@Component
public class CharacterEncoderFactory {
    private final CharacterEncoder basicCharacterEncoder;
    private final CharacterEncoder extendedCharacterEncoder;

    public CharacterEncoderFactory(CharacterEncoder basicCharacterEncoder, CharacterEncoder extendedCharacterEncoder) {
        this.basicCharacterEncoder = basicCharacterEncoder;
        this.extendedCharacterEncoder = extendedCharacterEncoder;
    }

    public CharacterEncoder getCharacterEncoder(Palette palette) {
        if(palette == Palette.ASCII_16) {
            return basicCharacterEncoder;
        } else if(palette == Palette.ASCII_256) {
            return extendedCharacterEncoder;
        } else {
            throw new IllegalArgumentException("No known encoder for palette: " + palette);
        }
    }
}
