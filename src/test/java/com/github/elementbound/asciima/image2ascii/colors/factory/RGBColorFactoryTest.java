package com.github.elementbound.asciima.image2ascii.colors.factory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.awt.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

public class RGBColorFactoryTest {
    private static final float HALF = 128.0f / 255.0f;

    private RGBColorFactory underTest;

    @BeforeMethod
    public void setup() {
        underTest = new RGBColorFactory();
    }

    @Test(dataProvider = "argbToRgbProvider")
    public void fromArgbShouldReturnExpected(int argb, RGBColor expected) {
        // Given in setup

        // When
        RGBColor actual = underTest.fromARGB(argb);

        // Then
        assertThat(actual, is(expected));
    }

    @Test(dataProvider = "argbToRgbProvider")
    public void fromRGBColorShouldReturnExpected(int expected, RGBColor rgb) {
        // Given in setup

        // When
        int actual = underTest.toARGB(rgb);

        // Then
        assertThat(actual, is(expected));
    }

    @DataProvider
    public Object[][] argbToRgbProvider() {
        return new Object[][] {
                {new Color(255,   0,   0).getRGB(), new RGBColor(1f, 0f, 0f)}, // red
                {new Color(  0, 255,   0).getRGB(), new RGBColor(0f, 1f, 0f)}, // green
                {new Color(  0,   0, 255).getRGB(), new RGBColor(0f, 0f, 1f)}, // blue

                {new Color(  0, 255, 255).getRGB(), new RGBColor(0f, 1f, 1f)}, // cyan
                {new Color(255,   0, 255).getRGB(), new RGBColor(1f, 0f, 1f)}, // magenta
                {new Color(255, 255,   0).getRGB(), new RGBColor(1f, 1f, 0f)}, // yellow

                {new Color(128, 128, 128).getRGB(), new RGBColor(HALF, HALF, HALF)} // gray
        };
    }
}
