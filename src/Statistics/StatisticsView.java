package Statistics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatisticsView extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JLabel lblLevel;
	private JList levelList;
	private JLabel lblNewLabel;
	private JTextField ResultTimes;
	private JLabel GamePlayedNum;
	private JLabel GameWinNum;
	private JLabel WinPercentNum;
	private JLabel LongestWinningStreak;
	private JLabel LongestLosingStreak;
	private JLabel CurrentStreak;
	private JButton btnClose;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsView frame = new StatisticsView();
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
	public StatisticsView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 654, 296);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 32, 174, 197);
		contentPane.add(scrollPane);
		
		lblLevel = new JLabel("Level");
		scrollPane.setColumnHeaderView(lblLevel);
		
		levelList = new JList();
		scrollPane.setViewportView(levelList);
		
		lblNewLabel = new JLabel("Best Times : ");
		lblNewLabel.setBounds(232, 33, 98, 18);
		contentPane.add(lblNewLabel);
		
		ResultTimes = new JTextField();
		ResultTimes.setBounds(232, 70, 161, 159);
		contentPane.add(ResultTimes);
		ResultTimes.setColumns(10);
		
		GamePlayedNum = new JLabel("Game played : ");
		GamePlayedNum.setBounds(425, 28, 161, 29);
		contentPane.add(GamePlayedNum);
		
		GameWinNum = new JLabel("Game win : ");
		GameWinNum.setBounds(425, 56, 161, 29);
		contentPane.add(GameWinNum);
		
		WinPercentNum = new JLabel("Win percentage : ");
		WinPercentNum.setBounds(425, 81, 161, 29);
		contentPane.add(WinPercentNum);
		
		LongestWinningStreak = new JLabel("Longest winning streak : ");
		LongestWinningStreak.setBounds(425, 108, 161, 29);
		contentPane.add(LongestWinningStreak);
		
		LongestLosingStreak = new JLabel("Longest losing streak : ");
		LongestLosingStreak.setBounds(425, 135, 161, 29);
		contentPane.add(LongestLosingStreak);
		
		CurrentStreak = new JLabel("Current streak : ");
		CurrentStreak.setBounds(425, 161, 161, 29);
		contentPane.add(CurrentStreak);
		
		btnClose = new JButton("Close");
		btnClose.setBounds(419, 202, 71, 27);
		contentPane.add(btnClose);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReset.setBounds(504, 202, 71, 27);
		contentPane.add(btnReset);
	}
}
