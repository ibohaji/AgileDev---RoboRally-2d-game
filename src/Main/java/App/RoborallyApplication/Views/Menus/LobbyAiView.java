package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.LGameConfiguration;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyAiView extends LobbyView {

    private final LGameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    ArrayList<Tuple<String, Boolean>> playersInformation;
    JPanel namePanel;

    public LobbyAiView(LobbyController lobbyController, LGameConfiguration gameConfiguration) {
        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
        createView();
    }

    private void createView() {
        playersInformation = new ArrayList<>();
        for (int i = 0; i < gameConfiguration.getNrOfPlayers(); i++) {
            JPanel namePanel = new JPanel(new BorderLayout());
            namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
            namePanel.setPreferredSize(new Dimension(100, 50));
            namePanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
            JTextField playerNameField = new JTextField("player" +" "+ String.valueOf(i+1));
            namePanel.add(playerNameField,new GridBagConstraintsBuilder(1,i).build());
            add(namePanel, new GridBagConstraintsBuilder(i,0).build());

            if (i != 0){
                JCheckBox checkBox = new JCheckBox("is AI");
                checkBox.setBounds(100,100, 50,50);
                add(checkBox, new GridBagConstraintsBuilder(i,1).build());
            }
        }

        JButton startGameButton = new JButton("START GAME");
        startGameButton.setFont(Fonts.LARGE);
        startGameButton.addActionListener(e -> {
            Component[] components = getComponents();
            for(Component component: components){
                if(component instanceof JPanel){
                    JPanel panel = (JPanel) component;
                    Component[] subComponents = panel.getComponents();
                    for(Component subComponent: subComponents){
                        if(subComponent instanceof JTextField){
                            JTextField textField = (JTextField) subComponent;
                            playersInformation.add(new Tuple<>(textField.getText(),true));
                        }
                    }
                }
            }
            gameConfiguration.createPlayersFromLobby(playersInformation);
            lobbyController.userClickStartGame(gameConfiguration);
        });
        add(startGameButton);

    }
}
