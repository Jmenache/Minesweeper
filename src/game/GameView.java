package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    private final JPanel pane;
    private final JButton[][] buttons;
    private final JPanel gridPane;

    private final static String ZERO_URL = "/images/200px-Minesweeper_0.svg.png";
    private final static String ONE_URL = "/images/200px-Minesweeper_1.svg.png";
    private final static String TWO_URL = "/images/200px-Minesweeper_2.svg.png";
    private final static String THREE_URL = "/images/200px-Minesweeper_3.svg.png";
    private final static String FOUR_URL = "/images/200px-Minesweeper_4.svg.png";
    private final static String FIVE_URL = "/images/200px-Minesweeper_5.svg.png";
    private final static String SIX_URL = "/images/200px-Minesweeper_6.svg.png";
    private final static String SEVEN_URL = "/images/200px-Minesweeper_7.svg.png";
    private final static String EIGHT_URL = "/images/200px-Minesweeper_8.svg.png";
    private final static String FLAG_URL = "/images/200px-Minesweeper_flag.svg.png";
    private final static String QUESTION_MARK_URL = "/images/200px-Minesweeper_questionmark.svg.png";
    private final static String UNOPENED_URL = "/images/200px-Minesweeper_unopened_square.svg.png";

    // private final JLabel threeLabel;
    private ImageIcon zeroIcon;
    private ImageIcon oneIcon;
    private ImageIcon twoIcon;
    private ImageIcon threeIcon;
    private ImageIcon fourIcon;
    private ImageIcon fiveIcon;
    private ImageIcon sixIcon;
    private ImageIcon sevenIcon;
    private ImageIcon eightIcon;
    private ImageIcon flagIcon;
    private ImageIcon questionMarkIcon;
    private ImageIcon unopenedIcon;

    public GameView() {
        // Define Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Create Frame
        frame = new JFrame(TITLE);
        URL mineIconURL = getClass().getResource("/images/mineIcon.png");
        ImageIcon mineIcon = new ImageIcon(mineIconURL);
        frame.setIconImage(mineIcon.getImage());

        frame.setSize(WIDTH ,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        pane = new JPanel(new BorderLayout());
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
        OptionsItem.addActionListener(event -> notifyListeners(GameViewListener::onOptions, event));

        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        // Create grid
        gridPane = new JPanel(new GridBagLayout());
        pane.add(gridPane, BorderLayout.CENTER);
        c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        initIcons();
        buttons = new JButton[9][9];
        createGrid(9, 9);

        // Create South Border
        JButton button = new JButton("lol");
        pane.add(button, BorderLayout.SOUTH);

        // Conclude view creation
        this.listeners = new ArrayList<>();
        frame.setVisible(true);
    }

    private void initIcons() {
        zeroIcon = initIcon(ZERO_URL);
        oneIcon = initIcon(ONE_URL);
        twoIcon = initIcon(TWO_URL);
        threeIcon = initIcon(THREE_URL);
        fourIcon = initIcon(FOUR_URL);
        fiveIcon = initIcon(FIVE_URL);
        sixIcon = initIcon(SIX_URL);
        sevenIcon = initIcon(SEVEN_URL);
        eightIcon = initIcon(EIGHT_URL);
        flagIcon = initIcon(FLAG_URL);
        questionMarkIcon = initIcon(QUESTION_MARK_URL);
        unopenedIcon = initIcon(UNOPENED_URL);
    }

    private ImageIcon initIcon(String address) {
        URL url = getClass().getResource(address);
        BufferedImage img = null;
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = null;
        if (img != null) {
            dimg = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        }
        if (dimg != null) {
            return new ImageIcon(dimg);
        }
        return null;
    }

    private void createGrid(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                buttons[row][col] = new JButton(unopenedIcon);
                int[] coordinates = {row, col};
                buttons[row][col].addActionListener(event -> notifyListeners(GameViewListener::onOpenSquare, coordinates));

                c.gridx = row;
                c.gridy = col;
                // threeLabel.setSize(buttons[row][col].getSize());
                // threeLabel.setMinimumSize(buttons[row][col].getMinimumSize());
                // threeLabel.setMaximumSize(buttons[row][col].getMaximumSize());
                // threeLabel.setPreferredSize(buttons[row][col].getPreferredSize());
                gridPane.add(buttons[row][col], c);
                // buttons[row][col].setVisible(false);
            }
        }
    }

    private <T> void notifyListeners(final BiConsumer<GameViewListener, T> consumer, final T data) {
        for (final GameViewListener listener : listeners) {
            consumer.accept(listener, data);
        }
    }

	public void updateGUI() {
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

    public ImageIcon getThreeIcon() {
        return threeIcon;
    }

    public void setUnopenedIcon(ImageIcon unopenedIcon) {
        this.unopenedIcon = unopenedIcon;
    }

    public void setThreeIcon(ImageIcon threeIcon) {
        this.threeIcon = threeIcon;
    }

    // public JLabel getThreeLabel() {
    //     return threeLabel;
    // }
}