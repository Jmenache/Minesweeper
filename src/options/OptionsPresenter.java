package options;


/**
 * OptionsPresenter Class
 */
public class OptionsPresenter implements OptionsViewListener {
    private final OptionsModel optionsModel;
    private final OptionsView optionsView;

    public OptionsPresenter(OptionsView optionsView, OptionsModel optionsModel) {
        this.optionsModel = optionsModel;
        this.optionsView = optionsView;
        optionsView.addListener(this);
    }
}
