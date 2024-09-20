package ru.nsu.romanov;

public class Main {
    public static void main(String[] args) {
        final int countIterations = 1000000000;
        int numberThreads = Integer.parseInt(args[0]);
    
        if (numberThreads <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        System.out.println("Pi : " + new PiFinder(countIterations, numberThreads).find());
    }

}
