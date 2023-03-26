package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;

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

}
