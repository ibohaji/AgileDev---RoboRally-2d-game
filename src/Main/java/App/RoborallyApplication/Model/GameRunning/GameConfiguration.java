package App.RoborallyApplication.Model.GameRunning;

import Utils.Tuple;

public class GameConfiguration {

    private final int nrOfPlayers;
    private final Tuple<Integer, Integer> boardDimensions;
    private final DifficultyEnum difficulty;
    private boolean isRegular;
    public GameConfiguration(int nrOfPlayers, DifficultyEnum difficulty){
        this.nrOfPlayers = nrOfPlayers;
        this.difficulty = difficulty;
        boardDimensions = difficulty.getDimensions();
    }

    /**
     * @return board dimensions as tuple where 'first' is horizontal value and 'second' is vertical value
     */

    public Tuple<Integer, Integer> getBoardDimensions(){
        return this.boardDimensions;
    }

    public void setAIOpponent(){
        isRegular = false;
    }

    public boolean getGameMode(){
        return isRegular;
    }

    public int getNrOfPlayers(){return this.nrOfPlayers;}

    public DifficultyEnum getDifficulty(){return this.difficulty;}

    public int getScalingSizeForTile(){
        int scaling = 0;
        switch (difficulty){
            case EASY -> scaling = 60;
            case MEDIUM -> scaling = 45;
            case HARD -> scaling = 45;
        }
        return scaling;
    }
}
