package options;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.function.BiConsumer;

@SuppressWarnings("serial")
public class OptionsView extends JPanel {
    private static final String TITLE = "Minesweeper";
    private final ArrayList<OptionsViewListener> listeners;

    private final JFrame frame;

    private JRadioButton beginnerButton;
    private JRadioButton intermediateButton;
    private JRadioButton advanceButton;
    private JRadioButton customButton;

    private JTextField rowsTextField;
	private JTextField colsTextField;
	private JTextField numberOfMinesTextField;

	private JCheckBox saveOnExitCheckbox;
    private JCheckBox AlwaysContinueGameCheckbox;
    private JCheckBox questionMarkCheckbox;

	public OptionsView() {
		frame = new JFrame(TITLE);
        frame.setAutoRequestFocus(false);
        frame.setAlwaysOnTop(true);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 596, 511);

//        JOptionPane jOptionPane = new JOptionPane("lol");
//        jOptionPane.setVisible(true);

        JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        frame.setContentPane(contentPane);

        JLabel setDifficultyTitle = new JLabel("Difficulty");
		setDifficultyTitle.setBounds(5, 5, 568, 18);
		contentPane.add(setDifficultyTitle);

        JPanel panel = new JPanel();
		panel.setBounds(5, 23, 568, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		beginnerButton = new JRadioButton("");
		beginnerButton.setBounds(23, 18, 25, 45);
		panel.add(beginnerButton);

        JLabel thirtyMinesLabel = new JLabel("30 mines");
		thirtyMinesLabel.setBounds(55, 51, 59, 18);
		panel.add(thirtyMinesLabel);

        JLabel beginnerSizeLabel = new JLabel("9x9 tiles grid");
		beginnerSizeLabel.setBounds(55, 71, 84, 18);
		panel.add(beginnerSizeLabel);

        JLabel lblBeginner = new JLabel("Beginner");
		lblBeginner.setBounds(55, 31, 62, 18);
		panel.add(lblBeginner);
		
		intermediateButton = new JRadioButton("");
		intermediateButton.setBounds(23, 94, 25, 45);
		panel.add(intermediateButton);

        JLabel fortyMinesLabel = new JLabel("40 mines");
		fortyMinesLabel.setBounds(55, 127, 59, 18);
		panel.add(fortyMinesLabel);

        JLabel intermediateSizeLabel = new JLabel("16x16 tiles grid");
		intermediateSizeLabel.setBounds(55, 147, 84, 18);
		panel.add(intermediateSizeLabel);

        JLabel intermediateLabel = new JLabel("Intermediate");
		intermediateLabel.setBounds(55, 107, 84, 18);
		panel.add(intermediateLabel);
		
		advanceButton = new JRadioButton("");
		advanceButton.setBounds(23, 169, 25, 45);
		panel.add(advanceButton);

        JLabel ninetyNineMinesLabel = new JLabel("99 mines");
		ninetyNineMinesLabel.setBounds(55, 201, 59, 18);
		panel.add(ninetyNineMinesLabel);

        JLabel advancedSizeLabel = new JLabel("16x30 tiles grid");
		advancedSizeLabel.setBounds(55, 221, 84, 18);
		panel.add(advancedSizeLabel);

        JLabel AdvancedLabel = new JLabel("Advanced");
		AdvancedLabel.setBounds(55, 181, 84, 18);
		panel.add(AdvancedLabel);
		
		customButton = new JRadioButton("");
		customButton.setBounds(236, 18, 25, 45);
		panel.add(customButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(beginnerButton);
        buttonGroup.add(intermediateButton);
        buttonGroup.add(advanceButton);
        buttonGroup.add(customButton);

        JLabel customLabel = new JLabel("Custom :");
		customLabel.setBounds(268, 30, 84, 18);
		panel.add(customLabel);

        JLabel customHeightLabel = new JLabel("Height(9-24) :");
		customHeightLabel.setBounds(268, 71, 95, 18);
		panel.add(customHeightLabel);

        JLabel customWidthLabel = new JLabel("Width(9-30) :");
		customWidthLabel.setBounds(268, 121, 95, 18);
		panel.add(customWidthLabel);

        JLabel customNumberOfMinesLabel = new JLabel("Mines(10-668) :");
		customNumberOfMinesLabel.setBounds(268, 169, 109, 18);
		panel.add(customNumberOfMinesLabel);
		
		rowsTextField = new JTextField();
		rowsTextField.setBounds(397, 68, 116, 24);
		panel.add(rowsTextField);
		rowsTextField.setColumns(10);
		
		colsTextField = new JTextField();
		colsTextField.setColumns(10);
		colsTextField.setBounds(397, 121, 116, 24);
		panel.add(colsTextField);
		
		numberOfMinesTextField = new JTextField();
		numberOfMinesTextField.setColumns(10);
		numberOfMinesTextField.setBounds(397, 169, 116, 24);
		panel.add(numberOfMinesTextField);

        saveOnExitCheckbox = new JCheckBox("   Always save game on exit");
		saveOnExitCheckbox.setBounds(36, 309, 376, 25);
		contentPane.add(saveOnExitCheckbox);

        AlwaysContinueGameCheckbox = new JCheckBox("   Always continue saved games");
		AlwaysContinueGameCheckbox.setBounds(36, 340, 376, 25);
		contentPane.add(AlwaysContinueGameCheckbox);

        questionMarkCheckbox = new JCheckBox("   Allows question marks (on double right-click)");
		questionMarkCheckbox.setBounds(36, 371, 376, 25);
		contentPane.add(questionMarkCheckbox);

        JButton btnOK = new JButton("OK");
		btnOK.setBounds(322, 419, 105, 27);
        btnOK.addActionListener(event -> notifyListeners(OptionsViewListener::onSubmit, event));
        contentPane.add(btnOK);
        frame.getRootPane().setDefaultButton(btnOK);

        JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(444, 419, 105, 27);
        btnCancel.addActionListener(event -> notifyListeners(OptionsViewListener::onCancel, event));
        contentPane.add(btnCancel);

        this.listeners = new ArrayList<>();
        frame.setVisible(true);
    }

    private <T> void notifyListeners(final BiConsumer<OptionsViewListener, T> consumer, final T data) {
        listeners.forEach(listener -> consumer.accept(listener, data));
    }

	void addListener(final OptionsViewListener listener) {
		listeners.add(listener);
	}

    JFrame getFrame() {
        return frame;
    }

    JRadioButton getBeginnerButton() {
        return beginnerButton;
    }

    JRadioButton getIntermediateButton() {
        return intermediateButton;
    }

    JRadioButton getAdvanceButton() {
        return advanceButton;
    }

    JRadioButton getCustomButton() {
        return customButton;
    }

    JTextField getRowsTextField() {
        return rowsTextField;
    }

    JTextField getColsTextField() {
        return colsTextField;
    }

    JTextField getNumberOfMinesTextField() {
        return numberOfMinesTextField;
    }

    JCheckBox getSaveOnExitCheckbox() {
        return saveOnExitCheckbox;
    }

    JCheckBox getAlwaysContinueGameCheckbox() {
        return AlwaysContinueGameCheckbox;
    }

    JCheckBox getQuestionMarkCheckbox() {
        return questionMarkCheckbox;
    }
}
