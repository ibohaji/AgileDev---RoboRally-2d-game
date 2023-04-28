package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.*;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends GameView {

    public GameOverPanel(GameController controller, LGameBrain gameBrain) {
        super(controller, gameBrain);
        String winner = gameBrain.getPlayerWhoWon();
        createView(winner,controller,gameBrain);
    }

    public void createView(String winner,GameController controller,LGameBrain brain) {
        JLabel scoreLabel;
        setLayout(new GridBagLayout());
        //Game over Label
        JLabel gameOverLabel = new JLabel("Game over!");
        gameOverLabel.setFont(new Font("Serif", Font.BOLD, 36));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(50, 0, 50, 0);
        add(gameOverLabel, constraints);

        scoreLabel = new JLabel("Winner is : " + winner);
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(scoreLabel, constraints);

        JButton closeButton = new JButton("Close Game");
        closeButton.addActionListener(e -> controller.quitGame());
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(50, 0, 0, 0);
        add(closeButton, constraints);

        setOpaque(false);
        setBackground(new Color(0, 0, 0, 128));
    }
}