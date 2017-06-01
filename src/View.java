import javax.swing.*;

import PhoneBook.Contact;
import PhoneBook.ContactPanel;
import PhoneBook.Phonebook;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
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

    private final JFrame frame;
    private final JButton[][] buttons;

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

        exitItem.addActionListener(event -> notifyListeners(ViewListener::onExit, event));

        // Add Menu
        pane.add(menuBar, BorderLayout.NORTH);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(StatisticsItem);
        gameMenu.add(OptionsItem);
        OptionsItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Options option = new Options(View.this);
				option.setVisible(true);
				/*if (option.isClickedOK())
				{
				//-----------------------------
				}*/
				//option.dispose();
			}
        });
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        JPanel gridPane = new JPanel(new GridBagLayout());

        pane.add(gridPane, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
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

    public JFrame getFrame() {
        return frame;
    }
}
