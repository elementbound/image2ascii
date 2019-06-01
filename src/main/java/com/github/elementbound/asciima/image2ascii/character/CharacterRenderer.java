package com.github.elementbound.asciima.image2ascii.character;

import java.awt.image.BufferedImage;

public interface CharacterRenderer {
    BufferedImage render(char character);
}
