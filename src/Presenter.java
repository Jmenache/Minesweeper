/**
 * Presenter Class
 */
public class Presenter implements ViewListener {
    private final View view;
    private final Model model;


    Presenter(final View view, final Model model) {
        this.view = view;
        // view.addListener(this);
        this.model = model;
    }
}
