package App.Controllers.GameController;

import App.Controllers.ApplicationController.ApplicationController;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.GameBrain;
import App.Domain.Tile;
import App.Views.Gameplay.GameStartView;
import App.Views.Gameplay.GameView;
import Utils.MapLoadingHelper;

import java.util.ArrayList;

public class GameController {
    private final ApplicationController applicationController;
    protected final GameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, GameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;
        this.view = new GameStartView(this, gameBrain);
    }


    protected void display() {
        applicationController.changePanel(view);
    }

    private void changeView(GameView viewToChangeTo){
        this.view = viewToChangeTo;
    }


    public static void main(String[] args) {
        // Method to see the view
        var game = new GameBrain(6, DifficultyEnum.HARD);
        ArrayList<Tile> map = MapLoadingHelper.loadMap(DifficultyEnum.HARD);
        var app = new ApplicationController();
        var gameController = new GameController(app, game);
        gameController.display();
    }


}
