package ru.nsu.romanov;

import ru.nsu.romanov.company.Company;
import ru.nsu.romanov.company.Founder;

public class Main {
    public static void main(String[] args) {
        new Founder(new Company(3)).start();
    }
}