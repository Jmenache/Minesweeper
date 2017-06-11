package game;

import java.awt.event.ActionEvent;

/**
 * game.GameViewListener Interface
 */
public interface GameViewListener{
    // Menu
    void onNewGame(ActionEvent event);
    void onOptions(ActionEvent event);
    void onStatistics(ActionEvent event);
    void onExit(ActionEvent event);

    // Game
    void onCreateGrid(int i);
    void onOpenSquare(int[] coordinates);
    void onRightClick(int[] coordinates);
    void onMiddleClick(int[] coordinates);

    // Other
    void onLoadGame();
}