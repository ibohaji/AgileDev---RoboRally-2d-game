package App.RoborallyApplication.Model.GameRunning;

import App.DTO.GameboardDTO;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.Enums.TileTypeEnum;
import App.RoborallyApplication.Model.GameObjects.Obstacle;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.iToDTO;
import Utils.JsonHelper;
import Utils.Tuple;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

public class Gameboard implements iToDTO {
    private UUID id;
    private UUID gameBrainId;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Robot> robots = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private GameBrain gameBrain;
    int mapTile[][];

    public Gameboard(){

    }
    public Gameboard(GameBrain brain){
        gameBrain = brain;
        gameBrainId = gameBrain.getID();
    }

    public UUID getGameBrainId(){
        return this.gameBrain.getID();
    }

    private void removeRobotFromBoard(Robot robotToRemove){
        this.robots.remove(robotToRemove);
    }

    protected void setRobots(ArrayList<Robot> robots){this.robots = robots;}

    public void setTiles(ArrayList<Tile> tiles){this.tiles = tiles;}

    public ArrayList<Tile> getTiles(){
        return this.tiles;
    }

    public void changeTile(Tile newTile){
        int xCoordinate = newTile.getCoordinates().first();
        int yCoordinate = newTile.getCoordinates().second();
        Tile tile = getTileFromCoordinate(xCoordinate, yCoordinate);
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).equals(tile)){
                tiles.remove(i);
                tiles.add(i, newTile);
            }
        }
    }

    public ArrayList<Robot> getRobots(){
        return this.robots;
    }

    public ArrayList<Obstacle> getObstacles(){return this.obstacles;}

    public Obstacle getObstacleFromCoordinate(int xCoordinate, int yCoordinate){
        for (Obstacle obs: obstacles) {
            int x = obs.getCoordinates().first();
            int y = obs.getCoordinates().second();
            if(x == xCoordinate && y == yCoordinate){
                return obs;
            }
        }
        return null;
    }

    public ArrayList<Tile> getTilesSurroundingCoordinate(int xCoordinate, int yCoordinate){
        ArrayList<Tile> surroundingTiles = new ArrayList<>();
        ArrayList<Tile> surroundingTilesFinal = new ArrayList<>();
        surroundingTiles.add(getTileFromCoordinate(xCoordinate - 1, yCoordinate - 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate, yCoordinate - 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate + 1, yCoordinate - 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate -1, yCoordinate));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate + 1, yCoordinate));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate - 1, yCoordinate + 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate, yCoordinate + 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate + 1, yCoordinate + 1));
        for (Tile tile: surroundingTiles) {
            if(tile != null){
                surroundingTilesFinal.add(tile);
            }
        }
        return surroundingTilesFinal;
    }
    public Tile getTileFromCoordinate(Integer x, Integer y){
        for (Tile tile: tiles) {
            if(tile.getCoordinates().first().equals(x) && tile.getCoordinates().second().equals(y)){
                return tile;
            }
        }
        return null;
    }

    public Robot getRobotFromCoordinate(Integer x, Integer y){
        for (Robot robot: robots) {
            if(robot.getCords().x == x && robot.getCords().y == y){
                return robot;
            }
        }
        return null;
    }

    public boolean isTileOccupiedByRobot(int xCoordinate, int yCoordinate){
        for (Robot robot: robots) {
            Point location = robot.getCords();
            if(location.x == xCoordinate && location.y == yCoordinate){
                return true;
            }
        }
        return false;
    }

    protected void removeRobot(Robot robot){
        this.robots.remove(robot);
    }

    @Override
    public String DTOasJson() {
        GameboardDTO gameboardDTO = new GameboardDTO(this);
        return JsonHelper.serializeObjectToJson(gameboardDTO);
    }

    @Override
    public UUID getID() {
        return id;
    }
    protected GameBrain getGameBrain() {
        return gameBrain;
    }
}
