package options;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.function.BiConsumer;

@SuppressWarnings("serial")
public class OptionsView extends JFrame {
    private static final String TITLE = "Minesweeper";
    private final ArrayList<OptionsViewListener> listeners;

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
		setTitle(TITLE);
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 511);
        JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Difficulty");
		lblTitle.setBounds(5, 5, 568, 18);
		contentPane.add(lblTitle);

        JPanel panel = new JPanel();
		panel.setBounds(5, 23, 568, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		beginnerButton = new JRadioButton("");
		beginnerButton.setBounds(23, 18, 25, 45);
		panel.add(beginnerButton);

        JLabel lblMines = new JLabel("30 mines");
		lblMines.setBounds(55, 51, 59, 18);
		panel.add(lblMines);

        JLabel lblxTilesGrid = new JLabel("9x9 tiles grid");
		lblxTilesGrid.setBounds(55, 71, 84, 18);
		panel.add(lblxTilesGrid);

        JLabel lblBeginner = new JLabel("Beginner");
		lblBeginner.setBounds(55, 31, 62, 18);
		panel.add(lblBeginner);
		
		intermediateButton = new JRadioButton("");
		intermediateButton.setBounds(23, 94, 25, 45);
		panel.add(intermediateButton);

        JLabel lblMines_1 = new JLabel("40 mines");
		lblMines_1.setBounds(55, 127, 59, 18);
		panel.add(lblMines_1);

        JLabel lblxTilesGrid_1 = new JLabel("16x16 tiles grid");
		lblxTilesGrid_1.setBounds(55, 147, 84, 18);
		panel.add(lblxTilesGrid_1);

        JLabel lblIntermediate = new JLabel("Intermediate");
		lblIntermediate.setBounds(55, 107, 84, 18);
		panel.add(lblIntermediate);
		
		advanceButton = new JRadioButton("");
		advanceButton.setBounds(23, 169, 25, 45);
		panel.add(advanceButton);

        JLabel lblMines_2 = new JLabel("99 mines");
		lblMines_2.setBounds(55, 201, 59, 18);
		panel.add(lblMines_2);

        JLabel lblxTilesGrid_2 = new JLabel("16x30 tiles grid");
		lblxTilesGrid_2.setBounds(55, 221, 84, 18);
		panel.add(lblxTilesGrid_2);

        JLabel lblAdvanced = new JLabel("Advanced");
		lblAdvanced.setBounds(55, 181, 84, 18);
		panel.add(lblAdvanced);
		
		customButton = new JRadioButton("");
		customButton.setBounds(236, 18, 25, 45);
		panel.add(customButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(beginnerButton);
        buttonGroup.add(intermediateButton);
        buttonGroup.add(advanceButton);
        buttonGroup.add(customButton);

        JLabel lblCustom = new JLabel("Custom :");
		lblCustom.setBounds(268, 30, 84, 18);
		panel.add(lblCustom);

        JLabel lblHeight = new JLabel("Height(9-24) :");
		lblHeight.setBounds(268, 71, 95, 18);
		panel.add(lblHeight);

        JLabel lblWidth = new JLabel("Width(9-30) :");
		lblWidth.setBounds(268, 121, 95, 18);
		panel.add(lblWidth);

        JLabel lblMines_3 = new JLabel("Mines(10-668) :");
		lblMines_3.setBounds(268, 169, 109, 18);
		panel.add(lblMines_3);
		
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
        getRootPane().setDefaultButton(btnOK);

        JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(444, 419, 105, 27);
        btnCancel.addActionListener(event -> notifyListeners(OptionsViewListener::onCancel, event));
        contentPane.add(btnCancel);

        this.listeners = new ArrayList<>();
        setVisible(true);
	}

    private <T> void notifyListeners(final BiConsumer<OptionsViewListener, T> consumer, final T data) {
        listeners.forEach(listener -> consumer.accept(listener, data));
    }

	void addListener(final OptionsViewListener listener) {
		listeners.add(listener);
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
