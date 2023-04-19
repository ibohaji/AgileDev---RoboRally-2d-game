package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.LGameConfiguration;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyRegularView extends LobbyView {

    private final LGameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    ArrayList<String> playerNames;
    public LobbyRegularView(LobbyController lobbyController, LGameConfiguration gameConfiguration){
        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
        createView();
    }

    private void createView() {
        playerNames = new ArrayList<>();
        for (int i = 1; i <= gameConfiguration.getNrOfPlayers(); i++) {
            JPanel namePanel = new JPanel(new BorderLayout());
            namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
            namePanel.setPreferredSize(new Dimension(100, 50));
            namePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
           // namePanel.add(new JLabel(String.valueOf(i)), new GridBagConstraintsBuilder(i,0).build());
            JTextField playerNameField = new JTextField("player" +" "+ String.valueOf(i));
            namePanel.add(playerNameField,new GridBagConstraintsBuilder(1,i).build());
            add(namePanel, new GridBagConstraintsBuilder(i,0).build());
        }
        JButton startGameButton = new JButton("START GAME");
        startGameButton.setFont(Fonts.LARGE);
        startGameButton.addActionListener(e -> {
            //i want to retrive the userInput names from the namePanel above
                Component[] components = getComponents();
                for(Component component: components){
                    if(component instanceof JPanel){
                        JPanel panel = (JPanel) component;
                        Component[] subComponents = panel.getComponents();
                        for(Component subComponent: subComponents){
                            if(subComponent instanceof JTextField){
                                JTextField textField = (JTextField) subComponent;
                                playerNames.add(textField.getText());

                            }
                        }
                    }
                }

            lobbyController.userClickStartGame(gameConfiguration);
        });
        add(startGameButton);
    }


}
