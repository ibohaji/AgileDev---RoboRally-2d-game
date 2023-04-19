package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Views.Menus.LobbyAiView;
import App.RoborallyApplication.Views.Menus.LobbyRegularView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

public class MainMenuController {

    private final ApplicationController applicationController;
    private final MainMenuView view;

    public MainMenuController(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.view = new MainMenuView(this);
    }

    public void userClickPlay(EnumDifficulty difficulty, int nrOfPlayers, boolean isRegular){
        LGameConfiguration gameConf = new LGameConfiguration(nrOfPlayers, difficulty);
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
