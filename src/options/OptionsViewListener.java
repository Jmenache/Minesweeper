package options;

import java.awt.event.ActionEvent;

/**
 * OptionsViewListener Interface
 */
public interface OptionsViewListener {
    void onSubmit(ActionEvent event);
    void onCancel(ActionEvent event);
}
