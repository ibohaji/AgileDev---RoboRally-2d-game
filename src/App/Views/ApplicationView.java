package App.Views;

import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;
public class ApplicationView extends JFrame{
    private final Container contentPane;

    private JPanel mainPanel = null;

    public ApplicationView() {
        setTitle("RoboRally");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        // maximize the window
        setMinimumSize(new Dimension(600, 700));
        setSize(800, 600);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        contentPane = getContentPane();
        addWindowStateListener(e -> {
            if (e.getNewState() == Frame.NORMAL) pack();
        });
    }

    public void changePanel(JPanel newPanel) {
        contentPane.removeAll();
        mainPanel = newPanel;
        contentPane.add(newPanel, new GridBagConstraintsBuilder(0, 0).weight(1, 1).fill(GridBagConstraints.BOTH).build());
        revalidate();
        repaint();
    }


}
