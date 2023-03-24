package App.Domain;

import App.Domain.Enums.DifficultyEnum;
import App.Domain.GameConfiguration;
import App.Domain.GamePhase;
import App.Domain.Gameboard;

import java.util.ArrayList;

public class GameBrain {

    private final GameConfiguration gameConfig;
    private Gameboard gameboard = null;

    private final int nrOfPlayers;
    private GamePhase currentGamePhase;
    public GameBrain(int nrOfPlayers, DifficultyEnum difficulty){
        gameConfig = new GameConfiguration(nrOfPlayers, difficulty);
        this.nrOfPlayers = nrOfPlayers;
        createGameboard();
        // create players
        // create robots for each player
        // addRobotsToGameBoard
    }

    protected void run(){
        // TODO
        try {
            if(gameboard.getRobots().size() == 0){
                throw new RuntimeException("No robots found");
            }
        } catch (RuntimeException e){
            e.printStackTrace();
        }
        
        // gamerunning logic
        getMovesFromPlayers();
        playOutRound();
    }

    private void playOutRound(){

    }

    private void getMovesFromPlayers(){

    }

    private void createGameboard(){
        this.gameboard = new Gameboard(gameConfig);
    }

    private void createPlayers(){
        //TODO
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < this.nrOfPlayers; i++) {
            players.add(new Player(((Integer)i).toString()));
        }
    }

    private ArrayList<Robot> createRobots(){
        // TODO
        return new ArrayList<>();
    }

    private void addRobotsToGameboard(){
        //TODO
    }
}
