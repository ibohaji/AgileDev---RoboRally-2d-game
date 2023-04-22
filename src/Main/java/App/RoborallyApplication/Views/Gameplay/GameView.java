package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;

public abstract class GameView extends JPanel {
    private final GameController controller;
    protected final LGameConfiguration gameConfiguration;
    protected LGameBrain gameBrain;

    public GameView(GameController controller, LGameBrain gameBrain){
        this.controller = controller;
        this.gameBrain = gameBrain;
        this.gameConfiguration = gameBrain.getGameConfig();

    }

    protected void getGameboardView(){

    }



    protected void getUserCardDeckView(){
        // TODO we don't need to deal cards manually by calling .dealCardsForRound()
        UserCardDeckView userCardDeckView = new UserCardDeckView(controller, gameBrain);
        add(userCardDeckView, new GridBagConstraintsBuilder(0, 1).anchor(GridBagConstraints.NORTH).build());
    }


    protected JLabel generateGameNameLabel(){
        JLabel gameNameLabel = new JLabel("ROBORALLY");
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }




}
