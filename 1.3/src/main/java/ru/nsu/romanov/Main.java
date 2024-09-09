package ru.nsu.romanov;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> printStrings(List.of("1 string", "1 hello", "1 test"))).start();
        new Thread(() -> printStrings(List.of("2 wow", "2 name", "2 java"))).start();
        new Thread(() -> printStrings(List.of("3 class", "3 text", "3 thread"))).start();
        new Thread(() -> printStrings(List.of("4 god", "4 pc", "4 word"))).start();

    }

    private static void printStrings(List<String> strings) {
        strings.forEach(System.out::println);
    }
}