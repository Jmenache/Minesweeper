package game;

import java.awt.event.ActionEvent;

/**
 * game.GamePresenter Class
 */
public class GamePresenter implements GameViewListener {
    private final GameView gameView;
    private final GameModel gameModel;


    public GamePresenter(final GameView gameView, final GameModel gameModel) {
        this.gameView = gameView;
        gameView.addListener(this);
        this.gameModel = gameModel;
    }

    @Override
    public void onNewGame(ActionEvent event) {
        System.out.println("New game");
        // gameModel.generateMines();
        // gameModel.printMap();

        // gameView.getButtons()[0][0].setVisible(false);

        // gameView.getGridPane().remove(0);
        // gameView.getGridPane().remove(gameView.getButtons()[0][0]);
        // gameView.getButtons()[0][0].removeAll();

        // gameView.addToGrid(0, 0, gameView.getThreeLabel());
    }

    @Override
    public void onExit(ActionEvent event) {
        gameView.getFrame().dispose();
        //gameView.getFrame().dispatchEvent(new WindowEvent(gameView.getFrame(), WindowEvent.WINDOW_CLOSING));
    }
}