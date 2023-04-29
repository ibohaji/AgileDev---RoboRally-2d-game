package App.RoborallyApplication.Model;

import App.DTO.GameboardDTO;
import Utils.JsonHelper;
import Utils.Tuple;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class LGameboard implements IToDTO {
    private UUID id;
    private UUID gameBrainId;
    private ArrayList<LTile> tiles = new ArrayList<>();
    private ArrayList<LRobot> robots = new ArrayList<>();
    private ArrayList<AbObstacle> obstacles = new ArrayList<>();
    private ArrayList<LTile> checkpointsInOrder = new ArrayList<>();
    private LGameBrain gameBrain;

    public LGameboard(LGameBrain brain){
        id = UUID.randomUUID();
        gameBrain = brain;
        gameBrainId = gameBrain.getID();
    }
    public UUID getGameBrainId(){
        return this.gameBrain.getID();
    }

    public void setRobots(ArrayList<LRobot> robots){this.robots = robots;}

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
    public AbObstacle getObstacleFromCoordinateNEW(Integer x, Integer y) {
        for(LTile tile: tiles){
            if(tile.doesTileHaveObstacle()){
                if(tile.getCoordinates().x == x && tile.getCoordinates().y == y){
                    return tile.getNewObstacle();
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
    public void addCheckpoint(LTile checkpointTile){
        if(this.gameBrain.getGameConfig().getDifficulty().equals(EnumDifficulty.EASY)){
            throw new RuntimeException("Problem in gameboard method addCheckpoint(). Can't add checkpoints to EASY difficulty");
        } else if (this.gameBrain.getGameConfig().getDifficulty().equals(EnumDifficulty.MEDIUM)) {
            if(this.checkpointsInOrder.isEmpty()){
                this.checkpointsInOrder.add(checkpointTile);
            } else {
                throw new RuntimeException("Problem in gameboard method addCheckpoint(). Can't add more than 1 checkpoint to MEDIUM difficulty");
            }
        } else if (this.gameBrain.getGameConfig().getDifficulty().equals(EnumDifficulty.HARD)) {
            if(this.checkpointsInOrder.size() < 3){
                this.checkpointsInOrder.add(checkpointTile);
            } else {
                throw new RuntimeException("Problem in gameboard method addCheckpoint(). Can't add more than 2 checkpoints to HARD difficulty");
            }
        } else {
            throw new RuntimeException("Problem with getting difficulty in gameboard method addCheckpoint()");
        }
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
    public LGameBrain getGameBrain() {
        return gameBrain;
    }

    public ArrayList<LTile> getCheckpointsInOrder(){return checkpointsInOrder;}
}
