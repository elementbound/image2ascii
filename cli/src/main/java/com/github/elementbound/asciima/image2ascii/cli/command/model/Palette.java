package com.github.elementbound.asciima.image2ascii.cli.command.model;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.character.encoder.impl.BasicPaletteCharacterEncoderImpl;
import com.github.elementbound.asciima.image2ascii.character.encoder.impl.ExtendedPaletteCharacterEncoderImpl;
import com.github.elementbound.asciima.image2ascii.colors.config.palette.Ascii16Palette;
import com.github.elementbound.asciima.image2ascii.colors.config.palette.Ascii256Palette;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.util.List;

public enum Palette {
    ASCII_16(Ascii16Palette.colors, new BasicPaletteCharacterEncoderImpl()),
    ASCII_256(Ascii256Palette.colors, new ExtendedPaletteCharacterEncoderImpl());

    private final List<RGBColor> palette;
    private final CharacterEncoder characterEncoder;

    Palette(List<RGBColor> palette, CharacterEncoder characterEncoder) {
        this.palette = palette;
        this.characterEncoder = characterEncoder;
    }

    public List<RGBColor> getPalette() {
        return palette;
    }

    public CharacterEncoder getCharacterEncoder() {
        return characterEncoder;
    }
}
