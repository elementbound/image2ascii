package com.github.elementbound.asciima.image2ascii.character.encoder.impl;

import com.github.elementbound.asciima.image2ascii.character.encoder.CharacterEncoder;
import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;
import com.github.elementbound.asciima.image2ascii.converter.model.CharacterCell;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("extendedCharacterEncoder")
public class ExtendedPaletteCharacterEncoderImpl implements CharacterEncoder {
    private static final int DEFAULT_INDEX = 0;

    private static final Map<RGBColor, Integer> INDICES = new HashMap<>();

    @Override
    public String getFormatString(CharacterCell cell) {
        int foregroundIndex = INDICES.getOrDefault(cell.getForegroundColor(), DEFAULT_INDEX);
        int backgroundIndex = INDICES.getOrDefault(cell.getBackgroundColor(), DEFAULT_INDEX);

        return String.format("\u001B[38;5;%dm\u001B[48;5;%dm",
                foregroundIndex,
                backgroundIndex
        );
    }

    static {
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 0 / 255.f), 0);
        INDICES.put(new RGBColor(128 / 255.f, 0 / 255.f, 0 / 255.f), 1);
        INDICES.put(new RGBColor(0 / 255.f, 128 / 255.f, 0 / 255.f), 2);
        INDICES.put(new RGBColor(128 / 255.f, 128 / 255.f, 0 / 255.f), 3);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 128 / 255.f), 4);
        INDICES.put(new RGBColor(128 / 255.f, 0 / 255.f, 128 / 255.f), 5);
        INDICES.put(new RGBColor(0 / 255.f, 128 / 255.f, 128 / 255.f), 6);
        INDICES.put(new RGBColor(192 / 255.f, 192 / 255.f, 192 / 255.f), 7);
        INDICES.put(new RGBColor(128 / 255.f, 128 / 255.f, 128 / 255.f), 8);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 0 / 255.f), 9);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 0 / 255.f), 10);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 0 / 255.f), 11);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 255 / 255.f), 12);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 255 / 255.f), 13);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 255 / 255.f), 14);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 255 / 255.f), 15);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 0 / 255.f), 16);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 95 / 255.f), 17);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 135 / 255.f), 18);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 175 / 255.f), 19);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 215 / 255.f), 20);
        INDICES.put(new RGBColor(0 / 255.f, 0 / 255.f, 255 / 255.f), 21);
        INDICES.put(new RGBColor(0 / 255.f, 95 / 255.f, 0 / 255.f), 22);
        INDICES.put(new RGBColor(0 / 255.f, 95 / 255.f, 95 / 255.f), 23);
        INDICES.put(new RGBColor(0 / 255.f, 95 / 255.f, 135 / 255.f), 24);
        INDICES.put(new RGBColor(0 / 255.f, 95 / 255.f, 175 / 255.f), 25);
        INDICES.put(new RGBColor(0 / 255.f, 95 / 255.f, 215 / 255.f), 26);
        INDICES.put(new RGBColor(0 / 255.f, 95 / 255.f, 255 / 255.f), 27);
        INDICES.put(new RGBColor(0 / 255.f, 135 / 255.f, 0 / 255.f), 28);
        INDICES.put(new RGBColor(0 / 255.f, 135 / 255.f, 95 / 255.f), 29);
        INDICES.put(new RGBColor(0 / 255.f, 135 / 255.f, 135 / 255.f), 30);
        INDICES.put(new RGBColor(0 / 255.f, 135 / 255.f, 175 / 255.f), 31);
        INDICES.put(new RGBColor(0 / 255.f, 135 / 255.f, 215 / 255.f), 32);
        INDICES.put(new RGBColor(0 / 255.f, 135 / 255.f, 255 / 255.f), 33);
        INDICES.put(new RGBColor(0 / 255.f, 175 / 255.f, 0 / 255.f), 34);
        INDICES.put(new RGBColor(0 / 255.f, 175 / 255.f, 95 / 255.f), 35);
        INDICES.put(new RGBColor(0 / 255.f, 175 / 255.f, 135 / 255.f), 36);
        INDICES.put(new RGBColor(0 / 255.f, 175 / 255.f, 175 / 255.f), 37);
        INDICES.put(new RGBColor(0 / 255.f, 175 / 255.f, 215 / 255.f), 38);
        INDICES.put(new RGBColor(0 / 255.f, 175 / 255.f, 255 / 255.f), 39);
        INDICES.put(new RGBColor(0 / 255.f, 215 / 255.f, 0 / 255.f), 40);
        INDICES.put(new RGBColor(0 / 255.f, 215 / 255.f, 95 / 255.f), 41);
        INDICES.put(new RGBColor(0 / 255.f, 215 / 255.f, 135 / 255.f), 42);
        INDICES.put(new RGBColor(0 / 255.f, 215 / 255.f, 175 / 255.f), 43);
        INDICES.put(new RGBColor(0 / 255.f, 215 / 255.f, 215 / 255.f), 44);
        INDICES.put(new RGBColor(0 / 255.f, 215 / 255.f, 255 / 255.f), 45);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 0 / 255.f), 46);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 95 / 255.f), 47);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 135 / 255.f), 48);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 175 / 255.f), 49);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 215 / 255.f), 50);
        INDICES.put(new RGBColor(0 / 255.f, 255 / 255.f, 255 / 255.f), 51);
        INDICES.put(new RGBColor(95 / 255.f, 0 / 255.f, 0 / 255.f), 52);
        INDICES.put(new RGBColor(95 / 255.f, 0 / 255.f, 95 / 255.f), 53);
        INDICES.put(new RGBColor(95 / 255.f, 0 / 255.f, 135 / 255.f), 54);
        INDICES.put(new RGBColor(95 / 255.f, 0 / 255.f, 175 / 255.f), 55);
        INDICES.put(new RGBColor(95 / 255.f, 0 / 255.f, 215 / 255.f), 56);
        INDICES.put(new RGBColor(95 / 255.f, 0 / 255.f, 255 / 255.f), 57);
        INDICES.put(new RGBColor(95 / 255.f, 95 / 255.f, 0 / 255.f), 58);
        INDICES.put(new RGBColor(95 / 255.f, 95 / 255.f, 95 / 255.f), 59);
        INDICES.put(new RGBColor(95 / 255.f, 95 / 255.f, 135 / 255.f), 60);
        INDICES.put(new RGBColor(95 / 255.f, 95 / 255.f, 175 / 255.f), 61);
        INDICES.put(new RGBColor(95 / 255.f, 95 / 255.f, 215 / 255.f), 62);
        INDICES.put(new RGBColor(95 / 255.f, 95 / 255.f, 255 / 255.f), 63);
        INDICES.put(new RGBColor(95 / 255.f, 135 / 255.f, 0 / 255.f), 64);
        INDICES.put(new RGBColor(95 / 255.f, 135 / 255.f, 95 / 255.f), 65);
        INDICES.put(new RGBColor(95 / 255.f, 135 / 255.f, 135 / 255.f), 66);
        INDICES.put(new RGBColor(95 / 255.f, 135 / 255.f, 175 / 255.f), 67);
        INDICES.put(new RGBColor(95 / 255.f, 135 / 255.f, 215 / 255.f), 68);
        INDICES.put(new RGBColor(95 / 255.f, 135 / 255.f, 255 / 255.f), 69);
        INDICES.put(new RGBColor(95 / 255.f, 175 / 255.f, 0 / 255.f), 70);
        INDICES.put(new RGBColor(95 / 255.f, 175 / 255.f, 95 / 255.f), 71);
        INDICES.put(new RGBColor(95 / 255.f, 175 / 255.f, 135 / 255.f), 72);
        INDICES.put(new RGBColor(95 / 255.f, 175 / 255.f, 175 / 255.f), 73);
        INDICES.put(new RGBColor(95 / 255.f, 175 / 255.f, 215 / 255.f), 74);
        INDICES.put(new RGBColor(95 / 255.f, 175 / 255.f, 255 / 255.f), 75);
        INDICES.put(new RGBColor(95 / 255.f, 215 / 255.f, 0 / 255.f), 76);
        INDICES.put(new RGBColor(95 / 255.f, 215 / 255.f, 95 / 255.f), 77);
        INDICES.put(new RGBColor(95 / 255.f, 215 / 255.f, 135 / 255.f), 78);
        INDICES.put(new RGBColor(95 / 255.f, 215 / 255.f, 175 / 255.f), 79);
        INDICES.put(new RGBColor(95 / 255.f, 215 / 255.f, 215 / 255.f), 80);
        INDICES.put(new RGBColor(95 / 255.f, 215 / 255.f, 255 / 255.f), 81);
        INDICES.put(new RGBColor(95 / 255.f, 255 / 255.f, 0 / 255.f), 82);
        INDICES.put(new RGBColor(95 / 255.f, 255 / 255.f, 95 / 255.f), 83);
        INDICES.put(new RGBColor(95 / 255.f, 255 / 255.f, 135 / 255.f), 84);
        INDICES.put(new RGBColor(95 / 255.f, 255 / 255.f, 175 / 255.f), 85);
        INDICES.put(new RGBColor(95 / 255.f, 255 / 255.f, 215 / 255.f), 86);
        INDICES.put(new RGBColor(95 / 255.f, 255 / 255.f, 255 / 255.f), 87);
        INDICES.put(new RGBColor(135 / 255.f, 0 / 255.f, 0 / 255.f), 88);
        INDICES.put(new RGBColor(135 / 255.f, 0 / 255.f, 95 / 255.f), 89);
        INDICES.put(new RGBColor(135 / 255.f, 0 / 255.f, 135 / 255.f), 90);
        INDICES.put(new RGBColor(135 / 255.f, 0 / 255.f, 175 / 255.f), 91);
        INDICES.put(new RGBColor(135 / 255.f, 0 / 255.f, 215 / 255.f), 92);
        INDICES.put(new RGBColor(135 / 255.f, 0 / 255.f, 255 / 255.f), 93);
        INDICES.put(new RGBColor(135 / 255.f, 95 / 255.f, 0 / 255.f), 94);
        INDICES.put(new RGBColor(135 / 255.f, 95 / 255.f, 95 / 255.f), 95);
        INDICES.put(new RGBColor(135 / 255.f, 95 / 255.f, 135 / 255.f), 96);
        INDICES.put(new RGBColor(135 / 255.f, 95 / 255.f, 175 / 255.f), 97);
        INDICES.put(new RGBColor(135 / 255.f, 95 / 255.f, 215 / 255.f), 98);
        INDICES.put(new RGBColor(135 / 255.f, 95 / 255.f, 255 / 255.f), 99);
        INDICES.put(new RGBColor(135 / 255.f, 135 / 255.f, 0 / 255.f), 100);
        INDICES.put(new RGBColor(135 / 255.f, 135 / 255.f, 95 / 255.f), 101);
        INDICES.put(new RGBColor(135 / 255.f, 135 / 255.f, 135 / 255.f), 102);
        INDICES.put(new RGBColor(135 / 255.f, 135 / 255.f, 175 / 255.f), 103);
        INDICES.put(new RGBColor(135 / 255.f, 135 / 255.f, 215 / 255.f), 104);
        INDICES.put(new RGBColor(135 / 255.f, 135 / 255.f, 255 / 255.f), 105);
        INDICES.put(new RGBColor(135 / 255.f, 175 / 255.f, 0 / 255.f), 106);
        INDICES.put(new RGBColor(135 / 255.f, 175 / 255.f, 95 / 255.f), 107);
        INDICES.put(new RGBColor(135 / 255.f, 175 / 255.f, 135 / 255.f), 108);
        INDICES.put(new RGBColor(135 / 255.f, 175 / 255.f, 175 / 255.f), 109);
        INDICES.put(new RGBColor(135 / 255.f, 175 / 255.f, 215 / 255.f), 110);
        INDICES.put(new RGBColor(135 / 255.f, 175 / 255.f, 255 / 255.f), 111);
        INDICES.put(new RGBColor(135 / 255.f, 215 / 255.f, 0 / 255.f), 112);
        INDICES.put(new RGBColor(135 / 255.f, 215 / 255.f, 95 / 255.f), 113);
        INDICES.put(new RGBColor(135 / 255.f, 215 / 255.f, 135 / 255.f), 114);
        INDICES.put(new RGBColor(135 / 255.f, 215 / 255.f, 175 / 255.f), 115);
        INDICES.put(new RGBColor(135 / 255.f, 215 / 255.f, 215 / 255.f), 116);
        INDICES.put(new RGBColor(135 / 255.f, 215 / 255.f, 255 / 255.f), 117);
        INDICES.put(new RGBColor(135 / 255.f, 255 / 255.f, 0 / 255.f), 118);
        INDICES.put(new RGBColor(135 / 255.f, 255 / 255.f, 95 / 255.f), 119);
        INDICES.put(new RGBColor(135 / 255.f, 255 / 255.f, 135 / 255.f), 120);
        INDICES.put(new RGBColor(135 / 255.f, 255 / 255.f, 175 / 255.f), 121);
        INDICES.put(new RGBColor(135 / 255.f, 255 / 255.f, 215 / 255.f), 122);
        INDICES.put(new RGBColor(135 / 255.f, 255 / 255.f, 255 / 255.f), 123);
        INDICES.put(new RGBColor(175 / 255.f, 0 / 255.f, 0 / 255.f), 124);
        INDICES.put(new RGBColor(175 / 255.f, 0 / 255.f, 95 / 255.f), 125);
        INDICES.put(new RGBColor(175 / 255.f, 0 / 255.f, 135 / 255.f), 126);
        INDICES.put(new RGBColor(175 / 255.f, 0 / 255.f, 175 / 255.f), 127);
        INDICES.put(new RGBColor(175 / 255.f, 0 / 255.f, 215 / 255.f), 128);
        INDICES.put(new RGBColor(175 / 255.f, 0 / 255.f, 255 / 255.f), 129);
        INDICES.put(new RGBColor(175 / 255.f, 95 / 255.f, 0 / 255.f), 130);
        INDICES.put(new RGBColor(175 / 255.f, 95 / 255.f, 95 / 255.f), 131);
        INDICES.put(new RGBColor(175 / 255.f, 95 / 255.f, 135 / 255.f), 132);
        INDICES.put(new RGBColor(175 / 255.f, 95 / 255.f, 175 / 255.f), 133);
        INDICES.put(new RGBColor(175 / 255.f, 95 / 255.f, 215 / 255.f), 134);
        INDICES.put(new RGBColor(175 / 255.f, 95 / 255.f, 255 / 255.f), 135);
        INDICES.put(new RGBColor(175 / 255.f, 135 / 255.f, 0 / 255.f), 136);
        INDICES.put(new RGBColor(175 / 255.f, 135 / 255.f, 95 / 255.f), 137);
        INDICES.put(new RGBColor(175 / 255.f, 135 / 255.f, 135 / 255.f), 138);
        INDICES.put(new RGBColor(175 / 255.f, 135 / 255.f, 175 / 255.f), 139);
        INDICES.put(new RGBColor(175 / 255.f, 135 / 255.f, 215 / 255.f), 140);
        INDICES.put(new RGBColor(175 / 255.f, 135 / 255.f, 255 / 255.f), 141);
        INDICES.put(new RGBColor(175 / 255.f, 175 / 255.f, 0 / 255.f), 142);
        INDICES.put(new RGBColor(175 / 255.f, 175 / 255.f, 95 / 255.f), 143);
        INDICES.put(new RGBColor(175 / 255.f, 175 / 255.f, 135 / 255.f), 144);
        INDICES.put(new RGBColor(175 / 255.f, 175 / 255.f, 175 / 255.f), 145);
        INDICES.put(new RGBColor(175 / 255.f, 175 / 255.f, 215 / 255.f), 146);
        INDICES.put(new RGBColor(175 / 255.f, 175 / 255.f, 255 / 255.f), 147);
        INDICES.put(new RGBColor(175 / 255.f, 215 / 255.f, 0 / 255.f), 148);
        INDICES.put(new RGBColor(175 / 255.f, 215 / 255.f, 95 / 255.f), 149);
        INDICES.put(new RGBColor(175 / 255.f, 215 / 255.f, 135 / 255.f), 150);
        INDICES.put(new RGBColor(175 / 255.f, 215 / 255.f, 175 / 255.f), 151);
        INDICES.put(new RGBColor(175 / 255.f, 215 / 255.f, 215 / 255.f), 152);
        INDICES.put(new RGBColor(175 / 255.f, 215 / 255.f, 255 / 255.f), 153);
        INDICES.put(new RGBColor(175 / 255.f, 255 / 255.f, 0 / 255.f), 154);
        INDICES.put(new RGBColor(175 / 255.f, 255 / 255.f, 95 / 255.f), 155);
        INDICES.put(new RGBColor(175 / 255.f, 255 / 255.f, 135 / 255.f), 156);
        INDICES.put(new RGBColor(175 / 255.f, 255 / 255.f, 175 / 255.f), 157);
        INDICES.put(new RGBColor(175 / 255.f, 255 / 255.f, 215 / 255.f), 158);
        INDICES.put(new RGBColor(175 / 255.f, 255 / 255.f, 255 / 255.f), 159);
        INDICES.put(new RGBColor(215 / 255.f, 0 / 255.f, 0 / 255.f), 160);
        INDICES.put(new RGBColor(215 / 255.f, 0 / 255.f, 95 / 255.f), 161);
        INDICES.put(new RGBColor(215 / 255.f, 0 / 255.f, 135 / 255.f), 162);
        INDICES.put(new RGBColor(215 / 255.f, 0 / 255.f, 175 / 255.f), 163);
        INDICES.put(new RGBColor(215 / 255.f, 0 / 255.f, 215 / 255.f), 164);
        INDICES.put(new RGBColor(215 / 255.f, 0 / 255.f, 255 / 255.f), 165);
        INDICES.put(new RGBColor(215 / 255.f, 95 / 255.f, 0 / 255.f), 166);
        INDICES.put(new RGBColor(215 / 255.f, 95 / 255.f, 95 / 255.f), 167);
        INDICES.put(new RGBColor(215 / 255.f, 95 / 255.f, 135 / 255.f), 168);
        INDICES.put(new RGBColor(215 / 255.f, 95 / 255.f, 175 / 255.f), 169);
        INDICES.put(new RGBColor(215 / 255.f, 95 / 255.f, 215 / 255.f), 170);
        INDICES.put(new RGBColor(215 / 255.f, 95 / 255.f, 255 / 255.f), 171);
        INDICES.put(new RGBColor(215 / 255.f, 135 / 255.f, 0 / 255.f), 172);
        INDICES.put(new RGBColor(215 / 255.f, 135 / 255.f, 95 / 255.f), 173);
        INDICES.put(new RGBColor(215 / 255.f, 135 / 255.f, 135 / 255.f), 174);
        INDICES.put(new RGBColor(215 / 255.f, 135 / 255.f, 175 / 255.f), 175);
        INDICES.put(new RGBColor(215 / 255.f, 135 / 255.f, 215 / 255.f), 176);
        INDICES.put(new RGBColor(215 / 255.f, 135 / 255.f, 255 / 255.f), 177);
        INDICES.put(new RGBColor(215 / 255.f, 175 / 255.f, 0 / 255.f), 178);
        INDICES.put(new RGBColor(215 / 255.f, 175 / 255.f, 95 / 255.f), 179);
        INDICES.put(new RGBColor(215 / 255.f, 175 / 255.f, 135 / 255.f), 180);
        INDICES.put(new RGBColor(215 / 255.f, 175 / 255.f, 175 / 255.f), 181);
        INDICES.put(new RGBColor(215 / 255.f, 175 / 255.f, 215 / 255.f), 182);
        INDICES.put(new RGBColor(215 / 255.f, 175 / 255.f, 255 / 255.f), 183);
        INDICES.put(new RGBColor(215 / 255.f, 215 / 255.f, 0 / 255.f), 184);
        INDICES.put(new RGBColor(215 / 255.f, 215 / 255.f, 95 / 255.f), 185);
        INDICES.put(new RGBColor(215 / 255.f, 215 / 255.f, 135 / 255.f), 186);
        INDICES.put(new RGBColor(215 / 255.f, 215 / 255.f, 175 / 255.f), 187);
        INDICES.put(new RGBColor(215 / 255.f, 215 / 255.f, 215 / 255.f), 188);
        INDICES.put(new RGBColor(215 / 255.f, 215 / 255.f, 255 / 255.f), 189);
        INDICES.put(new RGBColor(215 / 255.f, 255 / 255.f, 0 / 255.f), 190);
        INDICES.put(new RGBColor(215 / 255.f, 255 / 255.f, 95 / 255.f), 191);
        INDICES.put(new RGBColor(215 / 255.f, 255 / 255.f, 135 / 255.f), 192);
        INDICES.put(new RGBColor(215 / 255.f, 255 / 255.f, 175 / 255.f), 193);
        INDICES.put(new RGBColor(215 / 255.f, 255 / 255.f, 215 / 255.f), 194);
        INDICES.put(new RGBColor(215 / 255.f, 255 / 255.f, 255 / 255.f), 195);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 0 / 255.f), 196);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 95 / 255.f), 197);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 135 / 255.f), 198);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 175 / 255.f), 199);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 215 / 255.f), 200);
        INDICES.put(new RGBColor(255 / 255.f, 0 / 255.f, 255 / 255.f), 201);
        INDICES.put(new RGBColor(255 / 255.f, 95 / 255.f, 0 / 255.f), 202);
        INDICES.put(new RGBColor(255 / 255.f, 95 / 255.f, 95 / 255.f), 203);
        INDICES.put(new RGBColor(255 / 255.f, 95 / 255.f, 135 / 255.f), 204);
        INDICES.put(new RGBColor(255 / 255.f, 95 / 255.f, 175 / 255.f), 205);
        INDICES.put(new RGBColor(255 / 255.f, 95 / 255.f, 215 / 255.f), 206);
        INDICES.put(new RGBColor(255 / 255.f, 95 / 255.f, 255 / 255.f), 207);
        INDICES.put(new RGBColor(255 / 255.f, 135 / 255.f, 0 / 255.f), 208);
        INDICES.put(new RGBColor(255 / 255.f, 135 / 255.f, 95 / 255.f), 209);
        INDICES.put(new RGBColor(255 / 255.f, 135 / 255.f, 135 / 255.f), 210);
        INDICES.put(new RGBColor(255 / 255.f, 135 / 255.f, 175 / 255.f), 211);
        INDICES.put(new RGBColor(255 / 255.f, 135 / 255.f, 215 / 255.f), 212);
        INDICES.put(new RGBColor(255 / 255.f, 135 / 255.f, 255 / 255.f), 213);
        INDICES.put(new RGBColor(255 / 255.f, 175 / 255.f, 0 / 255.f), 214);
        INDICES.put(new RGBColor(255 / 255.f, 175 / 255.f, 95 / 255.f), 215);
        INDICES.put(new RGBColor(255 / 255.f, 175 / 255.f, 135 / 255.f), 216);
        INDICES.put(new RGBColor(255 / 255.f, 175 / 255.f, 175 / 255.f), 217);
        INDICES.put(new RGBColor(255 / 255.f, 175 / 255.f, 215 / 255.f), 218);
        INDICES.put(new RGBColor(255 / 255.f, 175 / 255.f, 255 / 255.f), 219);
        INDICES.put(new RGBColor(255 / 255.f, 215 / 255.f, 0 / 255.f), 220);
        INDICES.put(new RGBColor(255 / 255.f, 215 / 255.f, 95 / 255.f), 221);
        INDICES.put(new RGBColor(255 / 255.f, 215 / 255.f, 135 / 255.f), 222);
        INDICES.put(new RGBColor(255 / 255.f, 215 / 255.f, 175 / 255.f), 223);
        INDICES.put(new RGBColor(255 / 255.f, 215 / 255.f, 215 / 255.f), 224);
        INDICES.put(new RGBColor(255 / 255.f, 215 / 255.f, 255 / 255.f), 225);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 0 / 255.f), 226);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 95 / 255.f), 227);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 135 / 255.f), 228);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 175 / 255.f), 229);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 215 / 255.f), 230);
        INDICES.put(new RGBColor(255 / 255.f, 255 / 255.f, 255 / 255.f), 231);
        INDICES.put(new RGBColor(8 / 255.f, 8 / 255.f, 8 / 255.f), 232);
        INDICES.put(new RGBColor(18 / 255.f, 18 / 255.f, 18 / 255.f), 233);
        INDICES.put(new RGBColor(28 / 255.f, 28 / 255.f, 28 / 255.f), 234);
        INDICES.put(new RGBColor(38 / 255.f, 38 / 255.f, 38 / 255.f), 235);
        INDICES.put(new RGBColor(48 / 255.f, 48 / 255.f, 48 / 255.f), 236);
        INDICES.put(new RGBColor(58 / 255.f, 58 / 255.f, 58 / 255.f), 237);
        INDICES.put(new RGBColor(68 / 255.f, 68 / 255.f, 68 / 255.f), 238);
        INDICES.put(new RGBColor(78 / 255.f, 78 / 255.f, 78 / 255.f), 239);
        INDICES.put(new RGBColor(88 / 255.f, 88 / 255.f, 88 / 255.f), 240);
        INDICES.put(new RGBColor(98 / 255.f, 98 / 255.f, 98 / 255.f), 241);
        INDICES.put(new RGBColor(108 / 255.f, 108 / 255.f, 108 / 255.f), 242);
        INDICES.put(new RGBColor(118 / 255.f, 118 / 255.f, 118 / 255.f), 243);
        INDICES.put(new RGBColor(128 / 255.f, 128 / 255.f, 128 / 255.f), 244);
        INDICES.put(new RGBColor(138 / 255.f, 138 / 255.f, 138 / 255.f), 245);
        INDICES.put(new RGBColor(148 / 255.f, 148 / 255.f, 148 / 255.f), 246);
        INDICES.put(new RGBColor(158 / 255.f, 158 / 255.f, 158 / 255.f), 247);
        INDICES.put(new RGBColor(168 / 255.f, 168 / 255.f, 168 / 255.f), 248);
        INDICES.put(new RGBColor(178 / 255.f, 178 / 255.f, 178 / 255.f), 249);
        INDICES.put(new RGBColor(188 / 255.f, 188 / 255.f, 188 / 255.f), 250);
        INDICES.put(new RGBColor(198 / 255.f, 198 / 255.f, 198 / 255.f), 251);
        INDICES.put(new RGBColor(208 / 255.f, 208 / 255.f, 208 / 255.f), 252);
        INDICES.put(new RGBColor(218 / 255.f, 218 / 255.f, 218 / 255.f), 253);
        INDICES.put(new RGBColor(228 / 255.f, 228 / 255.f, 228 / 255.f), 254);
        INDICES.put(new RGBColor(238 / 255.f, 238 / 255.f, 238 / 255.f), 255);
    }
}
