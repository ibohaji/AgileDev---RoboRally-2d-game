package App.RoborallyApplication.Model;

import App.DTO.GameboardDTO;
import Utils.JsonHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class LGameboard implements iToDTO {
    private UUID id;
    private UUID gameBrainId;
    private ArrayList<LTile> tiles = new ArrayList<>();
    private ArrayList<LRobot> robots = new ArrayList<>();
    private ArrayList<LObstacle> obstacles = new ArrayList<>();
    private LGameBrain gameBrain;

    public LGameboard(){
    }

    public LGameboard(LGameBrain brain){
        gameBrain = brain;
        gameBrainId = gameBrain.getID();
    }

    public UUID getGameBrainId(){
        return this.gameBrain.getID();
    }

    private void removeRobotFromBoard(LRobot robotToRemove){
        this.robots.remove(robotToRemove);
    }

    protected void setRobots(ArrayList<LRobot> robots){this.robots = robots;}

    public void setTiles(ArrayList<LTile> tiles){this.tiles = tiles;}

    public ArrayList<LTile> getTiles(){
        return this.tiles;
    }

    public void changeTile(LTile newTile){
        int xCoordinate = newTile.getCoordinates().x;
        int yCoordinate = newTile.getCoordinates().y;
        LTile tile = getTileFromCoordinate(xCoordinate, yCoordinate);
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).equals(tile)){
                tiles.remove(i);
                tiles.add(i, newTile);
            }
        }
    }

    public ArrayList<LRobot> getRobots(){
        return this.robots;
    }

    public ArrayList<LObstacle> getObstacles(){return this.obstacles;}

    public LObstacle getObstacleFromCoordinate(int xCoordinate, int yCoordinate){
        for(LTile tile: tiles){
            if(tile.doesTileHaveObstacle()){
                if(tile.getCoordinates().x == xCoordinate && tile.getCoordinates().y == yCoordinate){
                    return tile.getObstacle();
                }
            }
        }
        return null;
    }

    public ArrayList<LTile> getTilesSurroundingCoordinate(int xCoordinate, int yCoordinate){
        ArrayList<LTile> surroundingTiles = new ArrayList<>();
        ArrayList<LTile> surroundingTilesFinal = new ArrayList<>();
        surroundingTiles.add(getTileFromCoordinate(xCoordinate - 1, yCoordinate - 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate, yCoordinate - 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate + 1, yCoordinate - 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate -1, yCoordinate));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate + 1, yCoordinate));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate - 1, yCoordinate + 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate, yCoordinate + 1));
        surroundingTiles.add(getTileFromCoordinate(xCoordinate + 1, yCoordinate + 1));
        for (LTile tile: surroundingTiles) {
            if(tile != null){
                surroundingTilesFinal.add(tile);
            }
        }
        return surroundingTilesFinal;
    }
    public LTile getTileFromCoordinate(Integer x, Integer y){
        for (LTile tile: tiles) {
            if(tile.getCoordinates().x == x && tile.getCoordinates().y == y){
                return tile;
            }
        }
        return null;
    }


    public LRobot getRobotFromCoordinate(Integer x, Integer y){
        for (LRobot robot: robots) {
            if(robot.getCords().x == x && robot.getCords().y == y){
                return robot;
            }
        }
        return null;
    }

    public boolean isTileOccupiedByRobot(int xCoordinate, int yCoordinate){
        for (LRobot robot: robots) {
            Point location = robot.getCords();
            if(location.x == xCoordinate && location.y == yCoordinate){
                return true;
            }
        }
        return false;
    }

    protected void removeRobot(LRobot robot){
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
    protected LGameBrain getGameBrain() {
        return gameBrain;
    }
}
