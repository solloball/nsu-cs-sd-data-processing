package ru.nsu.romanov;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> IntStream.range(0, 10).forEach(i -> System.out.println(i + " text"))).start();
    }
}