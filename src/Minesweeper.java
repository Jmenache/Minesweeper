import game.GameModel;
import game.GamePresenter;
import game.GameView;
import options.OptionsModel;
import options.OptionsView;

import javax.swing.*;

public class Minesweeper {

    private Minesweeper() {
        final OptionsModel optionsModel = new OptionsModel();

        final GameView gameView = new GameView();
        final GameModel gameModel = new GameModel();
        new GamePresenter(gameView, gameModel, optionsModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Minesweeper::new);
    }
}
