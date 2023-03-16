package App.Controllers.GameController;

import App.Controllers.ApplicationController.ApplicationController;
import App.GameLogic.GameBrain;
import App.Views.Gameplay.GameView;

public class GameController {
    private final ApplicationController applicationController;
    private final GameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, GameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;
    }


    protected void display() {
        applicationController.changePanel(view);
    }

    private void changeView(GameView viewToChangeTo){
        this.view = viewToChangeTo;
    }


}
