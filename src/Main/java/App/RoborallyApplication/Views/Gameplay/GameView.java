package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public abstract class GameView extends JPanel {
    protected final GameController controller;
    protected final LGameConfiguration gameConfiguration;
    protected LGameBrain gameBrain;

    public GameView(GameController controller, LGameBrain gameBrain){
        this.controller = controller;
        this.gameBrain = gameBrain;
        this.gameConfiguration = gameBrain.getGameConfig();

    }
    protected JLabel generateGameNameLabel(){
        JLabel gameNameLabel = new JLabel("ROBORALLY", SwingConstants.CENTER);
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }




}
