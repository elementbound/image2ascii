package com.github.elementbound.asciima.image2ascii.colors.factory;

import java.awt.*;

import org.springframework.stereotype.Component;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

@Component
public class RGBColorFactory {
    public RGBColor fromARGB(int argb) {
        Color color = new Color(argb);

        return new RGBColor(
                color.getRed() / 255.0f,
            color.getGreen() / 255.0f,
            color.getBlue() / 255.0f
        );
    }

    public int toARGB(RGBColor color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue()).getRGB();
    }
}
