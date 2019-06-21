package com.github.elementbound.asciima.image2ascii.colors.factory;

import com.github.elementbound.asciima.image2ascii.colors.model.HSVColor;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.springframework.stereotype.Component;

import static com.github.elementbound.asciima.image2ascii.util.MathHelper.max;
import static com.github.elementbound.asciima.image2ascii.util.MathHelper.min;

@Component
public class HSVColorFactory {
    public HSVColor fromRGB(RGBColor rgb) {
        float minValue = min(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        float maxValue = max(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
        float delta = maxValue - minValue;

        float hue = 0f;
        float value = maxValue;
        float saturation = maxValue == 0f ? 0f : delta / maxValue;

        if (delta != 0f) {
            if (maxValue == rgb.getRed()) {
                hue = ((rgb.getGreen() - rgb.getBlue()) / delta) % 6f;
            } else if (maxValue == rgb.getGreen()) {
                hue = ((rgb.getBlue() - rgb.getRed()) / delta + 2f) % 6f;
            } else if (maxValue == rgb.getBlue()) {
                hue = ((rgb.getRed() - rgb.getGreen()) / delta + 4f) % 6f;
            }
        }

        hue /= 6f;

        return new HSVColor(hue, saturation, value);
    }
}
