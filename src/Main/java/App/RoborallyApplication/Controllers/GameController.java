package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Views.Gameplay.GameBoardView;
import App.RoborallyApplication.Views.Gameplay.GameView;

public class GameController {
    private final ApplicationController applicationController;
    protected final GameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, GameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;
        this.view = new GameBoardView(this, gameBrain);
    }


    protected void display() {
        applicationController.changePanel(view);
    }

    private void changeView(GameView viewToChangeTo){
        this.view = viewToChangeTo;
    }


    public static void main(String[] args) {
        // Method to see the view
        var game = new GameBrain(2, DifficultyEnum.EASY);
        // ArrayList<Tile> map = MapLoadingHelper.loadMap(DifficultyEnum.HARD);
        var app = new ApplicationController();
        var gameController = new GameController(app, game);
        gameController.display();
    }


}
