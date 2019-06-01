package com.github.elementbound.asciima.image2ascii.colors.factory;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.springframework.stereotype.Component;

@Component
public class RGBColorFactory {
    public RGBColor fromARGB(int argb) {
        return new RGBColor(
                (argb >> 16 & 0xFF) / 255f,
                (argb >> 8 & 0xFF) / 255f,
                (argb & 0xFF) / 255f
        );
    }

    public int toARGB(RGBColor color) {
        return ((int) (color.getRed() * 255)) << 16 |
                ((int) (color.getGreen() * 255)) << 8 |
                ((int) (color.getBlue() * 255));
    }
}
