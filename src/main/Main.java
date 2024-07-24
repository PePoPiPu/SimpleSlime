package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleSlime simpleSlime = new SimpleSlime();

        System.out.println("Please introduce population factor percentage: ");
        double population = sc.nextInt();

        // Initialize grid with random alive cells
        simpleSlime.initializePopulation(population);


    }
}
