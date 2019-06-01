package com.github.elementbound.asciima.image2ascii.character.recognizer;

import java.awt.image.BufferedImage;

public interface CharacterRecognizer {
    /**
     * Recognize character in image.
     *
     * @param image       image to search
     * @param targetColor color of the character
     * @return recognized character
     */
    char recognize(BufferedImage image, int targetColor);
}
