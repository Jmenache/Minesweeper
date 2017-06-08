package game;

import java.awt.event.ActionEvent;

/**
 * game.GameViewListener Interface
 */
public interface GameViewListener {
    void onNewGame(ActionEvent event);
    void onExit(ActionEvent event);
}