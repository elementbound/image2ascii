package com.github.elementbound.asciima.image2ascii.character.renderer.impl;

import com.github.elementbound.asciima.image2ascii.character.renderer.CharacterRenderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CharacterRendererImpl implements CharacterRenderer {
    private final Font font;

    public CharacterRendererImpl(Font font) {
        this.font = font;
    }

    @Override
    public BufferedImage render(char character) {
        Canvas canvas = new Canvas();
        FontMetrics metrics = canvas.getFontMetrics(font);
        String characterString = String.valueOf(character);

        int width = metrics.stringWidth(characterString);
        int height = metrics.getHeight();

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_GRAY);
        Graphics graphics = result.getGraphics();

        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, width, height);

        graphics.setColor(Color.WHITE);
        graphics.setFont(font);
        graphics.drawString(characterString, 0, height - metrics.getMaxDescent());

        return result;
    }
}
