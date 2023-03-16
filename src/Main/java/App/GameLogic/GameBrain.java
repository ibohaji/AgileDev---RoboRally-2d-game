package App.GameLogic;

import App.Domain.DifficultyEnum;
import App.Domain.GameConfiguration;
import App.Domain.Gameboard;

public class GameBrain {

    private final GameConfiguration gameConfig;
    private final Gameboard gameboard;
    public GameBrain(int nrOfPlayers, DifficultyEnum difficulty, int boardSizeHorizonal, int boardSizeVertical){
        gameConfig = new GameConfiguration(nrOfPlayers, difficulty.EASY, boardSizeHorizonal, boardSizeVertical);
        gameboard = new Gameboard(gameConfig);
    }

    protected void run(){

    }
}
