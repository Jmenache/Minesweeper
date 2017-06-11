package game;

import java.util.Random;

/**
 * game.GameModel Class
 */
public class GameModel {
    private int[][] mines;
    private int rows = 9;
    private int cols = 9;
    private int nbrBombs = 10;
    private int[][] buttonsState;

    static final int HIDDEN = 0;
    static final int REVEALED = 1;
    static final int FLAG = 2;
    static final int QUESTION_MARK = 3;

    static final int MINE = -1;

    private Random rand = new Random();

    private boolean firstOpen;
    private boolean questionMarkEnabled = false;
    private String difficulty = "beginner";

    void generateMines(int safeRow, int safeCol) {
        mines = new int[rows][cols];

        int temp = 0;
        while (temp < nbrBombs) {
            int randomRow = rand.nextInt(rows);
            int randomCol = rand.nextInt(cols);
            if (mines[randomRow][randomCol] != MINE && (randomRow != safeRow || randomCol != safeCol)) {
                mines[randomRow][randomCol] = MINE;
                temp++;
            }
        }

        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (mines[row][col] == 0)
                    for (int nearRow = row - 1; nearRow <= row + 1; nearRow++)
                        for (int nearCol = col - 1; nearCol <= col + 1; nearCol++)
                            if (nearRow >= 0 && nearRow < rows && nearCol >= 0 && nearCol < cols && mines[nearRow][nearCol] == MINE)
                                mines[row][col]++;

    }

    void resetButtonState(){
        buttonsState = new int[rows][cols];
    }
    
    void printMap() {
        System.out.println("This is the bomb map: ");
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (mines[i][j] == MINE){
                    System.out.printf("%5s", "*");
                } else if (mines[i][j] == 0) {
                    System.out.printf("%5s", "-");
                } else {
                    System.out.printf("%5d", mines[i][j]);
                }
            }
            System.out.println();
        }
    }

    int[][] getMines() {
        return mines;
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return cols;
    }

    public int[][] getButtonsState() {
        return buttonsState;
    }

    public String getDifficulty() {
        return difficulty;
    }

    boolean isFirstOpen() {
        return firstOpen;
    }

    public boolean isQuestionMarkEnabled() {
        return questionMarkEnabled;
    }

    void setFirstOpen(boolean firstOpen) {
        this.firstOpen = firstOpen;
    }
}