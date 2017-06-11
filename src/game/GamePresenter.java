package game;

import Statistics.StatisticsModel;
import Statistics.StatisticsView;
import options.OptionsModel;
import options.OptionsView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * game.GamePresenter Class
 */
public class GamePresenter implements GameViewListener {
    private final GameView gameView;
    private final GameModel gameModel;

    private StatisticsView statisticsView = new StatisticsView();

    private OptionsView optionsView = new OptionsView();
    private OptionsModel optionsModel;


    public GamePresenter(final GameView gameView, final GameModel gameModel) {
        this.gameView = gameView;
        gameView.addListener(this);
        this.gameModel = gameModel;

        newGame();
    }

    @Override
    public void onNewGame(ActionEvent event) {
        newGame();
        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int col = 0; col < gameModel.getCols(); col++) {
                gameView.getButtons()[row][col].setIcon(gameView.getUnopenedIcon());
                gameView.getButtons()[row][col].setEnabled(true);
            }
        }

        gameView.updateGUI();
    }

    @Override
    public void onStatistics(ActionEvent event) {
        statisticsView.setVisible(true);
    }

    @Override
    public void onOptions(ActionEvent event) {
        optionsView.setVisible(true);
        if(optionsView.isClickedOK()) {
            optionsModel = optionsView.getOptionsModel();
            System.out.println(optionsModel.getWidth() + "," + optionsModel.getHeight() + "," + optionsModel.getNumOfMines());
            gameView.updateGUI();
        }

        //optionsView.dispose();
    }

    @Override
    public void onExit(ActionEvent event) {
        gameView.getFrame().dispose();
        // gameView.getFrame().dispatchEvent(new WindowEvent(gameView.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void onOpenSquare(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];

        if (gameModel.getButtonsState()[row][col] != GameModel.FLAGGED) {
            if(gameModel.isFirstOpen()) firstOpen(row, col);
            openSquare(row, col);
        }
    }

    @Override
    public void onRightClick(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];

        if (gameModel.getButtonsState()[row][col] != GameModel.OPENED) {
            switch (gameModel.getButtonsState()[row][col]) {
                case GameModel.CLOSED:
                    gameView.getButtons()[row][col].setDisabledIcon(gameView.getFlagIcon());
                    gameView.getButtons()[row][col].setEnabled(false);
                    gameModel.getButtonsState()[row][col] = GameModel.FLAGGED;
                    gameModel.decrementMineCount();
                    break;
                case GameModel.FLAGGED:
                    gameModel.incrementMineCount();
                    gameView.mapDisableIcon(gameModel.getMines(), row, col);
                    gameView.getButtons()[row][col].setEnabled(true);
                    if (gameModel.isQuestionMarkEnabled()) {
                        gameView.getButtons()[row][col].setIcon(gameView.getQuestionMarkIcon());
                        gameModel.getButtonsState()[row][col] = GameModel.QUESTION_MARKED;
                    } else {
                        gameView.getButtons()[row][col].setIcon(gameView.getUnopenedIcon());
                        gameModel.getButtonsState()[row][col] = GameModel.CLOSED;
                    }
                    break;
                case GameModel.QUESTION_MARKED:
                    gameView.getButtons()[row][col].setIcon(gameView.getUnopenedIcon());
                    gameModel.getButtonsState()[row][col] = GameModel.CLOSED;
                    break;
            }
            gameView.updateGUI();
        }
    }

    @Override
    public void onMiddleClick(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];

        if (gameModel.getButtonsState()[row][col] == GameModel.OPENED) {
            int flagCount = 0;
            for (int nearRow = row - 1; nearRow <= row + 1; nearRow++)
                for (int nearCol = col - 1; nearCol <= col + 1; nearCol++)
                    if (nearRow >= 0 && nearRow < gameModel.getRows() && nearCol >= 0 && nearCol < gameModel.getCols()) {
                        if (gameModel.getButtonsState()[nearRow][nearCol] == GameModel.FLAGGED) {
                            flagCount++;
                        }
                    }

            if (gameModel.getMines()[row][col] == flagCount) {
                for (int nearRow = row - 1; nearRow <= row + 1; nearRow++)
                    for (int nearCol = col - 1; nearCol <= col + 1; nearCol++)
                        if (nearRow >= 0 && nearRow < gameModel.getRows() && nearCol >= 0 && nearCol < gameModel.getCols()) {
                            if (gameModel.getButtonsState()[nearRow][nearCol] != GameModel.FLAGGED && gameModel.getButtonsState()[nearRow][nearCol] != GameModel.OPENED) {
                                openSquare(nearRow, nearCol);
                            }
                        }
            }
        }
    }

    private void newGame() {
        System.out.println("New game");

        gameModel.resetButtonState();
        gameModel.setMineCount(gameModel.getNbrBombs());
        gameModel.setSquareCount(gameModel.getRows() * gameModel.getCols());
        gameModel.generateMines();
        gameModel.setFirstOpen(true);
    }

    private void firstOpen(int row, int col) {
        gameModel.generateMines(row, col);
        Timer timer = new Timer( 1000, e -> {
            gameModel.incrementTimer();
            // gameView.updateTimer(gameModel.getTimer());
        } );
        gameView.mapDisableIcons(gameModel.getMines());
        timer.start();
        gameModel.setFirstOpen(false);
        // gameModel.printMap();
    }

    private void openSquare(int row, int col) {
        switch (gameModel.getMines()[row][col]) {
            case GameModel.EMPTY:
                gameModel.getButtonsState()[row][col] = GameModel.OPENED;
                openEmpty(row, col);
                break;
            case GameModel.MINE:
                gameView.getButtons()[row][col].setDisabledIcon(gameView.getExplodedMineIcon());
                for (int r = 0; r < gameModel.getRows(); r++)
                    for (int c = 0; c < gameModel.getCols(); c++)
                        if (gameView.getButtons()[r][c].isEnabled()) {
                            if (gameModel.getMines()[r][c] != GameModel.MINE) {
                                gameView.getButtons()[r][c].setDisabledIcon(gameView.getUnopenedIcon());
                            }
                            gameView.getButtons()[r][c].setEnabled(false);
                        }
                break;
            default:
                open(row, col);
                break;
        }
        gameView.updateGUI();
    }

    private void openEmpty(int row, int col) {
        for (int nearRow = row - 1; nearRow <= row + 1; nearRow++) {
            for (int nearCol = col - 1; nearCol <= col + 1; nearCol++) {
                if (nearRow >= 0 && nearRow < gameModel.getRows() && nearCol >= 0 && nearCol < gameModel.getCols()) {
                    if (gameModel.getMines()[nearRow][nearCol] != GameModel.MINE)
                        if (gameView.getButtons()[nearRow][nearCol].isEnabled())
                            if (gameModel.getButtonsState()[nearRow][nearCol] != GameModel.FLAGGED) {
                                open(nearRow, nearCol);
                                if (gameModel.getMines()[nearRow][nearCol] == GameModel.EMPTY) {
                                    openEmpty(nearRow, nearCol);
                                }
                            }
                }
            }
        }
    }

    private void open(int row, int col) {
        gameView.getButtons()[row][col].setEnabled(false);
        gameModel.getButtonsState()[row][col] = GameModel.OPENED;
        gameModel.decrementSquareCount();
        if (gameModel.wasLastEmptySquare()) gameWon();

    }

    private void gameWon() {
        System.out.println("Game won");

        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int col = 0; col < gameModel.getCols(); col++) {
                if (gameModel.getMines()[row][col] == GameModel.MINE) {
                    gameView.getButtons()[row][col].setDisabledIcon(gameView.getFlagIcon());
                    gameView.getButtons()[row][col].setEnabled(false);
                }
            }
        }
    }
}