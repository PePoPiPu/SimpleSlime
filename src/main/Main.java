package main;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleSlime simpleSlime = new SimpleSlime();

        System.out.println("Please introduce population factor percentage: ");
        double population = sc.nextInt();
        // Initialize cells in a random configuration
        GridCell[][] grid = simpleSlime.initializePopulation(population);
        // Create GridPanel object and add it to the interface
        GridPanel gridPanel = new GridPanel(grid);
        GUI gui = new GUI(grid);

        gui.createAndShowGUI(gridPanel);

        // Create game loop at 60FPS
        javax.swing.Timer timer = new Timer(1000/60, e -> {
            // Move cells
            simpleSlime.moveCells();
            simpleSlime.updateChemoAttractor(simpleSlime.getGrid());
            // Repaint with new instance of the grid
            gridPanel.setGrid(simpleSlime.getGrid());
        });
        timer.start();

    }
}
