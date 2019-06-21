package com.github.elementbound.asciima.image2ascii.colors.finder;

import com.github.elementbound.asciima.image2ascii.colors.model.RGBColor;

import java.awt.image.BufferedImage;
import java.util.List;

public interface PrimaryColorFinder {
    List<RGBColor> findPrimaryColors(BufferedImage image, int count);
}
