package com.github.elementbound.asciima.image2ascii.cli.command.model;

public enum CharacterSet {
    GRADIENT(" .:-=+*#%@"),
    EXTENDED_GRADIENT("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ");

    private final String characters;

    CharacterSet(String characters) {
        this.characters = characters;
    }

    public String getCharacters() {
        return characters;
    }
}
