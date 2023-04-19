import java.util.*;

public class Game2048 {
    private int[][] grid;
    private Random random;
    private boolean gameOver;
    private int score;

    public Game2048() {
        grid = new int[4][4];
        random = new Random();
        gameOver = false;
        score = 0;
        addNewTile();
        addNewTile();
    }

    private void addNewTile() {
        List<int[]> emptyTiles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    emptyTiles.add(new int[]{i, j});
                }
            }
        }
        if (emptyTiles.size() > 0) {
            int[] tile = emptyTiles.get(random.nextInt(emptyTiles.size()));
            grid[tile[0]][tile[1]] = random.nextInt(2) == 0 ? 2 : 4;
        }
    }

    public void move(Direction direction) {
        int[][] newGrid = new int[4][4];
        boolean hasMoved = false;
        switch (direction) {
            case LEFT:
                for (int i = 0; i < 4; i++) {
                    int index = 0;
                    int previous = 0;
                    for (int j = 0; j < 4; j++) {
                        int current = grid[i][j];
                        if (current != 0) {
                            if (previous == 0) {
                                previous = current;
                            } else if (previous == current) {
                                newGrid[i][index] = current * 2;
                                score += current * 2;
                                index++;
                                previous = 0;
                            } else {
                                newGrid[i][index] = previous;
                                index++;
                                previous = current;
                            }
                        }
                    }
                    if (previous != 0) {
                        newGrid[i][index] = previous;
                    }
                    if (!Arrays.equals(grid[i], newGrid[i])) {
                        hasMoved = true;
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < 4; i++) {
                    int index = 3;
                    int previous = 0;
                    for (int j = 3; j >= 0; j--) {
                        int current = grid[i][j];
                        if (current != 0) {
                            if (previous == 0) {
                                previous = current;
                            } else if (previous == current) {
                                newGrid[i][index] = current * 2;
                                score += current * 2;
                                index--;
                                previous = 0;
                            } else {
                                newGrid[i][index] = previous;
                                index--;
                                previous = current;
                            }
                        }
                    }
                    if (previous != 0) {
                        newGrid[i][index] = previous;
                    }
                    if (!Arrays.equals(grid[i], newGrid[i])) {
                        hasMoved = true;
                    }
                }
                break;
            case UP:
                for (int j = 0; j < 4; j++) {
                    int index = 0;
                    int previous = 0;
                    for (int i = 0; i < 4; i++) {
                        int current = grid[i][j];
                        if (current != 0) {
                            if (previous == 0) {
                                previous = current;
                            } else if (previous == current) {
                                newGrid[index][j] = current * 2;
                                score += current * 2;
                                index++;
                                previous = 0;
                            } else {
                                newGrid[index][j] = previous;
                                index++;
                                previous = current;
                            }
                        }
                    }
                    if (previous != 0) {
                        newGrid[index][j] = previous;
                    }
                    int[] originalCol = new int[4];
                    for (int i = 0; i < 4; i++) {
                        originalCol[i] = grid[i][j];
                    }
                    int[] newCol = new int[4];
                    for (int i = 0; i < 4; i++) {
                        newCol[i] = newGrid[i][j];
                    }
                    if (!Arrays.equals(originalCol, newCol)) {
                        hasMoved = true;
                    }
                }
                break;
            case DOWN:
                for (int j = 0; j < 4; j++) {
                    int index = 3;
                    int previous = 0;
                    for (int i = 3; i >= 0; i--) {
                        int current = grid[i][j];
                        if (current != 0) {
                            if (previous == 0) {
                                previous = current;
                            } else if (previous == current) {
                                newGrid[index][j] = current * 2;
                                score += current * 2;
                                index--;
                                previous = 0;
                            } else {
                                newGrid[index][j] = previous;
                                index--;
                                previous = current;
                            }
                        }
                    }
                    if (previous != 0) {
                        newGrid[index][j] = previous;
                    }
                    int[] originalCol = new int[4];
                    for (int i = 0; i < 4; i++) {
                        originalCol[i] = grid[i][j];
                    }
                    int[] newCol = new int[4];
                    for (int i = 0; i < 4; i++) {
                        newCol[i] = newGrid[i][j];
                    }
                    if (!Arrays.equals(originalCol, newCol)) {
                        hasMoved = true;
                    }
                }
                break;
        }
        if (hasMoved) {
            grid = newGrid;
            addNewTile();
            gameOver = isGameOver();
        }


    }

    private boolean isGameOver() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
                if (i > 0 && grid[i][j] == grid[i-1][j]) {
                    return false;
                }
                if (j > 0 && grid[i][j] == grid[i][j-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOverPublic() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public int getValueAt(int row, int col) {
        return grid[row][col];
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN
    }

}

