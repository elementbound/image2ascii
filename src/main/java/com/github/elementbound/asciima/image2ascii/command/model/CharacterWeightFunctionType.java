package com.github.elementbound.asciima.image2ascii.command.model;

import java.util.function.Supplier;

import com.github.elementbound.asciima.image2ascii.character.recognizer.CharacterWeightFunction;
import com.github.elementbound.asciima.image2ascii.character.recognizer.impl.GradientCharacterWeightFunctionImpl;
import com.github.elementbound.asciima.image2ascii.character.recognizer.impl.HitMissCharacterWeightFunctionImpl;

public enum CharacterWeightFunctionType {
    HIT_MISS(HitMissCharacterWeightFunctionImpl::new),
    GRADIENT(GradientCharacterWeightFunctionImpl::new);

    private final Supplier<CharacterWeightFunction> characterWeightFunctionSupplier;

    CharacterWeightFunctionType(Supplier<CharacterWeightFunction> characterWeightFunctionSupplier) {
        this.characterWeightFunctionSupplier = characterWeightFunctionSupplier;
    }

    public Supplier<CharacterWeightFunction> getCharacterWeightFunctionSupplier() {
        return characterWeightFunctionSupplier;
    }
}
