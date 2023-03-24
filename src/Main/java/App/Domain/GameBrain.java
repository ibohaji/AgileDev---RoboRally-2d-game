package App.Domain;

import App.DTO.GameBrainDTO;
import App.DTO.PlayerDTO;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.DirectionEnum;
import App.Domain.GameConfiguration;
import App.Domain.GamePhase;
import App.Domain.Gameboard;
import Utils.JsonHelper;

import java.util.ArrayList;

public class GameBrain implements serializable{

    private GameConfiguration gameConfig;
    private Gameboard gameboard = null;

    private int nrOfPlayers;
    private GamePhase currentGamePhase;

    public GameBrain(){

    }
    public GameBrain(int nrOfPlayers, DifficultyEnum difficulty){
        gameConfig = new GameConfiguration(nrOfPlayers, difficulty);
        this.nrOfPlayers = nrOfPlayers;
        createGameboard();
        ArrayList<Player> players = createPlayers();
        ArrayList<Robot> robots = createRobots(players);
        addRobotsToGameboard(robots);

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

    public GameConfiguration getGameConfig(){
        return this.gameConfig;
    }

    private void createGameboard(){
        this.gameboard = new Gameboard(gameConfig);
    }

    private ArrayList<Player> createPlayers(){
        //TODO
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < this.nrOfPlayers; i++) {
            players.add(new Player(((Integer)i).toString()));
        }
        return players;
    }

    private ArrayList<Robot> createRobots(ArrayList<Player> players){
        ArrayList<Robot> robots = new ArrayList<>();
        // TODO
        for (Player player: players) {
            Robot newRobot = new Robot();
            newRobot.SetDirection(DirectionEnum.NORTH);
            robots.add(newRobot);
        }
        return robots;
    }

    private void addRobotsToGameboard(ArrayList<Robot> robots){
        this.gameboard.addRobots(robots);
    }
    private String serializeGameToJson(){
        GameBrainDTO gameBrainDTO = new GameBrainDTO(this.gameboard, this.gameConfig, this.currentGamePhase);
        return JsonHelper.serializeObjectToJson(gameBrainDTO);
    }

    @Override
    public String toJson() {
        GameBrainDTO gameBrainDTO = new GameBrainDTO(this.gameboard, this.gameConfig, this.currentGamePhase);
        return JsonHelper.serializeObjectToJson(gameBrainDTO);
    }

    public Tile getTileFromCoordinate(Integer x, Integer y){
        for (Tile tile: gameboard.getTiles()) {
            if(tile.getCoordinates().first().equals(x) && tile.getCoordinates().second().equals(x)){
                return tile;
            }
        }
        return null;
    }
}
