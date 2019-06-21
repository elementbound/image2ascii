package com.github.elementbound.asciima.image2ascii.util;

public class MathHelper {
    @SafeVarargs
    public static <T extends Comparable<T>> T min(T... values) {
        T result = values[0];

        for (int i = 1; i < values.length; i++) {
            if (values[i].compareTo(result) < 0) {
                result = values[i];
            }
        }

        return result;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> T max(T... values) {
        T result = values[0];

        for (int i = 1; i < values.length; i++) {
            if (values[i].compareTo(result) > 0) {
                result = values[i];
            }
        }

        return result;
    }
}
