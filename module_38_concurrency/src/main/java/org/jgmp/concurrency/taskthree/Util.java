package org.jgmp.concurrency.taskthree;

import java.util.Random;

public class Util {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH_MIN = 5;
    private static final int LENGTH_MAX = 10;
    private static final Random RANDOM = new Random();

    public static String generateRandomMessage() {
        int length = RANDOM.nextInt(LENGTH_MAX - LENGTH_MIN + 1) + LENGTH_MIN;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}
