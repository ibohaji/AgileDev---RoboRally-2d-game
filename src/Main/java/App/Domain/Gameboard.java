package App.Domain;

import App.Domain.Enums.GraphicalElementEnum;
import Utils.Tuple;

import java.util.ArrayList;

public class Gameboard {
    // List of obstacles used -> List (Obstacle + Coordinate)
    // List of robots -> List (Robot)
    private ArrayList<Tile> spacesOnBoard = new ArrayList<>();
    private ArrayList<Robot> robots = new ArrayList<>();
    private final Tuple<Integer, Integer> dimensions;
    private final GameConfiguration gameConfig;


    public Gameboard(GameConfiguration config){
        gameConfig = config;
        dimensions = gameConfig.getBoardDimensions();
        initializeGameboard();
        initializeObstacles();
    }

    /**
     * Initialize default gameboard spaces
     */

    private void initializeGameboard(){
        for (int x = 0; x < dimensions.first(); x++) {
            for (int y = 0; y < dimensions.second(); y++) {
                Tile nextSpace = new Tile(x, y);
                nextSpace.putGraphicElementOnSpace(GraphicalElementEnum.DEFAULT_FLOOR);
                spacesOnBoard.add(nextSpace);

            }
        }
    }

    private void initializeObstacles(){

    }

    private ArrayList<Tile> getSpacesOnBoard(){
        return this.spacesOnBoard;
    }
}
