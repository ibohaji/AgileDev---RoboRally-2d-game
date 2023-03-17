package App.GameLogic;

import App.Domain.Enums.DifficultyEnum;
import App.Domain.GameConfiguration;
import App.Domain.GamePhase;
import App.Domain.Gameboard;

public class GameBrain {

    private final GameConfiguration gameConfig;
    private final Gameboard gameboard;

    private GamePhase currentGamePhase;
    public GameBrain(int nrOfPlayers, DifficultyEnum difficulty, int boardSizeHorizonal, int boardSizeVertical){
        gameConfig = new GameConfiguration(nrOfPlayers, difficulty, boardSizeHorizonal, boardSizeVertical);
        gameboard = new Gameboard(gameConfig);
    }

    protected void run(){
        // TODO
        // gamerunning logic
    }
}
