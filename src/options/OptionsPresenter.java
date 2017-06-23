package options;


import java.awt.event.ActionEvent;


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

        switch (optionsModel.getLevel()) {
            case OptionsModel.BEGINNER:
                optionsView.getBeginnerButton().setSelected(true);
                break;
            case OptionsModel.INTERMEDIATE:
                optionsView.getIntermediateButton().setSelected(true);
                break;
            case OptionsModel.ADVANCED:
                optionsView.getAdvanceButton().setSelected(true);
                break;
            case OptionsModel.CUSTOM:
                optionsView.getCustomButton().setSelected(true);
                optionsView.getRowsTextField().setText(String.valueOf(optionsModel.getRows()));
                optionsView.getColsTextField().setText(String.valueOf(optionsModel.getCols()));
                optionsView.getNumberOfMinesTextField().setText(String.valueOf(optionsModel.getNumberOfMines()));
                break;
        }
    }

    @Override
    public void onSubmit(ActionEvent event) {
        if (optionsView.getBeginnerButton().isSelected()) {
            optionsModel.setToBeginnerLevel();
        } else if (optionsView.getIntermediateButton().isSelected()) {
            optionsModel.setToIntermediateLevel();
        } else if (optionsView.getAdvanceButton().isSelected()) {
            optionsModel.setToAdvancedLevel();
        } else if (optionsView.getCustomButton().isSelected()) {
            int rows = Integer.parseInt(optionsView.getRowsTextField().getText());
            int cols = Integer.parseInt(optionsView.getColsTextField().getText());
            int numberOfMines = Integer.parseInt(optionsView.getNumberOfMinesTextField().getText());

            optionsModel.setToCustomLevel(rows, cols, numberOfMines);
        }


        optionsModel.setSaveOnExitEnabled(optionsView.getSaveOnExitCheckbox().isSelected());
        optionsModel.setAlwaysContinueGameEnabled(optionsView.getAlwaysContinueGameCheckbox().isSelected());
        optionsModel.setQuestionMarkEnabled(optionsView.getQuestionMarkCheckbox().isSelected());
        optionsView.getFrame().dispose();
    }

    @Override
    public void onCancel(ActionEvent event) {
        optionsView.getFrame().dispose();
    }
}
