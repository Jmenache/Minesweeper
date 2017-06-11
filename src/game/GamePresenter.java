package game;

import Statistics.StatisticsView;
import options.OptionsModel;
import options.OptionsPresenter;
import options.OptionsView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * game.GamePresenter Class
 */
public class GamePresenter implements GameViewListener {
    private final OptionsModel optionsModel;
    private final GameModel gameModel;
    private final GameView gameView;

    private StatisticsView statisticsView = new StatisticsView();


    public GamePresenter(final GameView gameView, final GameModel gameModel, final OptionsModel optionsModel) {
        this.optionsModel = optionsModel;

        this.gameModel = gameModel;
        this.gameView = gameView;
        gameView.addListener(this);

        newGame();
    }

    @Override
    public void onNewGame(ActionEvent event) {
        newGame();
        for (int row = 0; row < optionsModel.getRows(); row++) {
            for (int col = 0; col < optionsModel.getCols(); col++) {
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
        OptionsView optionsView = new OptionsView();
        new OptionsPresenter(optionsView, optionsModel);

        /*optionsView.setVisible(true);
        if(optionsView.isClickedOK()) {
            optionsModel = optionsView.getOptionsModel();
            System.out.println(optionsModel.getCols() + "," + optionsModel.getRows() + "," + optionsModel.getNumberOfMines());
            gameView.updateGUI();
        }*/

        //optionsView.dispose();
    }

    @Override
    public void onExit(ActionEvent event) {
        gameView.getFrame().dispose();
        // gameView.getFrame().dispatchEvent(new WindowEvent(gameView.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void onCreateGrid(int i) {
        // gameView.initiateButtons(optionsModel.getRows(), optionsModel.getCols());
        // gameView.createGrid(optionsModel.getRows(), optionsModel.getCols());
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
                    if (optionsModel.isQuestionMarkEnabled()) {
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
                    if (nearRow >= 0 && nearRow < optionsModel.getRows() && nearCol >= 0 && nearCol < optionsModel.getCols()) {
                        if (gameModel.getButtonsState()[nearRow][nearCol] == GameModel.FLAGGED) {
                            flagCount++;
                        }
                    }

            if (gameModel.getMines()[row][col] == flagCount) {
                for (int nearRow = row - 1; nearRow <= row + 1; nearRow++)
                    for (int nearCol = col - 1; nearCol <= col + 1; nearCol++)
                        if (nearRow >= 0 && nearRow < optionsModel.getRows() && nearCol >= 0 && nearCol < optionsModel.getCols()) {
                            if (gameModel.getButtonsState()[nearRow][nearCol] != GameModel.FLAGGED && gameModel.getButtonsState()[nearRow][nearCol] != GameModel.OPENED) {
                                openSquare(nearRow, nearCol);
                            }
                        }
            }
        }
    }

    @Override
    public void onLoadGame() {

    }

    private void newGame() {
        System.out.println("New game");

        gameModel.resetButtonState(optionsModel.getRows(), optionsModel.getCols());
        gameModel.setMineCount(optionsModel.getNumberOfMines());
        gameModel.setSquareCount(optionsModel.getRows() * optionsModel.getCols());
        initiateMines(optionsModel.getRows(), optionsModel.getCols());
        gameModel.setFirstOpen(true);
    }

    private void firstOpen(int row, int col) {
        generateMines(row, col);
        Timer timer = new Timer( 1000, e -> {
            gameModel.incrementTimer();
            // gameView.updateTimer(gameModel.getTimer());
        } );
        gameView.mapDisableIcons(gameModel.getMines());
        timer.start();
        gameModel.setFirstOpen(false);
        // gameModel.printMap();
    }

    private void initiateMines(int rows, int cols) {
        gameModel.initiateMines(rows, cols);
    }

    private void generateMines(int safeRow, int safeCol) {
        gameModel.initiateMines(optionsModel.getRows(), optionsModel.getCols());

        int minesPlaced = 0;
        while (minesPlaced < optionsModel.getNumberOfMines()) {
            int randomRow = gameModel.getRand().nextInt(optionsModel.getRows());
            int randomCol = gameModel.getRand().nextInt(optionsModel.getCols());
            if (gameModel.getMines()[randomRow][randomCol] != GameModel.MINE && (randomRow != safeRow || randomCol != safeCol)) {
                gameModel.getMines()[randomRow][randomCol] = GameModel.MINE;
                minesPlaced++;
            }
        }

        for (int row = 0; row < optionsModel.getRows(); row++)
            for (int col = 0; col < optionsModel.getCols(); col++)
                if (gameModel.getMines()[row][col] == 0)
                    for (int nearRow = row - 1; nearRow <= row + 1; nearRow++)
                        for (int nearCol = col - 1; nearCol <= col + 1; nearCol++)
                            if (nearRow >= 0 && nearRow < optionsModel.getRows() && nearCol >= 0 && nearCol < optionsModel.getCols() && gameModel.getMines()[nearRow][nearCol] == GameModel.MINE)
                                gameModel.getMines()[row][col]++;

    }

    private void openSquare(int row, int col) {
        switch (gameModel.getMines()[row][col]) {
            case GameModel.EMPTY:
                gameModel.getButtonsState()[row][col] = GameModel.OPENED;
                openEmpty(row, col);
                break;
            case GameModel.MINE:
                gameView.getButtons()[row][col].setDisabledIcon(gameView.getExplodedMineIcon());
                for (int r = 0; r < optionsModel.getRows(); r++)
                    for (int c = 0; c < optionsModel.getCols(); c++)
                        if (gameView.getButtons()[r][c].isEnabled()) {
                            if (gameModel.getMines()[r][c] != GameModel.MINE) {
                                gameView.getButtons()[r][c].setDisabledIcon(gameView.getUnopenedIcon());
                            }
                            gameView.getButtons()[r][c].setEnabled(false);
                        } else if (gameModel.getButtonsState()[r][c] == GameModel.FLAGGED) {
                            gameView.getButtons()[r][c].setDisabledIcon(gameView.getCrossedMineIcon());
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
                if (nearRow >= 0 && nearRow < optionsModel.getRows() && nearCol >= 0 && nearCol < optionsModel.getCols()) {
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
        if (wasLastEmptySquare()) gameWon();

    }

    private boolean wasLastEmptySquare() {
        return gameModel.getSquareCount() - optionsModel.getNumberOfMines() == 0;
    }

    private void gameWon() {
        System.out.println("Game won");

        for (int row = 0; row < optionsModel.getRows(); row++) {
            for (int col = 0; col < optionsModel.getCols(); col++) {
                if (gameModel.getMines()[row][col] == GameModel.MINE) {
                    gameView.getButtons()[row][col].setDisabledIcon(gameView.getFlagIcon());
                    gameView.getButtons()[row][col].setEnabled(false);
                }
            }
        }
    }

    private void saveGame() {
        
    }
}