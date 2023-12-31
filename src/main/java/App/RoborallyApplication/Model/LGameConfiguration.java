package App.RoborallyApplication.Model;

import Utils.Tuple;

import java.util.ArrayList;

public class LGameConfiguration {

    private final int nrOfPlayers;
    private final Tuple<Integer, Integer> boardDimensions;
    private final EnumDifficulty difficulty;
    private final ArrayList<LPlayer> players = new ArrayList<>();


    public LGameConfiguration(int nrOfPlayers, EnumDifficulty difficulty, boolean isRegular){
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
    public int getNrOfPlayers(){return this.nrOfPlayers;}
    public EnumDifficulty getDifficulty(){return this.difficulty;}

    /**
     * @param playerInfo Takes in a list of tuples, where the first element in tuple is playername and second element
     *                   is whether the player is AI or Human
     */
    public void createPlayersFromLobby(ArrayList<Tuple<String, Boolean>> playerInfo){
        for (Tuple<String, Boolean> info: playerInfo) {
            this.players.add(new LPlayer(info.first(), info.second()));
        }
    }
    public ArrayList<LPlayer> getPlayers(){
        return this.players;
    }
    public int getScalingSizeForTile(){
        int scaling = 0;
        switch (difficulty){
            case EASY -> scaling = 60;
            case MEDIUM, HARD -> scaling = 45;
        }
        return scaling;
    }
}
