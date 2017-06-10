package game;

import java.awt.event.ActionEvent;

/**
 * game.GameViewListener Interface
 */
public interface GameViewListener {
    // Menu
    void onNewGame(ActionEvent event);
    void onOptions(ActionEvent event);
    void onExit(ActionEvent event);

    // Game
    void onOpenSquare(int[] coordinates);
}