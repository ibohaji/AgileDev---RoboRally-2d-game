package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;
import App.RoborallyApplication.Views.Menus.LobbyAiView;
import App.RoborallyApplication.Views.Menus.LobbyRegularView;
import App.RoborallyApplication.Views.Menus.LobbyView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

public class LobbyController {

    private final ApplicationController applicationController;
    private final LobbyView view;
    public LobbyController(ApplicationController applicationController, boolean isRegular, GameConfiguration gameConfiguration){
        this.applicationController = applicationController;
        if(isRegular){
            this.view = new LobbyRegularView(this, gameConfiguration);
        } else {
            this.view = new LobbyAiView(this, gameConfiguration);
        }
    }

    public void userClickStartGame(GameConfiguration gameConfiguration){
        GameBrain gameBrain = new GameBrain(gameConfiguration.getNrOfPlayers(), gameConfiguration.getDifficulty());
        GameController gameController = new GameController(applicationController, gameBrain);
        applicationController.changePanel(new ProgrammingPhaseView(gameController, gameBrain));
    }
}
