package App.Domain;

import Utils.Tuple;

public class GameConfiguration {

    private final int nrOfPlayers;
    private final int nrOfCheckpoints;
    private final Tuple<Integer, Integer> boardDimensions;

    private final DifficultyEnum difficulty;

    public GameConfiguration(int nrOfPlayers, DifficultyEnum difficulty, int boardSizeHorizontal, int boardSizeVertical){
        this.nrOfPlayers = nrOfPlayers;
        this.difficulty = difficulty;
        if(difficulty.ordinal() == DifficultyEnum.EASY.ordinal()){
            nrOfCheckpoints =  1;
        } else {
            nrOfCheckpoints = 3;
        }
        boardDimensions = new Tuple<>(boardSizeHorizontal, boardSizeVertical);
    }

    /**
     * @return board dimensions as tuple where 'first' is horizontal value and 'second' is vertical value
     */
    protected Tuple<Integer, Integer> getBoardDimensions(){
        return this.boardDimensions;
    }
}
