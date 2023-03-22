package App.Domain;

import App.Domain.Enums.DifficultyEnum;
import Utils.Tuple;

public class GameConfiguration {

    private final int nrOfPlayers;
    private final Tuple<Integer, Integer> boardDimensions;

    private final DifficultyEnum difficulty;

    public GameConfiguration(int nrOfPlayers, DifficultyEnum difficulty){
        this.nrOfPlayers = nrOfPlayers;
        this.difficulty = difficulty;
        boardDimensions = difficulty.getDimensions();
    }

    /**
     * @return board dimensions as tuple where 'first' is horizontal value and 'second' is vertical value
     */
    protected Tuple<Integer, Integer> getBoardDimensions(){
        return this.boardDimensions;
    }
}
