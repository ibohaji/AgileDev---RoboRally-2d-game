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
        for (int i = 0; i < dimensions.first(); i++) {
            for (int j = 0; j < dimensions.second(); j++) {
                SpaceOnBoard nextSpace = new SpaceOnBoard(i, j);
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
