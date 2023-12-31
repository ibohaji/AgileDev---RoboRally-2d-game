package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;
import App.RoborallyApplication.Views.Menus.LobbyAiView;
import App.RoborallyApplication.Views.Menus.LobbyRegularView;
import App.RoborallyApplication.Views.Menus.LobbyView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

public class LobbyController {

    private final ApplicationController applicationController;
    private final LobbyView view;
    // Test purpose only
    private GameController t_gameController;

    public LobbyController(ApplicationController applicationController, boolean isRegular, LGameConfiguration gameConfiguration){
        this.applicationController = applicationController;
        if(isRegular){
            this.view = new LobbyRegularView(this, gameConfiguration);
        } else {
            this.view = new LobbyAiView(this, gameConfiguration);
        }
    }

    public void userClickStartGame(LGameConfiguration gameConfiguration){
        LGameBrain gameBrain = new LGameBrain(gameConfiguration);
        GameController gameController = new GameController(applicationController, gameBrain);
        this.t_gameController = gameController;
    }

    public void userClickExit(){
        this.applicationController.changePanel(new MainMenuView(new MainMenuController(this.applicationController)));
    }

    // Test purpose only
    public GameController getGameController() {
        return this.t_gameController;
    }
}
