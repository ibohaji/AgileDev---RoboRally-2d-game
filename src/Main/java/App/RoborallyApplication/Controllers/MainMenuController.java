package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Controllers.ApplicationController;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Views.Menus.LobbyAiView;
import App.RoborallyApplication.Views.Menus.LobbyRegularView;
import App.RoborallyApplication.Views.Menus.LobbyView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

public class MainMenuController {

    private final ApplicationController applicationController;
    private final MainMenuView view;

    public MainMenuController(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.view = new MainMenuView(this);
    }

    public void userClickPlay(DifficultyEnum difficulty, int nrOfPlayers, boolean isRegular){
        GameConfiguration gameConf = new GameConfiguration(nrOfPlayers, difficulty);
        LobbyController lobbyController = new LobbyController(this.applicationController, isRegular,gameConf);
        if(isRegular){
            applicationController.changePanel(new LobbyRegularView(lobbyController, gameConf));
        } else {
            gameConf.setAIOpponent();
            applicationController.changePanel(new LobbyAiView(lobbyController, gameConf));
        }
    }

    public void display() {
        applicationController.changePanel(view);
    }
}
