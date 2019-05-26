package com.github.elementbound.asciima.image2ascii.colors.factory;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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

    @DataProvider
    public Object[][] argbToRgbProvider() {
        return new Object[][] {
                {0x00FF0000, new RGBColor(1f, 0f, 0f)}, // red
                {0x0000FF00, new RGBColor(0f, 1f, 0f)}, // green
                {0x000000FF, new RGBColor(0f, 0f, 1f)}, // blue

                {0x0000FFFF, new RGBColor(0f, 1f, 1f)}, // cyan
                {0x00FF00FF, new RGBColor(1f, 0f, 1f)}, // magenta
                {0x0000FFFF, new RGBColor(0f, 1f, 1f)}, // yellow

                {0x00808080, new RGBColor(HALF, HALF, HALF)} // gray
        };
    }
}