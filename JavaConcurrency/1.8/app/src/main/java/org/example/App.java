package org.example;

public class App {
    public static void main(String[] args) throws InterruptedException {
        int numberThreads = Integer.parseInt(args[0]);

        if (numberThreads <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        var solver = new PiFinder(numberThreads);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            solver.finish();
            System.out.println(solver.printRes());
        }));

        solver.find();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException ignored) {
        }
    }
}
