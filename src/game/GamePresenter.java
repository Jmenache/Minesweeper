package game;

import options.OptionsModel;
import options.OptionsView;

import javax.swing.text.View;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        // gameModel.generateMines();
        // gameModel.printMap();

        // gameView.getButtons()[0][0].setVisible(false);

        // gameView.getGridPane().remove(0);
        // gameView.getGridPane().remove(gameView.getButtons()[0][0]);
        // gameView.getButtons()[0][0].removeAll();

        // gameView.addToGrid(0, 0, gameView.getThreeLabel());
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
        //gameView.getFrame().dispatchEvent(new WindowEvent(gameView.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void onOpenSquare(int[] coordinates) {
        System.out.println("Open Square");
        gameView.getButtons()[coordinates[0]][coordinates[1]].setIcon(gameView.getThreeIcon());
        gameView.updateGUI();
    }
}