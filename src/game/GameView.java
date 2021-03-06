package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.BiConsumer;


/**
 * GameView Class
 */
public class GameView {
    private static final String TITLE = "Minesweeper";
    private final ArrayList<GameViewListener> listeners;

    private final GridBagConstraints c;

    private final JFrame frame;

    private JButton[][] buttons;
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
    private final static String QUESTION_MARK_URL = "/images/200px-Minesweeper_questionMark.svg.png";
    private final static String UNOPENED_URL = "/images/200px-Minesweeper_unopened_square.svg.png";
    private final static String FRAME_MINE_URL = "/images/mineIcon.png";
    // TODO: Could be WRONG_FLAG_PLACEMENT_URL
    private final static String CROSSED_MINE_URL = "/images/crossedMine.jpg";
    private final static String MINE_URL = "/images/mine.jpg";
    private static final String EXPLODED_MINE_URL = "/images/1200x630bb.jpg";
    private static final String NEW_GAME_URL = "/images/newGame.png";
    private static final String OPEN_SQUARE_URL = "/images/openSquare.png";
    private static final String DEFEAT_URL = "/images/defeat.png";
    private static final String VICTORY_URL = "/images/victory.png";

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
    private ImageIcon frameMineIcon;
    private ImageIcon crossedMineIcon;
    private ImageIcon mineIcon;
    private ImageIcon explodedMineIcon;
    private ImageIcon newGameIcon;
    private ImageIcon openSquareIcon;
    private ImageIcon defeatIcon;
    private ImageIcon victoryIcon;

    private JLabel timeLabel;
    private JButton faceButton;
    private JLabel numberOfMinesLabel;

    private Timer timer;

    public GameView(int row, int col) {
        // Define Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        initIcons();

        // Create Frame
        frame = new JFrame(TITLE);
        frame.setIconImage(frameMineIcon.getImage());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel pane = new JPanel(new BorderLayout());
        frame.setContentPane(pane);

        // Create Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("game");
        JMenu helpMenu = new JMenu("?");

        JMenuItem newGameItem = new JMenuItem("New game");
        JMenuItem statisticsItem  = new JMenuItem("statistics");
        JMenuItem optionsItem  = new JMenuItem("Options");
        JMenuItem saveItem  = new JMenuItem("Save");
        JMenuItem loadItem  = new JMenuItem("Load");
        JMenuItem exitItem = new JMenuItem("Exit");

        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        statisticsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        optionsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
        loadItem.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_MASK));

        newGameItem.addActionListener(event -> notifyListeners(GameViewListener::onNewGame, event));
        optionsItem.addActionListener(event -> notifyListeners(GameViewListener::onOptions, event));
        statisticsItem.addActionListener(event -> notifyListeners(GameViewListener::onStatistics, event));
        saveItem.addActionListener(event -> notifyListeners(GameViewListener::onSaveGame, event));
        loadItem.addActionListener(event -> notifyListeners(GameViewListener::onLoadGame, event));
        exitItem.addActionListener(event -> notifyListeners(GameViewListener::onExit, event));

        // Add Menu
        pane.add(menuBar, BorderLayout.NORTH);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        gameMenu.add(newGameItem);

        gameMenu.addSeparator();
        gameMenu.add(statisticsItem);
        gameMenu.add(optionsItem);

        gameMenu.addSeparator();
        gameMenu.add(saveItem);
        gameMenu.add(loadItem);

        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        // Create grid
        gridPane = new JPanel(new GridBagLayout());
        gridPane.setBackground(Color.black);
        pane.add(gridPane, BorderLayout.CENTER);
        c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        newGrid(row, col);

        // Create South Border
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout());
        pane.add(bottomPanel, BorderLayout.SOUTH);

        JButton mineButton = new JButton("Mines");
        mineButton.setEnabled(false);
        bottomPanel.add(mineButton);

        numberOfMinesLabel = new JLabel();
        bottomPanel.add(numberOfMinesLabel);

        faceButton = new JButton(newGameIcon);
        faceButton.setBorderPainted(false);
        faceButton.setBorder(null);
        faceButton.setFocusable(false);
        faceButton.setMargin(new Insets(0, 0, 0, 0));
        faceButton.setContentAreaFilled(false);
        faceButton.setFocusPainted(false);
        faceButton.setRolloverEnabled(false);
        faceButton.addActionListener(event -> notifyListeners(GameViewListener::onNewGame, event));
        bottomPanel.add(faceButton);

        timeLabel = new JLabel();
        timer = new Timer( 1000, e -> notifyListeners(GameViewListener::onTimerStart, e));
        timer.setInitialDelay(0);
        bottomPanel.add(timeLabel);

        JButton timeButton = new JButton("Time");
        timeButton.setEnabled(false);
        bottomPanel.add(timeButton);

        // Conclude view creation
        this.listeners = new ArrayList<>();
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // void initiateButtons(int rows, int cols) {
    //     buttons = new JButton[rows][cols];
    // }

    void newGrid(int row, int col) {
        gridPane.removeAll();


        buttons = new JButton[row][col];
        createGrid(row, col);
        frame.pack();
        frame.setResizable(false);
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
        frameMineIcon = initIcon(FRAME_MINE_URL);
        crossedMineIcon = initIcon(CROSSED_MINE_URL);
        mineIcon = initIcon(MINE_URL);
        explodedMineIcon = initIcon(EXPLODED_MINE_URL);
        newGameIcon = initIcon(NEW_GAME_URL);
        openSquareIcon = initIcon(OPEN_SQUARE_URL);
        defeatIcon = initIcon(DEFEAT_URL);
        victoryIcon = initIcon(VICTORY_URL);

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
                buttons[row][col].setSelectedIcon(unopenedIcon);
                buttons[row][col].setPressedIcon(unopenedIcon);
                buttons[row][col].setBorderPainted(false);
                buttons[row][col].setBorder(null);
                buttons[row][col].setFocusable(false);
                buttons[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttons[row][col].setContentAreaFilled(false);
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].setRolloverEnabled(false);

                int[] coordinates = {row, col};
                buttons[row][col].addActionListener(event -> notifyListeners(GameViewListener::onOpenSquare, coordinates));

                buttons[row][col].addMouseListener(new MouseInputAdapter() {
                    @Override
                    public void mousePressed(MouseEvent event) {
                        super.mousePressed(event);
                        if (SwingUtilities.isRightMouseButton(event)) {
                            notifyListeners(GameViewListener::onRightClick, coordinates);
                        } else if (SwingUtilities.isMiddleMouseButton(event) || event.getClickCount() == 2) {
                            notifyListeners(GameViewListener::onMiddleClick, coordinates);
                        }
                    }
                });

                c.gridx = row;
                c.gridy = col;
                gridPane.add(buttons[row][col], c);
            }
        }
    }

    void mapDisableIcon(int[][] mines, int row, int col) {
        switch (mines[row][col]) {
            case 0:
                buttons[row][col].setDisabledIcon(zeroIcon);
                break;
            case 1:
                buttons[row][col].setDisabledIcon(oneIcon);
                break;
            case 2:
                buttons[row][col].setDisabledIcon(twoIcon);
                break;
            case 3:
                buttons[row][col].setDisabledIcon(threeIcon);
                break;
            case 4:
                buttons[row][col].setDisabledIcon(fourIcon);
                break;
            case 5:
                buttons[row][col].setDisabledIcon(fiveIcon);
                break;
            case 6:
                buttons[row][col].setDisabledIcon(sixIcon);
                break;
            case 7:
                buttons[row][col].setDisabledIcon(sevenIcon);
                break;
            case 8:
                buttons[row][col].setDisabledIcon(eightIcon);
                break;
            case -1:
                buttons[row][col].setDisabledIcon(mineIcon);
                break;
        }
    }

    void mapDisableIcons(int[][] mines) {
        for (int row = 0; row < mines.length; row++) {
            for (int col = 0; col < mines[row].length; col++) {
                mapDisableIcon(mines, row, col);
            }
        }
    }

    private <T> void notifyListeners(final BiConsumer<GameViewListener, T> consumer, final T data) {
        listeners.forEach(listener -> consumer.accept(listener, data));
    }

	void updateGUI() {
		frame.revalidate();
        frame.repaint();
	}

    void addListener(final GameViewListener listener) {
        listeners.add(listener);
    }

    JFrame getFrame() {
        return frame;
    }

    JButton[][] getButtons() {
        return buttons;
    }

    ImageIcon getFlagIcon() {
        return flagIcon;
    }

    ImageIcon getQuestionMarkIcon() {
        return questionMarkIcon;
    }

    ImageIcon getUnopenedIcon() {
        return unopenedIcon;
    }

    ImageIcon getCrossedMineIcon() {
        return crossedMineIcon;
    }

    ImageIcon getExplodedMineIcon() {
        return explodedMineIcon;
    }

    ImageIcon getNewGameIcon() {
        return newGameIcon;
    }

    ImageIcon getOpenSquareIcon() {
        return openSquareIcon;
    }

    ImageIcon getDefeatIcon() {
        return defeatIcon;
    }

    ImageIcon getVictoryIcon() {
        return victoryIcon;
    }

    JLabel getNumberOfMinesLabel() {
        return numberOfMinesLabel;
    }

    JLabel getTimeLabel() {
        return timeLabel;
    }

    JButton getFaceButton() {
        return faceButton;
    }

    Timer getTimer() {
        return timer;
    }
}