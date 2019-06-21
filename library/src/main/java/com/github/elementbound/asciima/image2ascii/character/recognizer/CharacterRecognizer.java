package com.github.elementbound.asciima.image2ascii.character.recognizer;

import java.awt.image.BufferedImage;

public interface CharacterRecognizer {
    /**
     * Recognize com.github.elementbound.asciima.image2ascii.character in com.github.elementbound.asciima.image2ascii.image.
     *
     * @param image       com.github.elementbound.asciima.image2ascii.image to search
     * @param targetColor color of the com.github.elementbound.asciima.image2ascii.character
     * @return recognized com.github.elementbound.asciima.image2ascii.character
     */
    char recognize(BufferedImage image, int targetColor);
}
