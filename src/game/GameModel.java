package game;

import javax.swing.*;
import java.util.Random;

/**
 * game.GameModel Class
 */
public class GameModel {
    private Random rand = new Random();

    private int[][] mines;

    private int[][] buttonsState;
    static final int CLOSED = 0;
    static final int OPENED = 1;
    static final int FLAGGED = 2;
    static final int QUESTION_MARKED = 3;

    private int seconds = 0;

    private int squareCount;
    private int mineCount;
    private boolean firstOpen;

    static final int EMPTY = 0;
    static final int MINE = -1;

    Random getRand() {
        return rand;
    }

    void initiateMines(int rows, int cols) {
        mines = new int[rows][cols];
    }

    void resetButtonState(int rows, int cols){
        buttonsState = new int[rows][cols];
    }
    
    // void printMap() {
    //     System.out.println("This is the bomb map: ");
    //     for(int i = 0; i < rows; i++) {
    //         for(int j = 0; j < cols; j++) {
    //             if (mines[i][j] == MINE){
    //                 System.out.printf("%5s", "*");
    //             } else if (mines[i][j] == 0) {
    //                 System.out.printf("%5s", "-");
    //             } else {
    //                 System.out.printf("%5d", mines[i][j]);
    //             }
    //         }
    //         System.out.println();
    //     }
    // }

    int[][] getMines() {
        return mines;
    }

    int[][] getButtonsState() {
        return buttonsState;
    }

    int getSeconds() {
        return seconds;
    }

    void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    void incrementTimer() {
        seconds++;
    }

    void incrementMineCount() {
        mineCount++;
    }

    void decrementMineCount() {
        mineCount--;
    }

    void decrementSquareCount() {
        squareCount--;
    }

    int getSquareCount() {
        return squareCount;
    }

    void setSquareCount(int squareCount) {
        this.squareCount = squareCount;
    }

    int getMineCount() {
        return mineCount;
    }

    void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    boolean isFirstOpen() {
        return firstOpen;
    }

    void setFirstOpen(boolean firstOpen) {
        this.firstOpen = firstOpen;
    }
}