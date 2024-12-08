package ru.nsu.romanov;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        class Writer implements Runnable {
            private final List<String> str;

            public Writer(List<String> str) {
                this.str = new ArrayList<>(str);
            }

            @Override
            public void run() {
                System.out.println(str);
            }
        }

        List<String> list = new ArrayList<>();
        list.add("Test");
        list.add("Task");
        new Thread(new Writer(list)).start();
        list.set(0, "Main");
        list.set(1, "Mask");
        new Thread(new Writer(list)).start();
        list.set(0, "Toto");
        list.set(1, "Todo");
        new Thread(new Writer(list)).start();
        list.set(0, "java");
        list.set(1, "language");
        new Thread(new Writer(list)).start();
    }

}