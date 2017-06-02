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
    public void onExit(ActionEvent event) {
        view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
    }
}
