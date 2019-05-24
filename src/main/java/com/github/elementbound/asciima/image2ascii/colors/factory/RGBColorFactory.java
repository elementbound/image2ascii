package com.github.elementbound.asciima.image2ascii.colors.factory;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

public class RGBColorFactory {
    public RGBColor fromARGB(int argb) {
        return new RGBColor(
                (argb >> 16 & 0xFF) / 255f,
                (argb >> 8 & 0xFF) / 255f,
                (argb & 0xFF) / 255f
        );
    }
}
