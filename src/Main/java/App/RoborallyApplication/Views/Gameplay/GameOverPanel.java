package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.*;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;
import Utils.MusicPlayer;

import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends GameView {

    private GameController gameController;
    private String winner = null;
    public GameOverPanel(GameController controller, LGameBrain gameBrain) {
        super(controller, gameBrain);
        if(gameBrain.isThereAWinner()){
            winner = gameBrain.getPlayerWhoWon();
        }
        createView();
    }

    public void createView() {
        MusicPlayer.getInstance().closeLoop();
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

        if(winner == null){
            scoreLabel = new JLabel("NO WINNER");
            MusicPlayer.getInstance().play("App/Resources/Music/lose.wav");
        } else {
            scoreLabel = new JLabel("Winner is : " + winner);
            MusicPlayer.getInstance().play("App/Resources/Music/win.wav");
        }

        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(scoreLabel, constraints);

        JButton closeButton = new JButton("Close Game");
        closeButton.addActionListener(e -> {
                    controller.quitGame();
                });
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(50, 0, 0, 0);
        add(closeButton, constraints);

        setOpaque(false);
        setBackground(new Color(0, 0, 0, 128));
    }
}