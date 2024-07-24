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
                    // Make sure Agent's F, FR and FL sensors don't get out of bounds off the grid
                    boolean outOfBounds = false;
                    int direction = rand.nextInt(0, 7);

                    outOfBounds = checkOutOfBounds(direction, i, j);

                    if(!outOfBounds) {
                        grid[i][j] = new Agent(true, direction);
                        popCont++;
                    }
                }
            }
        }

        return grid;
    }

    private boolean checkOutOfBounds (int direction, int i, int j) {
        Agent agent = new Agent(true, direction);
        boolean outOfBounds = false;
        int sensorOffset = agent.getSensorOffsetDistance();
        if((i + sensorOffset > grid.length - 2 || j + sensorOffset > grid[0].length - 2) || (i - sensorOffset < 1 || j - sensorOffset < 1)) {
            outOfBounds = true;
        }
        return outOfBounds;
    }

    public void moveCells() {

        Random rand = new Random();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // Decide if current Agent is motor or sensory stage
                boolean isMotor = rand.nextBoolean();
                boolean outOfBounds;
                    // Check if current GridCell is an instance of an Agent object
                    if (grid[i][j] instanceof Agent && isMotor) {
                        int direction = ((Agent) grid[i][j]).getDirection();
                        // Move in facing direction
                        switch (direction) {
                            // Top left heading
                            case 0:
                                // Check if movement gets the cell sensors out of bounds
                                outOfBounds = checkOutOfBounds(direction, i, j);

                                // If position is free (The cell is not an intance of Agent class)
                                if(!(grid[i - 1][j - 1] instanceof Agent) && !outOfBounds) {
                                    // Set current position to ChemoAttractor instance
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    // Set coordinates to Agent instance, keep direction heading
                                    grid[i - 1][j - 1] = new Agent(true, direction);
                                } else {
                                    // If position isn't free, set a random new direction
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Top heading
                            case 1:
                                outOfBounds = checkOutOfBounds(direction, i, j);

                                if(!(grid[i - 1][j] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i - 1][j] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Top right heading
                            case 2:
                                outOfBounds = checkOutOfBounds(direction, i, j);

                                if(!(grid[i - 1][j + 1] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i - 1][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Right heading
                            case 3:
                                outOfBounds = checkOutOfBounds(direction, i, j);
                                if(!(grid[i][j + 1] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Lower right heading
                            case 4:
                                outOfBounds = checkOutOfBounds(direction, i, j);
                                if(!(grid[i + 1][j + 1] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i + 1][j + 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Bottom heading
                            case 5:
                                outOfBounds = checkOutOfBounds(direction, i, j);
                                if(!(grid[i + 1][j] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i + 1][j] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Lower left heading
                            case 6:
                                outOfBounds = checkOutOfBounds(direction, i, j);
                                if(!(grid[i + 1][j - 1] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    depositChemoAttractor(grid, i, j, (ChemoAttractor) grid[i][j]);
                                    grid[i + 1][j - 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                                // Left heading
                            case 7:
                                outOfBounds = checkOutOfBounds(direction, i, j);
                                if(!(grid[i][j - 1] instanceof Agent) && !outOfBounds) {
                                    grid[i][j] = new ChemoAttractor(9);
                                    grid[i][j - 1] = new Agent(true, direction);
                                } else {
                                    ((Agent) grid[i][j]).setDirection(rand.nextInt(0, 7));
                                }
                                break;
                        }
                    } else {// Sensory stage
                        if (grid[i][j] instanceof Agent && !isMotor) {
                            checkForChemoAttractor(grid, (Agent) grid[i][j], i, j);
                        }
                    }
            }
        }
    }

    private void depositChemoAttractor(GridCell[][] grid, int i, int j, ChemoAttractor chemoAttractor) {

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
    }

    private void checkForChemoAttractor(GridCell[][] grid, Agent agent, int i, int j) {
        int offset = agent.getSensorOffsetDistance();
        int direction = agent.getDirection();

        switch (direction) {
            case 0:
                // Check sensors for ChemoAttractor and store its values
                // Sensor should be at x, y coordinates depending on direction heading
                if (grid[i + offset][j - offset] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i + offset][j - offset]).getStrength());
                } else if (grid[i + offset][j] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i + offset][j]).getStrength());
                } else if (grid[i][j - offset] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i][j - offset]).getStrength());
                }
                break;
            case 1:
                if (grid[i + offset][j] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i + offset][j]).getStrength());
                } else if (grid[i + offset][j + offset] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i + offset][j + offset]).getStrength());
                } else if (grid[i + offset][j - offset] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i + offset][j - offset]).getStrength());
                }
                break;
            case 2:
                if (grid[i + offset][j + offset] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i + offset][j + offset]).getStrength());
                } else if (grid[i][j + offset] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i][j + offset]).getStrength());
                } else if (grid[i + offset][j] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i + offset][j]).getStrength());
                }
                break;
            case 3:
                if (grid[i][j + offset] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i][j + offset]).getStrength());
                } else if (grid[i + offset][j + offset] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i + offset][j + offset]).getStrength());
                } else if (grid[i - offset][j + offset] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i - offset][j + offset]).getStrength());
                }
                break;
            case 4:
                if (grid[i - offset][j + offset] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i - offset][j + offset]).getStrength());
                } else if (grid[i - offset][j] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i - offset][j]).getStrength());
                } else if (grid[i][j + offset] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i][j + offset]).getStrength());
                }
                break;
            case 5:
                if (grid[i - offset][j] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i - offset][j]).getStrength());
                } else if (grid[i - offset][j - offset] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i - offset][j - offset]).getStrength());
                } else if (grid[i - offset][j + offset] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i - offset][j + offset]).getStrength());
                }
                break;
            case 6:
                if (grid[i - offset][j - offset] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i - offset][j - offset]).getStrength());
                } else if (grid[i][j - offset] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i][j - offset]).getStrength());
                } else if (grid[i - offset][j] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i - offset][j]).getStrength());
                }
                break;
            case 7:
                if (grid[i][j - offset] instanceof ChemoAttractor) {
                    agent.setFwS(((ChemoAttractor) grid[i][j - offset]).getStrength());
                } else if (grid[i + offset][j - offset] instanceof ChemoAttractor) {
                    agent.setFwRS(((ChemoAttractor) grid[i + offset][j - offset]).getStrength());
                } else if (grid[i - offset][j - offset] instanceof ChemoAttractor) {
                    agent.setFwLS(((ChemoAttractor) grid[i - offset][j - offset]).getStrength());
                }
                break;
        }


       moveTowardsChemoAttractor(grid, agent, i, j);
    }

    private void moveTowardsChemoAttractor(GridCell[][] grid, Agent agent, int i, int j) {
        // Change direction depending on values
        if (agent.getFwS() > agent.getFwLS() && agent.getFwS() > agent.getFwRS()) {
            // Face same direction
            agent.setDirection(agent.getDirection());
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
    }

    public void updateChemoAttractor(GridCell[][] grid) {

        // Check grid for instance of chemoattractor
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] instanceof ChemoAttractor) {
                    double strength = ((ChemoAttractor) grid[i][j]).getStrength();
                    double decayFactor = ((ChemoAttractor) grid[i][j]).getDecayFactor();
                    // Lower chemoAttractor strength by decay factor.
                    if (strength > ((ChemoAttractor) grid[i][j]).getMIN_STRENGTH() + 1){
                        ((ChemoAttractor) grid[i][j]).setStrength(strength / decayFactor);
                    } else {
                        // If strength is less than the minimum strength, the chemoAttractor disappears
                        grid[i][j] = null;
                    }
                }
            }
        }
    }
}
