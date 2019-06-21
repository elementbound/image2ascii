package com.github.elementbound.asciima.image2ascii.font;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Arrays;

public class FontSize {
    private final int width;
    private final int height;

    public FontSize(Font font) {
        Canvas canvas = new Canvas();
        FontMetrics metrics = canvas.getFontMetrics(font);

        width = Arrays.stream(metrics.getWidths()).max().getAsInt();
        height = metrics.getHeight();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
