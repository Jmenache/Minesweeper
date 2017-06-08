import game.GameModel;
import game.GamePresenter;
import game.GameView;

import javax.swing.*;

public class Minesweeper {

    private Minesweeper() {
        final GameView gameView = new GameView();
        final GameModel gameModel = new GameModel();
        new GamePresenter(gameView, gameModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Minesweeper::new);
    }
}
