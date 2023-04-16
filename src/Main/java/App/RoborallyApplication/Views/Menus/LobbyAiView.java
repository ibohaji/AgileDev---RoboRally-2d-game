package App.RoborallyApplication.Views.Menus;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;

public class LobbyAiView extends LobbyView{

    private final GameConfiguration gameConfiguration;
    private final LobbyController lobbyController;
    public LobbyAiView(LobbyController lobbyController, GameConfiguration gameConfiguration){
        this.lobbyController = lobbyController;
        this.gameConfiguration = gameConfiguration;
    }
}
