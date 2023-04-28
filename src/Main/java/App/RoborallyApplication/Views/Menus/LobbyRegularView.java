package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.LGameConfiguration;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyRegularView extends LobbyView {

    private final LGameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    ArrayList<Tuple<String, Boolean>> playersInformation;

    public LobbyRegularView(LobbyController lobbyController, LGameConfiguration gameConfiguration){
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
            namePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
           // namePanel.add(new JLabel(String.valueOf(i)), new GridBagConstraintsBuilder(i,0).build());
            JTextField playerNameField = new JTextField("player" +" "+ String.valueOf(i+1));
            namePanel.add(playerNameField,new GridBagConstraintsBuilder(1,i).build());
            add(namePanel, new GridBagConstraintsBuilder(i,0).build());
        }

        JButton startGameButton = new JButton("START GAME");
        startGameButton.setFont(Fonts.LARGE);
        startGameButton.addActionListener(e -> {
            startGameButton.setEnabled(false);
            //i want to retrive the userInput names from the namePanel above
                Component[] components = getComponents();
                for(Component component: components){
                    if(component instanceof JPanel){
                        JPanel panel = (JPanel) component;
                        Component[] subComponents = panel.getComponents();
                        for(Component subComponent: subComponents){
                            if(subComponent instanceof JTextField){
                                JTextField textField = (JTextField) subComponent;
                                playersInformation.add(new Tuple<>(textField.getText(), true));
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
