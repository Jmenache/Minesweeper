package game;

import options.OptionsModel;
import options.OptionsView;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.BiConsumer;


/**
 * game.GameView Class
 */
@SuppressWarnings("serial")
public class GameView extends JFrame {
    private static final String TITLE = "Minesweeper";

    private static final int WIDTH = 400, HEIGHT = 500;

    private final ArrayList<GameViewListener> listeners;

    private final GridBagConstraints c;

    private final JFrame frame;
    private final JButton[][] buttons;
    private final JPanel gridPane;
    private int row = 9;
    private int col = 9;
    private int mines = 10;
	private OptionsView optionsView = new OptionsView();
	private OptionsModel optionsModel;

    // private final JLabel threeLabel;
	
    public GameView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        frame = new JFrame(TITLE);
        URL mineIconURL = getClass().getResource("/images/mineIcon.png");
        ImageIcon mineIcon = new ImageIcon(mineIconURL);
        frame.setIconImage(mineIcon.getImage());

        frame.setSize(WIDTH ,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel pane = new JPanel(new BorderLayout());
        frame.setContentPane(pane);

        // Create Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("game");
        JMenu helpMenu = new JMenu("?");

        JMenuItem newGameItem = new JMenuItem("New game");
        JMenuItem StatisticsItem  = new JMenuItem("Statistics");
        JMenuItem OptionsItem  = new JMenuItem("options");
        JMenuItem exitItem = new JMenuItem("Exit");

        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        StatisticsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        OptionsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        newGameItem.addActionListener(event -> notifyListeners(GameViewListener::onNewGame, event));
        exitItem.addActionListener(event -> notifyListeners(GameViewListener::onExit, event));

        // Add Menu
        pane.add(menuBar, BorderLayout.NORTH);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(StatisticsItem);
        gameMenu.add(OptionsItem);
        OptionsItem.addActionListener(e -> {
            optionsView.setVisible(true);
            if(optionsView.isClickedOK()){
                optionsModel = optionsView.getOptionsModel();
                row= optionsModel.getWidth();
                col= optionsModel.getHeight();
                mines= optionsModel.getNumOfMines();
                System.out.println(row+","+col+","+mines);
                updateGUI();
            }

            //optionsView.dispose();
        });
        gameMenu.addSeparator();
        gameMenu.add(exitItem);


        gridPane = new JPanel(new GridBagLayout());

        pane.add(gridPane, BorderLayout.CENTER);

        c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;


        buttons = new JButton[row][col];
        URL threeIconURL = getClass().getResource("/images/200px-Minesweeper_unopened_square.svg.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(threeIconURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = null;
        if (img != null) {
            dimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        }
        ImageIcon threeIcon = null;
        if (dimg != null) {
            threeIcon = new ImageIcon(dimg);
        }
        // ImageIcon threeIcon = new ImageIcon(threeIconURL);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                buttons[i][j] = new JButton(/*threeIcon*/);
                c.gridx = i;
                c.gridy = j;

                // threeLabel.setSize(buttons[i][j].getSize());
                // threeLabel.setMinimumSize(buttons[i][j].getMinimumSize());
                // threeLabel.setMaximumSize(buttons[i][j].getMaximumSize());
                // threeLabel.setPreferredSize(buttons[i][j].getPreferredSize());
                gridPane.add(buttons[i][j], c);
                // buttons[i][j].setVisible(false);
            }
        }

        JButton button = new JButton("lol");
        pane.add(button, BorderLayout.SOUTH);

        this.listeners = new ArrayList<>();
        frame.setVisible(true);
    }


    private <T> void notifyListeners(final BiConsumer<GameViewListener, T> consumer, final T data) {
        for (final GameViewListener listener : listeners) {
            consumer.accept(listener, data);
        }
    }

	public void updateGUI()
	{
		revalidate();
		repaint();
	}

    void addListener(final GameViewListener listener) {
        listeners.add(listener);
    }

    void addToGrid(int x, int y, JComponent component) {
        c.gridx = x;
        c.gridy = y;
        gridPane.add(component, c);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JPanel getGridPane() {
        return gridPane;
    }

    // public JLabel getThreeLabel() {
    //     return threeLabel;
    // }
}