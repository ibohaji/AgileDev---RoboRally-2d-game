package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameConfiguration;
import App.RoborallyApplication.Views.Menus.LobbyAiView;
import App.RoborallyApplication.Views.Menus.LobbyRegularView;
import App.RoborallyApplication.Views.Menus.MainMenuView;
import Utils.MusicPlayer;

public class MainMenuController {

    private final ApplicationController applicationController;
    private final MainMenuView view;

    public MainMenuController(ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.view = new MainMenuView(this);
        MusicPlayer.getInstance().openLoop();
        if(MusicPlayer.getInstance().checkLoopPlay()) {
            MusicPlayer.getInstance().playLoop("lobbyMusic.wav");
        }
    }

    public void userClickPlay(EnumDifficulty difficulty, int nrOfPlayers, boolean isRegular){
        LGameConfiguration gameConf = new LGameConfiguration(nrOfPlayers, difficulty, isRegular);
        LobbyController lobbyController = new LobbyController(this.applicationController, isRegular,gameConf);
        if(isRegular){
            applicationController.changePanel(new LobbyRegularView(lobbyController, gameConf));
        } else {
            applicationController.changePanel(new LobbyAiView(lobbyController, gameConf));
        }
    }

    public void display() {
        applicationController.changePanel(view);
    }

}
