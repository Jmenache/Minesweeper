package game;

import java.awt.event.ActionEvent;

/**
 * game.GameViewListener Interface
 */
public interface GameViewListener{
    // Menu
    void onNewGame(ActionEvent event);
    // TODO: Should clarify names of onStatistics and onOptions
    void onStatistics(ActionEvent event);
    void onOptions(ActionEvent event);
    void onSaveGame(ActionEvent event);
    void onLoadGame(ActionEvent event);
    void onExit(ActionEvent event);

    // Game
    void onCreateGrid(int i);
    void onOpenSquare(int[] coordinates);
    void onTimerStart(ActionEvent actionEvent);
    void onRightClick(int[] coordinates);
    void onMiddleClick(int[] coordinates);
}