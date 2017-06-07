package MenuSet;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Presenter Class
 */
public class Presenter implements ViewListener {
    private final View view;
    private final Model model;


    Presenter(final View view, final Model model) {
        this.view = view;
        view.addListener(this);
        this.model = model;
    }

    @Override
    public void onNewGame(ActionEvent event) {
        System.out.println("New Game");
        // model.generateMines();
        // model.printMap();

        view.getButtons()[0][0].setVisible(false);
        // view.getGridPane().remove(0);
        // view.getGridPane().remove(view.getButtons()[0][0]);
        // view.getButtons()[0][0].removeAll();
        view.addToGrid(0, 0, view.getThreeLabel());
    }

    @Override
    public void onExit(ActionEvent event) {
        view.getFrame().dispose();
        //view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
    }
}