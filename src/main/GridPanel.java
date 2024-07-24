package main;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private GridCell[][] cellGrid;
    private final int cellSize = 1;

    public GridPanel(GridCell[][] cellGrid) {
        this.cellGrid = cellGrid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g, cellGrid);
    }

    private void drawGrid(Graphics g, GridCell[][] cellGrid) {

        for (int row = 0; row < cellGrid.length; row++) {

            for (int col = 0; col < cellGrid[row].length; col++) {

                if (cellGrid[row][col] instanceof Agent && ((Agent) cellGrid[row][col]).getState()) {
                    g.setColor(Color.WHITE); // Alive cell
                } else {
                    g.setColor(Color.BLACK); // Dead cell
                }

                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    public void setGrid(GridCell[][] cellGrid) {
        this.cellGrid = cellGrid;
        repaint(); // Request a repaint to update the display
    }
}
