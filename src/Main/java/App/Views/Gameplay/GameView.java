package App.Views.Gameplay;

import App.Controllers.GameController.GameController;
import App.Model.GameRunning.GameBrain;
import App.Model.GameRunning.GameConfiguration;

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
