package main;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private boolean[][] cellGrid;
    private final int cellSize = 1;

    public GridPanel(boolean[][] cellGrid) {
        this.cellGrid = cellGrid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g, cellGrid);
    }

    private void drawGrid(Graphics g, boolean[][] cellGrid) {

        for (int row = 0; row < cellGrid.length; row++) {

            for (int col = 0; col < cellGrid[row].length; col++) {

                if (cellGrid[row][col]) {
                    g.setColor(Color.WHITE); // Alive cell
                } else {
                    g.setColor(Color.BLACK); // Dead cell
                }

                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY); // Cell border
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    public void setGrid(boolean[][] cellGrid) {
        this.cellGrid = cellGrid;
        repaint(); // Request a repaint to update the display
    }
}
