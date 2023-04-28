package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.LGameConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App.RoborallyApplication.Model.LPlayer;
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
    private ArrayList<Tuple<String,Boolean>> isAI = new ArrayList<>();
    JPanel namePanel;

    public LobbyAiView(LobbyController lobbyController, LGameConfiguration gameConfiguration) {
        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
        createView();
    }

    private void createView(){
        playersInformation = new ArrayList<>();
        for (int i = 0; i < gameConfiguration.getNrOfPlayers(); i++) {
            JPanel namePanel = new JPanel(new BorderLayout());
            namePanel.setLayout(new BoxLayout(namePanel,BoxLayout.X_AXIS));
            namePanel.setPreferredSize(new Dimension(100, 50));
            namePanel.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
            JTextField playerNameField = new JTextField("player" +" "+ String.valueOf(i+1));
            namePanel.add(playerNameField,new GridBagConstraintsBuilder(1,i).build());
            add(namePanel, new GridBagConstraintsBuilder(i,0).build());
            isAI.add(new Tuple<>(playerNameField.getText(), true));

            if (i != 0){
                JCheckBox checkBox = new JCheckBox("is AI");
                checkBox.setBounds(100,100, 50,50);
                add(checkBox, new GridBagConstraintsBuilder(i,1).build());
                ActionListener actionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                        boolean selected = abstractButton.getModel().isSelected();
                        isAI.set(isAI.indexOf(new Tuple<>(playerNameField.getText(), true)), new Tuple<>(playerNameField.getText(), !selected));
                    }
                };
                checkBox.addActionListener(actionListener);
            }

        }
        JButton startGameButton = new JButton("START GAME");
        startGameButton.setFont(Fonts.LARGE);
        startGameButton.addActionListener(e -> {
            startGameButton.setEnabled(false);
            gameConfiguration.createPlayersFromLobby(isAI);
            lobbyController.userClickStartGame(gameConfiguration);
        });
        add(startGameButton);
    }
}
