package com.github.elementbound.asciima.image2ascii.image;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class ImageIterator implements Iterator<Integer> {
    private BufferedImage image;
    private int x;
    private int y;

    private ImageIterator(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean hasNext() {
        return (x + y * image.getWidth()) < image.getWidth() * image.getHeight();
    }

    @Override
    public Integer next() {
        int result = image.getRGB(x, y);

        x = (x + 1) % image.getWidth();
        y += (x == 0) ? 1 : 0;

        return result;
    }

    public static Iterator<Integer> iterate(BufferedImage image) {
        return new ImageIterator(image, 0, 0);
    }

    public static Iterable<Integer> asIterable(BufferedImage image) {
        Iterator<Integer> iterator = iterate(image);
        return () -> iterator;
    }
}
