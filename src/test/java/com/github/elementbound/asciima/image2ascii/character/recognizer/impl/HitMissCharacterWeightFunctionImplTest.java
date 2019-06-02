package com.github.elementbound.asciima.image2ascii.character.recognizer.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HitMissCharacterWeightFunctionImplTest {
    private static final int MISS_COLOR = Color.BLACK.getRGB();
    private static final int HIT_COLOR = Color.WHITE.getRGB();
    private static final char HIT_CHARACTER = '#';

    private HitMissCharacterWeightFunctionImpl underTest;

    @BeforeMethod
    public void setup() {
        underTest = new HitMissCharacterWeightFunctionImpl();
    }

    @Test
    public void getWeightShouldReturnNegativeOneOnAllMisses() {
        // Given
        BufferedImage sourceImage = createImage(4, 4, "" +
                "    " +
                "    " +
                "    " +
                "    ");

        BufferedImage characterImage = createImage(4, 4, "" +
                "####" +
                "####" +
                "####" +
                "####");

        // When
        double actual = underTest.getWeight(sourceImage, HIT_COLOR, characterImage);

        // Then
        assertThat(actual, is(-1.0));
    }

    @Test
    public void getWeightShouldReturnZeroOnEqualHitMiss() {
        // Given
        BufferedImage sourceImage = createImage(4, 4, "" +
                "# # " +
                " # #" +
                "# # " +
                " # #");

        BufferedImage characterImage = createImage(4, 4, "" +
                " # #" +
                " # #" +
                " # #" +
                " # #");

        // When
        double actual = underTest.getWeight(sourceImage, HIT_COLOR, characterImage);

        // Then
        assertThat(actual, is(0.0));
    }

    @Test
    public void getWeightShouldReturnOneOnAllHits() {
        // Given
        BufferedImage sourceImage = createImage(4, 4, "" +
                "####" +
                "####" +
                "####" +
                "####");

        BufferedImage characterImage = createImage(4, 4, "" +
                "####" +
                "####" +
                "####" +
                "####");

        // When
        double actual = underTest.getWeight(sourceImage, HIT_COLOR, characterImage);

        // Then
        assertThat(actual, is(1.0));
    }

    private BufferedImage createImage(int width, int height, String text) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        int x = 0;
        int y = 0;

        for (char c : text.toCharArray()) {
            result.setRGB(x, y, c == HIT_CHARACTER ? HIT_COLOR : MISS_COLOR);

            x += 1;
            if (x >= width) {
                x = 0;
                y += 1;
            }
        }

        return result;
    }
}