import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.BiConsumer;

/**
 * View Class
 */
class View {
    private static final String TITLE = "Minesweeper";

    private static final int WIDTH = 400, HEIGHT = 500;

    private final ArrayList<ViewListener> listeners;

    private final GridBagConstraints c;

    private final JFrame frame;
    private final JButton[][] buttons;
    private final JPanel gridPane;

    private final JLabel threeLabel;

    View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        frame = new JFrame(TITLE);
        URL mineIconURL = getClass().getResource("images/mineIcon.png");
        ImageIcon mineIcon = new ImageIcon(mineIconURL);
        frame.setIconImage(mineIcon.getImage());

        frame.setSize(WIDTH ,HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel pane = new JPanel(new BorderLayout());
        frame.setContentPane(pane);

        // Create Menu
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        JMenu helpMenu = new JMenu("?");

        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem StatisticsItem  = new JMenuItem("Statistics");
        JMenuItem OptionsItem  = new JMenuItem("Options");
        JMenuItem exitItem = new JMenuItem("Exit");

        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        StatisticsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        OptionsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        newGameItem.addActionListener(event -> notifyListeners(ViewListener::onNewGame, event));
        exitItem.addActionListener(event -> notifyListeners(ViewListener::onExit, event));

        // Add Menu
        pane.add(menuBar, BorderLayout.NORTH);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(StatisticsItem);
        gameMenu.add(OptionsItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        gridPane = new JPanel(new GridBagLayout());

        pane.add(gridPane, BorderLayout.CENTER);

        c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        buttons = new JButton[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new JButton();
                c.gridx = i;
                c.gridy = j;
                gridPane.add(buttons[i][j], c);
            }
        }

        JButton button = new JButton("lol");
        pane.add(button, BorderLayout.SOUTH);


        URL threeIconURL = getClass().getResource("images/200px-Minesweeper_3.svg.png");
        ImageIcon threeIcon = new ImageIcon(threeIconURL);
        threeLabel  = new JLabel(threeIcon);

        this.listeners = new ArrayList<>();
        frame.setVisible(true);
    }



    private <T> void notifyListeners(final BiConsumer<ViewListener, T> consumer, final T data) {
        for (final ViewListener listener : listeners) {
            consumer.accept(listener, data);
        }
    }

    void addListener(final ViewListener listener) {
        listeners.add(listener);
    }

    void addToGrid(int x, int y, JComponent component) {
        c.gridx = x;
        c.gridy = y;
        gridPane.add(component, c);
    }

    JFrame getFrame() {
        return frame;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JPanel getGridPane() {
        return gridPane;
    }

    public JLabel getThreeLabel() {
        return threeLabel;
    }
}
