package App.Views.Gameplay;

import App.Controllers.GameController.GameController;
import App.Controllers.MainMenuController.MainMenuController;
import App.Domain.GameBrain;
import App.Domain.GameConfiguration;
import Utils.Tuple;

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

}
