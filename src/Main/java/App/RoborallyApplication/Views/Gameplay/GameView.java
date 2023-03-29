package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import Utils.Fonts;
import Utils.ImageUtils;

import javax.swing.*;

public abstract class GameView extends JPanel {
    private final GameController controller;
    protected final GameConfiguration gameConfiguration;
    protected GameBrain gameBrain;

    public GameView(GameController controller, GameBrain gameBrain){
        this.controller = controller;
        this.gameBrain = gameBrain;
        this.gameConfiguration = gameBrain.getGameConfig();
    }

    protected void getGameboardView(){

    }

    protected JLabel generateGameNameLabel(){
        JLabel gameNameLabel = new JLabel("ROBORALLY");
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }

}
