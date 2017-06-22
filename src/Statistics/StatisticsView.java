package Statistics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@SuppressWarnings("serial")
public class StatisticsView extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            try {
                StatisticsView frame = new StatisticsView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
	}

	/**
	 * Create the frame.
	 */
	public StatisticsView() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 654, 296);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 32, 174, 197);
		contentPane.add(scrollPane);

		JLabel lblLevel = new JLabel("Level");
		scrollPane.setColumnHeaderView(lblLevel);

		JList levelList = new JList();
		scrollPane.setViewportView(levelList);

		JLabel lblNewLabel = new JLabel("Best Times : ");
		lblNewLabel.setBounds(232, 33, 98, 18);
		contentPane.add(lblNewLabel);

		JTextField resultTimes = new JTextField();
		resultTimes.setBounds(232, 70, 161, 159);
		contentPane.add(resultTimes);
		resultTimes.setColumns(10);

		JLabel gamePlayedNum = new JLabel("Game played : ");
		gamePlayedNum.setBounds(425, 28, 161, 29);
		contentPane.add(gamePlayedNum);

		JLabel gameWinNum = new JLabel("Game win : ");
		gameWinNum.setBounds(425, 56, 161, 29);
		contentPane.add(gameWinNum);

		JLabel winPercentNum = new JLabel("Win percentage : ");
		winPercentNum.setBounds(425, 81, 235, 29);
		contentPane.add(winPercentNum);

		JLabel longestWinningStreak = new JLabel("Longest winning streak : ");
		longestWinningStreak.setBounds(425, 108, 251, 29);
		contentPane.add(longestWinningStreak);

		JLabel longestLosingStreak = new JLabel("Longest losing streak : ");
		longestLosingStreak.setBounds(425, 135, 209, 29);
		contentPane.add(longestLosingStreak);

		JLabel currentStreak = new JLabel("Current streak : ");
		currentStreak.setBounds(425, 161, 161, 29);
		contentPane.add(currentStreak);

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(419, 202, 71, 27);
		contentPane.add(btnClose);
		btnClose.addActionListener(e -> dispose());

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(arg0 -> {
        });
		btnReset.setBounds(504, 202, 71, 27);
		contentPane.add(btnReset);
	}
}
