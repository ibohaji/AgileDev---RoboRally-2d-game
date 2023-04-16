package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;

public class LobbyRegularView extends LobbyView {

    private final GameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    public LobbyRegularView(LobbyController lobbyController, GameConfiguration gameConfiguration){
        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
        createView();
    }

    private void createView() {
        for (int i = 0; i < gameConfiguration.getNrOfPlayers(); i++) {
            JPanel namePanel = new JPanel();
            namePanel.setLayout(new GridBagLayout());
            namePanel.add(new JLabel(String.valueOf(i)), new GridBagConstraintsBuilder(0,i).build());
            namePanel.add(new JTextField("player" + String.valueOf(i)), new GridBagConstraintsBuilder(1,i).build());
            add(namePanel, new GridBagConstraintsBuilder(0,i).build());
        }
        JButton startGameButton = new JButton("START GAME");
        startGameButton.setFont(Fonts.LARGE);
        startGameButton.addActionListener(e -> {
            lobbyController.userClickStartGame(gameConfiguration);
        });
        add(startGameButton);
    }

}
