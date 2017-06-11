package game;

import options.OptionsModel;
import options.OptionsView;

import java.awt.event.ActionEvent;

/**
 * game.GamePresenter Class
 */
public class GamePresenter implements GameViewListener {
    private final GameView gameView;
    private final GameModel gameModel;

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
            for (int col = 0; col < gameModel.getColumns(); col++) {
                gameView.getButtons()[row][col].setEnabled(true);
            }
        }

        gameView.updateGUI();
    }

    @Override
    public void onOptions(ActionEvent event) {
        optionsView.setVisible(true);
        if(optionsView.isClickedOK()){
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
        if (gameModel.getButtonsState()[row][col] != GameModel.REVEALED) {
            gameModel.getButtonsState()[row][col] = GameModel.REVEALED;
            if(gameModel.isFirstOpen()) {
                gameModel.generateMines(row, col);
                gameView.mapIcons(gameModel.getMines());
                gameModel.setFirstOpen(false);
                // gameModel.printMap();
            }
            switch (gameModel.getMines()[row][col]) {
                case 0:
                    openEmpty(row, col);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    revealButton(row, col);
                    break;
                case GameModel.MINE:
                    gameView.getButtons()[row][col].setDisabledIcon(gameView.getExplodedMineIcon());
                    for (int r = 0; r < gameModel.getRows(); r++)
                        for (int c = 0; c < gameModel.getColumns(); c++)
                            if (gameView.getButtons()[r][c].isEnabled()) {
                                if (gameModel.getMines()[r][c] != GameModel.MINE) {
                                    gameView.getButtons()[r][c].setDisabledIcon(gameView.getUnopenedIcon());
                                }
                                gameView.getButtons()[r][c].setEnabled(false);
                            }
                    break;
            }
            gameView.updateGUI();
        }

    }

    @Override
    public void onRightClick(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        if (gameModel.getButtonsState()[row][col] != GameModel.REVEALED) {
            switch (gameModel.getButtonsState()[row][col]) {
                case GameModel.HIDDEN:
                    gameView.getButtons()[row][col].setDisabledIcon(gameView.getFlagIcon());
                    gameView.getButtons()[row][col].setEnabled(false);
                    gameModel.getButtonsState()[row][col] = GameModel.FLAG;
                    break;
                case GameModel.FLAG:
                    if (gameModel.isQuestionMarkEnabled()) {
                        gameView.getButtons()[row][col].setDisabledIcon(gameView.getQuestionMarkIcon());
                        gameModel.getButtonsState()[row][col] = GameModel.QUESTION_MARK;
                    } else {
                        gameView.getButtons()[row][col].setDisabledIcon(gameView.getUnopenedIcon());
                        gameView.getButtons()[row][col].setEnabled(true);
                        gameModel.getButtonsState()[row][col] = GameModel.HIDDEN;
                    }
                    break;
                case GameModel.QUESTION_MARK:
                    gameView.getButtons()[row][col].setDisabledIcon(gameView.getUnopenedIcon());
                    gameView.getButtons()[row][col].setEnabled(true);
                    gameModel.getButtonsState()[row][col] = GameModel.HIDDEN;
                    break;
            }
        }
    }

    @Override
    public void onMiddleClick(int[] coordinates) {
        System.out.println("Middle Click");
        int row = coordinates[0];
        int col = coordinates[1];
    }

    private void newGame() {
        System.out.println("New game");

        gameModel.resetButtonState();
        gameModel.setFirstOpen(true);
    }

    private void openEmpty(int row, int col) {
        for (int nearRow = row - 1; nearRow <= row + 1; nearRow++) {
            for (int nearCol = col - 1; nearCol <= col + 1; nearCol++) {
                if (nearRow >= 0 && nearRow < gameModel.getRows() && nearCol >= 0 && nearCol < gameModel.getColumns()) {
                    if (gameModel.getMines()[nearRow][nearCol] != -1 && gameView.getButtons()[nearRow][nearCol].isEnabled()) {
                        revealButton(nearRow, nearCol);
                        if (gameModel.getMines()[nearRow][nearCol] == 0) {
                            openEmpty(nearRow, nearCol);
                        }
                    }
                }
            }
        }
    }

    private void revealButton(int row, int col) {
        gameModel.getButtonsState()[row][col] = GameModel.REVEALED;
        gameView.getButtons()[row][col].setEnabled(false);
    }
}