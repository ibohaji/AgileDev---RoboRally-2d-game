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
    private Tuple<Integer, Integer> dimensions;
    //private GameConfiguration gameConfig;
    private GameBrain gameBrain;
    int mapTile[][];

    public Gameboard(){

    }
    public Gameboard(GameBrain brain){
        gameBrain = brain;
        gameBrainId = gameBrain.getID();
        dimensions = brain.getGameConfig().getBoardDimensions();
        initializeGameboard();
    }

    /**
     * Initialize default gameboard spaces
     */

    private void initializeGameboard() {
        for (int x = 0; x < dimensions.first(); x++) {
            for (int y = 0; y < dimensions.second(); y++) {
                Tile nextTile = new Tile(x, y, TileTypeEnum.DEFAULT_FLOOR);
                nextTile.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR,
                        this.gameBrain.getGameConfig().getDifficulty());
                tiles.add(nextTile);
            }
        }
    }



    /*private void initializeMap(){
        int col = 0;
        int row = 0;
        Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
        int width = dim.first();
        int height = dim.second();

        while (col < width && row < height){
            int tileNum = mapTile[col][row];
            Tile nextTile = new Tile(col,row);
            tilesOnBoard.add(nextTile);
        }*/

        /*
         while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();


            if (col == gp.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
         */
/*
    }*/

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map/Map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
            int width = dim.first();
            int height = dim.second();

            while (col < width && row < height) {
                String line = br.readLine();

                while (col < width) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTile[col][row] = num;
                    col++;
                }
                if (col == width) {
                    col = 0;
                    row++;
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /*public void initMap(){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        Tuple<Integer,Integer> dim = DifficultyEnum.EASY.getDimensions();
        int screenCol = dim.first();
        int screenRow = dim.second();

        while (col < screenCol && row < screenCol) {
            int tileNum = mapTile[col][row];




            g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);
            col++;
            x += gp.getTileSize();


            if (col == gp.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += gp.getTileSize();
            }
        }
    }*/

    public UUID getGameBrainId(){
        return this.gameBrain.getID();
    }

    private void removeRobotFromBoard(Robot robotToRemove){
        this.robots.remove(robotToRemove);
    }

    protected void setRobots(ArrayList<Robot> robots){this.robots = robots;}

    protected void setTiles(ArrayList<Tile> tiles){this.tiles = tiles;}

    public ArrayList<Tile> getTiles(){
        return this.tiles;
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
