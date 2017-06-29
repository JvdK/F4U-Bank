package com.bank.util;

import java.security.SecureRandom;

public class RandomStringGenerator {

    private final static char[] NUMBERS = "0123456789".toCharArray();
    private static final SecureRandom random = new SecureRandom();


    public static String generateRandomIntegerString(int size){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            char c = NUMBERS[random.nextInt(NUMBERS.length)];
            sb.append(c);
        }
        return sb.toString();
    }

}
