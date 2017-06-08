package game;

import java.util.Random;

/**
 * game.GameModel Class
 */
public class GameModel {
    private int[][] mines;
    private int rows = 9;
    private int columns = 9;
    private int nbrBombs = 10;

    private Random rand = new Random();

    private String difficulty = "beginner";

    public String getDifficulty() {
        return difficulty;
    }

    public void generateMines() {
        mines = new int[rows][columns];

        int temp = 0;
        int randomRow;
        int randomColumn;
        while (temp < nbrBombs) {
            randomRow = rand.nextInt(rows);
            randomColumn = rand.nextInt(columns);
            if (mines[randomRow][randomColumn] != -1) {
                mines[randomColumn][randomRow] = -1;
                temp++;
            }
        }

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                if (mines[i][j] == 0)
                    for (int nearRow = i - 1; nearRow <= i + 1; nearRow++)
                        for (int nearColumn = j - 1; nearColumn <= j + 1; nearColumn++)
                            if (nearRow >= 0 && nearRow < rows && nearColumn >= 0 && nearColumn < columns && mines[nearRow][nearColumn] == -1)
                                mines[i][j]++;

    }
    
    void printMap() {
        System.out.println("This is the bomb map: ");
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (mines[i][j] == -1){
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
}