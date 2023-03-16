package App.Views.Gameplay;

import App.Controllers.GameController.GameController;
import App.Controllers.MainMenuController.MainMenuController;

import javax.swing.*;

public abstract class GameView extends JPanel {
    private final GameController controller;

    public GameView(GameController controller){
        this.controller = controller;
    }

}
