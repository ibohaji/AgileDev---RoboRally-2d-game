package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Views.Menus.LobbyAiView;
import App.RoborallyApplication.Views.Menus.LobbyRegularView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

public class MainMenuController {

    private final ApplicationController applicationController;
    private final MainMenuView view;
    // Test purpose only
    private LobbyController t_lobbyController;
    private LGameConfiguration t_gameConfiguration;

    public MainMenuController(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.view = new MainMenuView(this);
    }

    public void userClickPlay(EnumDifficulty difficulty, int nrOfPlayers, boolean isRegular){
        LGameConfiguration gameConf = new LGameConfiguration(nrOfPlayers, difficulty, isRegular);
        LobbyController lobbyController = new LobbyController(this.applicationController, isRegular,gameConf);
        if(isRegular){
            applicationController.changePanel(new LobbyRegularView(lobbyController, gameConf));
        } else {
            applicationController.changePanel(new LobbyAiView(lobbyController, gameConf));
        }
        // Test purpose only
        this.t_lobbyController = lobbyController;
        this.t_gameConfiguration = gameConf;
    }

    public void display() {
        applicationController.changePanel(view);
    }

    // Test purpose only
    public LobbyController getLobbyController() {
        return this.t_lobbyController;
    }
    public LGameConfiguration getGameConfiguration() {
        return this.t_gameConfiguration;
    }
}
