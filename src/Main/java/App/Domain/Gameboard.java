package App.Domain;

import App.Domain.Enums.GraphicalElementEnum;
import Utils.Tuple;

import java.util.ArrayList;

public class Gameboard {
    // List of obstacles used -> List (Obstacle + Coordinate)
    // List of robots -> List (Robot)
    private ArrayList<Tile> tilesOnBoard = new ArrayList<>();
    private ArrayList<Robot> robots = new ArrayList<>();
    private Tuple<Integer, Integer> dimensions;
    private GameConfiguration gameConfig;


    public Gameboard(){

    }
    public Gameboard(GameConfiguration config){
        gameConfig = config;
        dimensions = gameConfig.getBoardDimensions();
        initializeGameboard();
    }

    /**
     * Initialize default gameboard spaces
     */

    private void initializeGameboard(){
        for (int x = 0; x < dimensions.first(); x++) {
            for (int y = 0; y < dimensions.second(); y++) {
                Tile nextTile = new Tile(x, y);
                System.out.println(nextTile.graphicalElement == null);
                nextTile.graphicalElement.changeGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR);
                tilesOnBoard.add(nextTile);
            }
        }
    }
    public Tuple<Integer, Integer> getGameboardDimensions(){
        return this.dimensions;
    }

    public GameConfiguration getGameConfig(){
        return this.gameConfig;
    }

    public ArrayList<Tile> getTiles(){
        return this.tilesOnBoard;
    }

    private void removeRobotFromBoard(Robot robotToRemove){
        this.robots.remove(robotToRemove);
    }


    private ArrayList<Tile> getTilesOnBoard(){
        return this.tilesOnBoard;
    }
}
