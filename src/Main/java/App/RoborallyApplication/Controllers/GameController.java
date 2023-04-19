package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Views.Gameplay.GameBoardView;
import App.RoborallyApplication.Views.Gameplay.GameView;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;

public class GameController {
    private final ApplicationController applicationController;
    protected final LGameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, LGameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;
        this.view = new GameBoardView(this, gameBrain);
        this.view = new ProgrammingPhaseView(this, gameBrain);
    }

    protected void display() {
        applicationController.changePanel(view);
    }

    private void changeView(GameView viewToChangeTo){
        this.view = viewToChangeTo;
    }


    public static void main(String[] args) {
        // Method to see the view
        var game = new LGameBrain(2, EnumDifficulty.EASY);
        var app = new ApplicationController();
        var gameController = new GameController(app, game);
        gameController.display();
    }


}
