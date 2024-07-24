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
                    // Check if current GridCell is an instance of an Agent object
                    if (grid[i][j] instanceof Agent && isMotor) {
                        int direction = ((Agent) grid[i][j]).getDirection();
                        // Move in facing direction
                        switch (direction) {
                            // Top left heading
                            case 0:
                                // If position is free (The cell is not an intance of Agent class)
                                if(!(grid[i - 1][j - 1] instanceof Agent)) {
                                    // Set current position to ChemoAttractor instance
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    // Set coordinates to Agent instance, keep direction heading
                                    grid[i - 1][j - 1] = new Agent(true, direction);
                                } else {
                                    // If position isn't free, set a random new direction
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Top heading
                            case 1:
                                if(!(grid[i - 1][j] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i - 1][j] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Top right heading
                            case 2:
                                if(!(grid[i - 1][j + 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i - 1][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Right heading
                            case 3:
                                if(!(grid[i][j + 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Lower right heading
                            case 4:
                                if(!(grid[i + 1][j + 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i + 1][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Bottom heading
                            case 5:
                                if(!(grid[i + 1][j] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i + 1][j] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Lower left heading
                            case 6:
                                if(!(grid[i + 1][j - 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i + 1][j - 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                // Left heading
                            case 7:
                                if(!(grid[i][j - 1] instanceof Agent)) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    grid[i][j - 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                        }
                    } else { // Sensory stage
                        checkForChemoAttractor(grid, (Agent) grid[i][j], i, j);
                    }
            }
        }
        return grid;
    }

    public GridCell[][] depositChemoAttractor(GridCell[][] grid, int i, int j, ChemoAttractor chemoAttractor) {

        // Determine number of rows and columns in the GridCell
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Define the bound of the ChemoAttractor strength area based of its strengthRadius
        int radius = chemoAttractor.getStrengthRadius();

        int rowStart = Math.max(0, i - radius);
        int rowEnd = Math.min(numRows, i + radius + 1); // +1 to include the endpoint
        int colStart = Math.max(0, j - radius);
        int colEnd = Math.min(numCols, j + radius + 1);

        // Iterate over the area
        for (int row = rowStart; row < rowEnd; row++) {
            for (int col = colStart; col < colEnd; col++) {
                // Check if current cell is an Agent or not to avoid deleting agents
                if (!(grid[row][col] instanceof Agent)) {
                    Random rand = new Random();
                    // Generate strength value for chemoAttractor
                    double strength = rand.nextDouble(chemoAttractor.getMIN_STRENGTH(), chemoAttractor.getMAX_STRENGTH());
                    // Assign that value to current cell
                    grid[row][col] = new ChemoAttractor(strength);
                }
            }
        }
        return grid;
    }

    public Agent checkForChemoAttractor(GridCell[][] grid, Agent agent, int i, int j) {
        int offset = agent.getSensorOffsetDistance();

        // Check sensors for ChemoAttractor and store its values
        if (grid[i - offset][j] instanceof ChemoAttractor) {
            agent.setFwS(((ChemoAttractor) grid[i - offset][j]).getStrength());
        } else if (grid[i - offset][j + offset] instanceof ChemoAttractor) {
            agent.setFwRS(((ChemoAttractor) grid[i - offset][j + offset]).getStrength());
        } else if (grid[i - offset][j - offset] instanceof ChemoAttractor) {
            agent.setFwLS(((ChemoAttractor) grid[i - offset][j - offset]).getStrength());
        }

        // Change direction depending on values
        if (agent.getFwS() > agent.getFwLS() && agent.getFwS() > agent.getFwRS()) {
            // Face same direction
            agent.setDirection(agent.getDirection());
            return agent;
        } else if (agent.getFwS() < agent.getFwLS() && agent.getFwS() < agent.getFwRS()) {
            // Rotate randomly left or right
            Random rand = new Random();
            if(rand.nextBoolean()) {
                // Check direction is not out of bounds
                if (agent.getDirection() + 1 > 7) {
                    agent.setDirection(0);
                } else {
                    agent.setDirection(agent.getDirection() + 1);
                }
            } else {
                if (agent.getDirection() - 1 < 0) {
                    agent.setDirection(7);
                } else {
                    agent.setDirection(agent.getDirection() - 1);
                }
            }
        } else if (agent.getFwLS() < agent.getFwRS()) {
            agent.setDirection(agent.getDirection() + 1);
        } else if (agent.getFwRS() < agent.getFwLS()) {
            agent.setDirection(agent.getDirection() - 1);
        } else {
            agent.setDirection(agent.getDirection());
        }
        return agent;
    }

}
