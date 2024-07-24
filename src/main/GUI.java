package main;

import javax.swing.*;

public class GUI {
    private GridCell[][] grid;

    public GUI(GridCell[][] grid) {
        this.grid = grid;
    }

    public void createAndShowGUI (GridPanel gridPanel) {
        JFrame frame = new JFrame("SimpleSlime");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gridPanel);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
