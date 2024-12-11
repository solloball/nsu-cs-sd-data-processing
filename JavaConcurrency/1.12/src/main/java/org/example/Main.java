package org.example;

import java.util.Scanner;

public class Main {
    static private Node head = new Node();
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    return;
                }
                System.out.println("Start sort");
                head = head.bubbleSort();
                System.out.println("Stop sort");
            }
        }).start();

    Scanner in = new Scanner(System.in);

    while(in.hasNextLine()) {
        String next = in.nextLine();
        if (next.compareTo("") == 0) {
            head.print();
        } else {
            head = head.add(next);
        }
    }
    }
}