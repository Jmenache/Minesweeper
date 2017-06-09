package options;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Checkbox;

@SuppressWarnings("serial")
public class OptionsView extends JFrame {
//JDialog
	private JPanel contentPane;
	private JLabel lblTitle;
	private JPanel panel;
	private JRadioButton btnbeginner;
	private JLabel lblMines;
	private JLabel lblxTilesGrid;
	private JLabel lblBeginner;
	private JRadioButton btnInter;
	private JLabel lblMines_1;
	private JLabel lblxTilesGrid_1;
	private JLabel lblIntermediate;
	private JRadioButton btnAdv;
	private JLabel lblMines_2;
	private JLabel lblxTilesGrid_2;
	private JLabel lblAdvanced;
	private JRadioButton btnCustom;
	private JLabel lblCustom;
	private JLabel lblHeight;
	private JLabel lblWidth;
	private JLabel lblMines_3;
	private JTextField txtFieldHeight;
	private JTextField txtFieldWidth;
	private JTextField txtFieldMines;
	private Checkbox checkSave;
	private Checkbox checkContinue;
	private Checkbox checkRightClick;
	private JButton btnOK;
	private JButton btnCancel;
	
	private boolean clickedOK;
	private OptionsModel optionsModel;
	
	public OptionsView() {
		setTitle("Options");
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		
		btnInter = new JRadioButton("");
		btnInter.setBounds(23, 94, 25, 45);
		panel.add(btnInter);
		
		lblMines_1 = new JLabel("40 mines");
		lblMines_1.setBounds(55, 127, 59, 18);
		panel.add(lblMines_1);
		
		lblxTilesGrid_1 = new JLabel("16x16 tiles grid");
		lblxTilesGrid_1.setBounds(55, 147, 84, 18);
		panel.add(lblxTilesGrid_1);
		
		lblIntermediate = new JLabel("Intermediate");
		lblIntermediate.setBounds(55, 107, 84, 18);
		panel.add(lblIntermediate);
		
		btnAdv = new JRadioButton("");
		btnAdv.setBounds(23, 169, 25, 45);
		panel.add(btnAdv);
		
		lblMines_2 = new JLabel("99 mines");
		lblMines_2.setBounds(55, 201, 59, 18);
		panel.add(lblMines_2);
		
		lblxTilesGrid_2 = new JLabel("16x30 tiles grid");
		lblxTilesGrid_2.setBounds(55, 221, 84, 18);
		panel.add(lblxTilesGrid_2);
		
		lblAdvanced = new JLabel("Advanced");
		lblAdvanced.setBounds(55, 181, 84, 18);
		panel.add(lblAdvanced);
		
		btnCustom = new JRadioButton("");
		btnCustom.setBounds(236, 18, 25, 45);
		panel.add(btnCustom);
		
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
		
		txtFieldHeight = new JTextField();
		txtFieldHeight.setBounds(397, 68, 116, 24);
		panel.add(txtFieldHeight);
		txtFieldHeight.setColumns(10);
		
		txtFieldWidth = new JTextField();
		txtFieldWidth.setColumns(10);
		txtFieldWidth.setBounds(397, 121, 116, 24);
		panel.add(txtFieldWidth);
		
		txtFieldMines = new JTextField();
		txtFieldMines.setColumns(10);
		txtFieldMines.setBounds(397, 169, 116, 24);
		panel.add(txtFieldMines);
		
		checkSave = new Checkbox("    Always save game on exit");
		checkSave.setBounds(36, 309, 376, 25);
		contentPane.add(checkSave);
		
		checkContinue = new Checkbox("    Always continue saved games");
		checkContinue.setBounds(36, 340, 376, 25);
		contentPane.add(checkContinue);
		
		checkRightClick = new Checkbox("    Allows question marks (on double right-click)");
		checkRightClick.setBounds(36, 371, 376, 25);
		contentPane.add(checkRightClick);
		
		btnOK = new JButton("OK");
		btnOK.setBounds(322, 419, 105, 27);
		btnOK.addActionListener(e -> {
            if(btnbeginner.isSelected()){
                System.out.println("beginner");
                optionsModel.setNumOfMines(30);
                optionsModel.setHeight(9);
                optionsModel.setWidth(9);
                clickedOK = true;
            }
            else if(btnInter.isSelected()){
                System.out.println("intermediate");
                optionsModel.setNumOfMines(40);
                optionsModel.setHeight(16);
                optionsModel.setWidth(16);
                clickedOK = true;
            }
            else if(btnAdv.isSelected()){
                System.out.println("advanced");

                optionsModel.setNumOfMines(99);
                optionsModel.setHeight(30);
                optionsModel.setWidth(16);
                clickedOK = true;
            }
            else if(btnCustom.isSelected()){
                System.out.println("custom");

                optionsModel.setNumOfMines(Integer.parseInt(txtFieldMines.getText()));
                optionsModel.setHeight(Integer.parseInt(txtFieldHeight.getText()));
                optionsModel.setWidth(Integer.parseInt(txtFieldWidth.getText()));
                clickedOK = true;
            }
            else{
                clickedOK = false;
            }

            //dispose();
        });
		btnOK.setActionCommand("OK");
		contentPane.add(btnOK);
		getRootPane().setDefaultButton(btnOK);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(444, 419, 105, 27);
		btnCancel.addActionListener(e -> {
            clickedOK = false;
            dispose();
        });
		btnCancel.setActionCommand("Cancel");
		contentPane.add(btnCancel);
		
		optionsModel = new OptionsModel();
		clickedOK =false;
	}
	
	public boolean isClickedOK() {
		return clickedOK;
	}
	
	public OptionsModel getOptionsModel() {
		System.out.println("pass getlevel");
		return optionsModel;
	}
}
