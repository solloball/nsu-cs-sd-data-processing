package ru.nsu.romanov;

import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        final int countIterations = 1000;
        if (args.length != 1) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        int numberThreads;
        try {
            numberThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wrong number format");
        }
        if (numberThreads <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        System.out.println("Pi : " + new PiFinder(countIterations, numberThreads).find());
    }

}