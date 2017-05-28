import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Checkbox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class option_ex extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitle;
	private JPanel panel;
	private JRadioButton btnbeginner;
	private JLabel lblMines;
	private JLabel lblxTilesGrid;
	private JLabel lblBeginner;
	private JRadioButton radioButton;
	private JLabel lblMines_1;
	private JLabel lblxTilesGrid_1;
	private JLabel lblIntermediate;
	private JRadioButton radioButton_1;
	private JLabel lblMines_2;
	private JLabel lblxTilesGrid_2;
	private JLabel lblAdvanced;
	private JRadioButton radioButton_2;
	private JLabel lblCustom;
	private JLabel lblHeight;
	private JLabel lblWidth;
	private JLabel lblMines_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel_1;
	private Checkbox checkbox;
	private Checkbox checkbox_1;
	private Checkbox checkbox_2;
	private JButton btnOK;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					option_ex frame = new option_ex();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public option_ex() {
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Difficulty");
		lblTitle.setBounds(5, 5, 568, 18);
		contentPane.add(lblTitle);
		
		panel = new JPanel();
		panel.setBounds(5, 23, 568, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnbeginner = new JRadioButton("");
		btnbeginner.setBounds(23, 18, 25, 45);
		panel.add(btnbeginner);
		
		lblMines = new JLabel("30 mines");
		lblMines.setBounds(55, 51, 59, 18);
		panel.add(lblMines);
		
		lblxTilesGrid = new JLabel("9x9 tiles grid");
		lblxTilesGrid.setBounds(55, 71, 84, 18);
		panel.add(lblxTilesGrid);
		
		lblBeginner = new JLabel("Beginner");
		lblBeginner.setBounds(55, 31, 62, 18);
		panel.add(lblBeginner);
		
		radioButton = new JRadioButton("");
		radioButton.setBounds(23, 94, 25, 45);
		panel.add(radioButton);
		
		lblMines_1 = new JLabel("40 mines");
		lblMines_1.setBounds(55, 127, 59, 18);
		panel.add(lblMines_1);
		
		lblxTilesGrid_1 = new JLabel("16x16 tiles grid");
		lblxTilesGrid_1.setBounds(55, 147, 84, 18);
		panel.add(lblxTilesGrid_1);
		
		lblIntermediate = new JLabel("Intermediate");
		lblIntermediate.setBounds(55, 107, 84, 18);
		panel.add(lblIntermediate);
		
		radioButton_1 = new JRadioButton("");
		radioButton_1.setBounds(23, 169, 25, 45);
		panel.add(radioButton_1);
		
		lblMines_2 = new JLabel("99 mines");
		lblMines_2.setBounds(55, 201, 59, 18);
		panel.add(lblMines_2);
		
		lblxTilesGrid_2 = new JLabel("16x30 tiles grid");
		lblxTilesGrid_2.setBounds(55, 221, 84, 18);
		panel.add(lblxTilesGrid_2);
		
		lblAdvanced = new JLabel("Advanced");
		lblAdvanced.setBounds(55, 181, 84, 18);
		panel.add(lblAdvanced);
		
		radioButton_2 = new JRadioButton("");
		radioButton_2.setBounds(236, 18, 25, 45);
		panel.add(radioButton_2);
		
		lblCustom = new JLabel("Custom :");
		lblCustom.setBounds(268, 30, 84, 18);
		panel.add(lblCustom);
		
		lblHeight = new JLabel("Height(9-24) :");
		lblHeight.setBounds(268, 71, 95, 18);
		panel.add(lblHeight);
		
		lblWidth = new JLabel("Width(9-30) :");
		lblWidth.setBounds(268, 121, 95, 18);
		panel.add(lblWidth);
		
		lblMines_3 = new JLabel("Mines(10-668) :");
		lblMines_3.setBounds(268, 169, 109, 18);
		panel.add(lblMines_3);
		
		textField = new JTextField();
		textField.setBounds(397, 68, 116, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(397, 121, 116, 24);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(397, 169, 116, 24);
		panel.add(textField_2);
		
		panel_1 = new JPanel();
		panel_1.setBounds(5, 458, 568, 1);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		checkbox = new Checkbox("    Always save game on exit");
		checkbox.setBounds(36, 309, 376, 25);
		contentPane.add(checkbox);
		
		checkbox_1 = new Checkbox("    Always continue saved games");
		checkbox_1.setBounds(36, 340, 376, 25);
		contentPane.add(checkbox_1);
		
		checkbox_2 = new Checkbox("    Allows question marks (on double right-click)");
		checkbox_2.setBounds(36, 371, 376, 25);
		contentPane.add(checkbox_2);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(322, 419, 105, 27);
		contentPane.add(btnOK);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(444, 419, 105, 27);
		contentPane.add(btnCancel);
	}
}
