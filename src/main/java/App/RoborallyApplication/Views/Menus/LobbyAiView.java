package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.LGameConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LobbyAiView extends LobbyView {

    private final LGameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    private ArrayList<Tuple<String,Boolean>> isAI = new ArrayList<>();
    private ArrayList<Tuple<String,String>> playName = new ArrayList<>();
    private ArrayList<Tuple<String,Boolean>> info = new ArrayList<>();
    JPanel namePanel;

    public LobbyAiView(LobbyController lobbyController, LGameConfiguration gameConfiguration) {
        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
        createView();
    }

    private void createView(){
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
                ActionListener actionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                        boolean selected = abstractButton.getModel().isSelected();
                    }
                };
                checkBox.addActionListener(actionListener);
            }
        }
        JButton exitButton = exitLobbyButton();
        JButton Button = new JButton("START GAME");
        Button.setFont(Fonts.LARGE);
        Button.addActionListener(e -> {
            Button.setEnabled(false);
            exitButton.setEnabled(false);
            Component[] components = getComponents();
            isAI.add(new Tuple<>(null, true));
            for(Component component: components){
                if (component instanceof JCheckBox){
                    JCheckBox checkBox = (JCheckBox) component;
                    isAI.add(new Tuple<>(null, !checkBox.isSelected()));
                }
                if(component instanceof JPanel){
                    JPanel panel = (JPanel) component;
                    Component[] subComponents = panel.getComponents();
                    for(Component subComponent: subComponents){
                        if(subComponent instanceof JTextField){
                            JTextField textField = (JTextField) subComponent;
                            playName.add(new Tuple<>(textField.getText(), null));
                        }
                    }
                }
            }
            for (int i = 0;i < gameConfiguration.getNrOfPlayers();i++){
                info.add(new Tuple<>(playName.get(i).first(),isAI.get(i).second()));
            }
            gameConfiguration.createPlayersFromLobby(info);
            lobbyController.userClickStartGame(gameConfiguration);
        });
        add(Button,  new GridBagConstraintsBuilder(0,2).gridWidth(gameConfiguration.getNrOfPlayers()).build());
        add(exitButton,  new GridBagConstraintsBuilder(0,3).gridWidth(gameConfiguration.getNrOfPlayers()).build());

    }
    private  JButton exitLobbyButton(){
        JButton exitGame = new JButton("EXIT");
        exitGame.setFont(Fonts.LARGE);
        exitGame.addActionListener(e -> {
            lobbyController.userClickExit();
        });
        return exitGame;
    }
}
