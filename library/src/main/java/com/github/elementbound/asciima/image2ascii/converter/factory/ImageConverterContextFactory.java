package com.github.elementbound.asciima.image2ascii.converter.factory;

import com.github.elementbound.asciima.image2ascii.character.recognizer.impl.CharacterRecognizerImpl;
import com.github.elementbound.asciima.image2ascii.character.renderer.impl.CharacterRendererImpl;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.impl.PalettePrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterConfiguration;
import com.github.elementbound.asciima.image2ascii.converter.model.ImageConverterContext;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ImageConverterContextFactory {
    private RGBColorFactory rgbColorFactory;

    public ImageConverterContext createContext(ImageConverterConfiguration configuration) {
        PalettePrimaryColorFinder primaryColorFinder = new PalettePrimaryColorFinder(rgbColorFactory, configuration.getPalette(), configuration.getColorDistanceFunction());
        ImageColorMapper imageColorMapper = new ImageColorMapper(configuration.getColorMapperFactory(), rgbColorFactory);

        Set<Character> characterSet = stringToSet(configuration.getCharacterSet());
        CharacterRendererImpl characterRenderer = new CharacterRendererImpl(configuration.getFont());
        CharacterRecognizerImpl characterRecognizer = new CharacterRecognizerImpl(characterSet, characterRenderer, configuration.getCharacterWeightFunction());

        return ImageConverterContext.builder()
                .primaryColorFinder(primaryColorFinder)
                .imageColorMapper(imageColorMapper)
                .characterRecognizer(characterRecognizer)
                .build();
    }

    private Set<Character> stringToSet(String string) {
        return IntStream.range(0, string.length())
                .mapToObj(string::charAt)
                .collect(Collectors.toSet());
    }
}
