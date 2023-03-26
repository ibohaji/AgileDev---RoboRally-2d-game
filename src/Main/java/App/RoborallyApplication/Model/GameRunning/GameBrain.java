package App.RoborallyApplication.Model.GameRunning;

import App.DTO.GameBrainDTO;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.GamePhase;
import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.IReloadable;
import Utils.JsonHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class GameBrain implements IReloadable {

    private UUID id;

    private GameConfiguration gameConfig;
    private Gameboard gameboard = null;

    private ArrayList<Player> players;
    private int nrOfPlayers;
    private GamePhase currentGamePhase;

    public GameBrain(){

    }
    public GameBrain(int nrOfPlayers, DifficultyEnum difficulty){
        this.id = UUID.randomUUID();
        gameConfig = new GameConfiguration(nrOfPlayers, difficulty);
        this.nrOfPlayers = nrOfPlayers;
        createGameboard();
        this.players = createPlayers();
        ArrayList<Robot> robots = createRobots(players);
        addRobotsToGameboard(robots);
        currentGamePhase = GamePhase.ROUND_START;
    }

    private void playRound(){

    }

    private void getProgrammingPhaseChoices(){

    }

    private void makeMove(Player player, ProgrammingCard card){
        // TODO
    }

    private boolean canRobotMakeMoveToCoordinate(){
        // TODO
        return true;
    }

    private void movePlayerWithCollision(DirectionEnum hitDirection){
        // if overboard then back to beginning

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
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < this.nrOfPlayers; i++) {
            players.add(new Player("player" + (Integer.toString(i + 1))));
        }
        return players;
    }

    private ArrayList<Robot> createRobots(ArrayList<Player> players){
        ArrayList<Robot> robots = new ArrayList<>();
        for (Player player: players) {
            Robot newRobot = new Robot();
            newRobot.SetDirection(DirectionEnum.NORTH);
            player.assignRobot(newRobot);
            robots.add(newRobot);
        }
        return robots;
    }

    private void addRobotsToGameboard(ArrayList<Robot> robots){
        this.gameboard.addRobots(robots);
    }

    @Override
    public String toJson() {
        GameBrainDTO gameBrainDTO = new GameBrainDTO(this.gameboard, this.gameConfig, this.currentGamePhase, this);
        return JsonHelper.serializeObjectToJson(gameBrainDTO);
    }
    @Override
    public UUID getID() {
        return this.id;
    }

    public Tile getTileFromCoordinate(Integer x, Integer y){
        for (Tile tile: gameboard.getTiles()) {
            if(tile.getCoordinates().first().equals(x) && tile.getCoordinates().second().equals(y)){
                return tile;
            }
        }
        return null;
    }

    public Robot getRobotFromCoordinate(Integer x, Integer y){
        for (Robot robot: gameboard.getRobots()) {
            if(robot.getCords().x == x && robot.getCords().y == y){
                return robot;
            }
        }
        return null;
    }

    public boolean isTileOccupiedByRobot(int xCoordinate, int yCoordinate){
        for (Robot robot:this.gameboard.getRobots()) {
            Point location = robot.getCords();
            if(location.x == xCoordinate && location.y == yCoordinate){
                return true;
            }
        }
        return false;
    }

    // TODO
    // initalize map should go here
    // reloading of a game should go here
}
