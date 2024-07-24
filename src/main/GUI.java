package main;

import javax.swing.*;

public class GUI {
    private boolean[][] grid;

    public GUI(boolean[][] grid) {
        this.grid = grid;
    }

    public void createAndShowGUI (GridPanel gridPanel) {
        JFrame frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gridPanel);

        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
