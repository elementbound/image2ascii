package image2ascii.colors.factory;

import com.github.elementbound.asciima.image2ascii.colors.factory.HSVColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.model.HSVColor;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HSVColorFactoryTest {
    private HSVColorFactory underTest;

    @BeforeMethod
    public void setup() {
        underTest = new HSVColorFactory();
    }

    @Test(dataProvider = "argbToRgbProvider")
    public void fromArgbShouldReturnExpected(RGBColor rgb, HSVColor expected) {
        // Given in setup

        // When
        HSVColor actual = underTest.fromRGB(rgb);

        // Then
        assertThat(actual, is(expected));
    }

    @DataProvider
    public Object[][] argbToRgbProvider() {
        return new Object[][] {
                {new RGBColor(1.0f, 0.0f, 0.0f), new HSVColor(0.0f / 360.0f, 1.0f, 1.0f)}, //red
                {new RGBColor(0.0f, 1.0f, 0.0f), new HSVColor(120.0f / 360.0f, 1.0f, 1.0f)}, //green
                {new RGBColor(0.0f, 0.0f, 1.0f), new HSVColor(240.0f / 360.0f, 1.0f, 1.0f)}, //blue

                {new RGBColor(1.0f, 1.0f, 1.0f), new HSVColor(0.0f, 0.0f, 1.0f)}, //white
                {new RGBColor(0.5f, 0.5f, 0.5f), new HSVColor(0.0f, 0.0f, 0.5f)}, //gray
                {new RGBColor(0.0f, 0.0f, 0.0f), new HSVColor(0.0f, 0.0f, 0.0f)}, //black
        };
    }
}