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
    }

    @Override
    public void onNewGame(ActionEvent event) {
        System.out.println("New game");
        for (int row = 0; row < gameModel.getRows(); row++) {
            for (int col = 0; col < gameModel.getColumns(); col++) {
                gameView.getButtons()[row][col].setEnabled(true);
            }
        }
        gameModel.setFirstOpen(true);
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
                gameView.getButtons()[row][col].setEnabled(false);
                break;
            case -1:
                gameView.getButtons()[row][col].setDisabledIcon(gameView.getExplodedMineIcon());
                for (int r = 0; r < gameModel.getRows(); r++)
                    for (int c = 0; c < gameModel.getColumns(); c++)
                        if (gameView.getButtons()[r][c].isEnabled()) {
                            if (gameModel.getMines()[r][c] != -1) {
                                gameView.getButtons()[r][c].setDisabledIcon(gameView.getUnopenedIcon());
                            }
                            gameView.getButtons()[r][c].setEnabled(false);
                        }
                break;
        }
        gameView.updateGUI();
    }

    private void openEmpty(int row, int col) {
        for (int nearRow = row - 1; nearRow <= row + 1; nearRow++) {
            for (int nearCol = col - 1; nearCol <= col + 1; nearCol++) {
                if (nearRow >= 0 && nearRow < gameModel.getRows() && nearCol >= 0 && nearCol < gameModel.getColumns()) {
                    if (gameModel.getMines()[nearRow][nearCol] != -1 && gameView.getButtons()[nearRow][nearCol].isEnabled()) {
                        gameView.getButtons()[nearRow][nearCol].setEnabled(false);
                        if (gameModel.getMines()[nearRow][nearCol] == 0) {
                            openEmpty(nearRow, nearCol);
                        }
                    }
                }
            }
        }
    }
}