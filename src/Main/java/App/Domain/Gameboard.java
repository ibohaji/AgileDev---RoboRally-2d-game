package App.Domain;

import Utils.Tuple;

import java.util.ArrayList;

public class Gameboard {
    // List of obstacles used -> List (Obstacle + Coordinate)
    // List of robots -> List (Robot)
    private ArrayList<SpaceOnBoard> spacesOnBoard = new ArrayList<>();
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
                SpaceOnBoard nextSpace = new SpaceOnBoard(x, y);
                nextSpace.putGraphicElementOnSpace(GraphicalElementEnum.DEFAULT_FLOOR);
                spacesOnBoard.add(nextSpace);

            }
        }
    }

    private void initializeObstacles(){

    }

    private ArrayList<SpaceOnBoard> getSpacesOnBoard(){
        return this.spacesOnBoard;
    }
}
