package com.github.elementbound.asciima.image2ascii.command.model;

import java.util.List;

import com.github.elementbound.asciima.image2ascii.colors.config.palette.Ascii16Palette;
import com.github.elementbound.asciima.image2ascii.colors.config.palette.Ascii256Palette;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

public enum Palette {
    ASCII_16(Ascii16Palette.colors),
    ASCII_256(Ascii256Palette.colors);

    private final List<RGBColor> palette;

    Palette(List<RGBColor> palette) {
        this.palette = palette;
    }

    public List<RGBColor> getPalette() {
        return palette;
    }
}
