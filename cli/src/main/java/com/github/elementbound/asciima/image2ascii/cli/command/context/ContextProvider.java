package com.github.elementbound.asciima.image2ascii.cli.command.context;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterWeightFunction;
import com.github.elementbound.asciima.image2ascii.character.recognizer.impl.CharacterRecognizerImpl;
import com.github.elementbound.asciima.image2ascii.character.renderer.CharacterRenderer;
import com.github.elementbound.asciima.image2ascii.colors.ColorDistanceFunction;
import com.github.elementbound.asciima.image2ascii.colors.factory.RGBColorFactory;
import com.github.elementbound.asciima.image2ascii.colors.finder.impl.PalettePrimaryColorFinder;
import com.github.elementbound.asciima.image2ascii.colors.mapper.ColorMapperFactory;
import com.github.elementbound.asciima.image2ascii.cli.command.context.model.ContextRequest;
import com.github.elementbound.asciima.image2ascii.cli.command.context.model.ContextResponse;
import com.github.elementbound.asciima.image2ascii.image.ImageColorMapper;

@Component
public class ContextProvider {
    private final CharacterEncoderFactory characterEncoderFactory;

    private final CharacterRenderer characterRenderer;
    private final RGBColorFactory rgbColorFactory;
    private final ColorDistanceFunction colorDistanceFunction;

    public ContextProvider(CharacterEncoderFactory characterEncoderFactory, CharacterRenderer characterRenderer, RGBColorFactory rgbColorFactory, ColorDistanceFunction colorDistanceFunction) {
        this.characterEncoderFactory = characterEncoderFactory;
        this.characterRenderer = characterRenderer;
        this.rgbColorFactory = rgbColorFactory;
        this.colorDistanceFunction = colorDistanceFunction;
    }

    public ContextResponse getContext(ContextRequest request) {
        String characters = request.getCharacterSet().getCharacters();
        Set<Character> characterSet = IntStream.range(0, characters.length())
            .mapToObj(characters::charAt)
            .collect(Collectors.toSet());

        Supplier<CharacterWeightFunction> characterWeightFunctionSupplier = request.getCharacterWeightFunctionType().getCharacterWeightFunctionSupplier();
        Function<ColorDistanceFunction, ColorMapperFactory> colorMapperFactorySupplier = request.getColorMapperType().getFactorySupplier();

        return ContextResponse.builder()
            .characterEncoder(characterEncoderFactory.getCharacterEncoder(request.getPalette()))
            .characterRecognizer(new CharacterRecognizerImpl(characterSet, characterRenderer, characterWeightFunctionSupplier.get()))
            .palettePrimaryColorFinder(new PalettePrimaryColorFinder(rgbColorFactory, request.getPalette().getPalette(),  colorDistanceFunction))
            .imageColorMapper(new ImageColorMapper(colorMapperFactorySupplier.apply(colorDistanceFunction), rgbColorFactory))
            .build();
    }
}
