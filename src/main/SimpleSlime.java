package main;

import java.util.Random;

public class SimpleSlime {
    private GridCell[][] grid = new GridCell[200][200];
    private double population; // Population percentage of image area

    public SimpleSlime() {

    }

    public GridCell[][] getGrid() {
        return grid;
    }

    public GridCell[][] initializePopulation(double populationPercentage) {

        Random rand = new Random();
        int popCont = 0;
        // Get number of cells to initialize
        population = populationPercentage / 100 * (Math.pow(200, 2));

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Randomize cell initialization
                if(rand.nextInt(0, 100) < 2 && popCont <= population) {
                    // Set current cell to be an Agent, Agent state to active and Agent direction
                    grid[i][j] = new Agent(true, rand.nextInt(0, 7));
                    popCont++;
                }
            }
        }

        return grid;
    }


    public GridCell[][] moveCells() {

        Random rand = new Random();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Decide if current Agent is motor or sensory stage
                boolean isMotor = rand.nextBoolean();

                if (isMotor){
                    // Check if current GridCell is an instance of an Agent object
                    if (grid[i][j] instanceof Agent) {
                        int direction = ((Agent) grid[i][j]).getDirection();
                        // Move in facing direction
                        switch (direction) {
                            // Top left heading
                            case 0:
                                // If position is free (The cell is not an intance of Agent class)
                                if(!(grid[i - 1][j - 1] instanceof Agent)) {
                                    // Set current position to ChemoAttractor instance
                                    grid[i][j] = new ChemoAttractor();
                                    // Set coordinates to Agent instance, keep direction heading
                                    grid[i - 1][j - 1] = new Agent(true, direction);
                                } else {
                                    // If position isn't free, set a random new direction
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Top heading
                            case 1:
                                if(!(grid[i - 1][j] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i - 1][j] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Top right heading
                            case 2:
                                if(!(grid[i - 1][j + 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i - 1][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Right heading
                            case 3:
                                if(!(grid[i][j + 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Lower right heading
                            case 4:
                                if(!(grid[i + 1][j + 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i + 1][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Bottom heading
                            case 5:
                                if(!(grid[i + 1][j] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i + 1][j] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Lower left heading
                            case 6:
                                if(!(grid[i + 1][j - 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i + 1][j - 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Left heading
                            case 7:
                                if(!(grid[i][j - 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor();
                                    grid[i][j - 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                        }
                    }
                } else { // Sensory stage
                    // Check if any ChemoAttractors are detected


                }

            }
        }
        return grid;
    }

}
