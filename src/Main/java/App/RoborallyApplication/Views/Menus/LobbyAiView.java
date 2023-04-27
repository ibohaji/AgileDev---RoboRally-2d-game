package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.LGameConfiguration;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyAiView extends LobbyView{

    private final LGameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    ArrayList<Tuple<String, Boolean>> playersInformation;
    JPanel namePanel;

    public LobbyAiView(LobbyController lobbyController, LGameConfiguration gameConfiguration){
        //TODO
        // need checkbox after every name panel to check whether AI player or not

        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
        createView();
    }

    private void createView() {
        playersInformation = new ArrayList<>();
        JPanel namePanel;
        int nrOfPlayers = gameConfiguration.getNrOfPlayers();
        namePanel = new JPanel(new BorderLayout());
        namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
        namePanel.setPreferredSize(new Dimension(100, 50));
        namePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        // namePanel.add(new JLabel(String.valueOf(i)), new GridBagConstraintsBuilder(i,0).build());
        JTextField aiNameField = new JTextField("Player" +" "+ String.valueOf(1));
        namePanel.add(aiNameField,new GridBagConstraintsBuilder(1,1).build());
        add(namePanel, new GridBagConstraintsBuilder(1,0).build());



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
                            // TODO fix this to say true/false
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
