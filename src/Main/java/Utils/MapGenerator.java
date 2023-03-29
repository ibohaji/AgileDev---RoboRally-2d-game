package Utils;

import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.Enums.TileTypeEnum;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Model.GameRunning.Gameboard;


import java.util.ArrayList;


public class MapGenerator {

    public static Gameboard generateEasyMap() {
        DifficultyEnum difficulty = DifficultyEnum.EASY;
        GameConfiguration gameConfiguration = new GameConfiguration(2, difficulty);
        Tuple<Integer, Integer> boardDimensions = gameConfiguration.getBoardDimensions();
        Gameboard gameboard = new Gameboard(new GameBrain(2, difficulty));

        ArrayList<Tile> tilesOnBoard = new ArrayList<>();

        TileTypeEnum floor = TileTypeEnum.DEFAULT_FLOOR;

        // create floor tiles
        for (int i = 0; i < boardDimensions.first(); i++) {
            for (int j = 0; j < boardDimensions.second(); j++) {
                Tile nextTile = new Tile(i, j, floor);
                nextTile.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR, difficulty);
                tilesOnBoard.add(nextTile);
            }
        }

        // add pit tile
        Tile pitTile = new Tile(3, 5, TileTypeEnum.OBSTACLE);
        pitTile.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
        tilesOnBoard.add(pitTile);

        // add healing tiles
        Tile healTile1 = new Tile(7, 4, TileTypeEnum.OBSTACLE);
        healTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        tilesOnBoard.add(healTile1);

        Tile healTile2 = new Tile(0, 1, TileTypeEnum.OBSTACLE);
        healTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        tilesOnBoard.add(healTile2);

        // add treadmill tiles
        Tile treadmillTile1 = new Tile(3, 7, TileTypeEnum.OBSTACLE);
        treadmillTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.RIGHT_TREADMILL, difficulty);
        tilesOnBoard.add(treadmillTile1);

        Tile treadmillTile2 = new Tile(2, 2, TileTypeEnum.OBSTACLE);
        treadmillTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.RIGHT_TREADMILL, difficulty);
        tilesOnBoard.add(treadmillTile2);

        Tile treadmillTile3 = new Tile(5, 4, TileTypeEnum.OBSTACLE);
        treadmillTile3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
        tilesOnBoard.add(treadmillTile3);

        Tile treadmillTile4 = new Tile(6, 6, TileTypeEnum.OBSTACLE);
        treadmillTile4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
        tilesOnBoard.add(treadmillTile2);

        // add acid tiles
        Tile acidTiles1 = new Tile(4, 3, TileTypeEnum.OBSTACLE);
        acidTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        tilesOnBoard.add(acidTiles1);

        Tile acidTiles2 = new Tile(6, 1, TileTypeEnum.OBSTACLE);
        acidTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        tilesOnBoard.add(acidTiles2);

        // add checkpoint tile
        Tile checkPointTiles1 = new Tile(3, 6, TileTypeEnum.CHECKPOINT);
        checkPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.ROBOT_SOUTH, difficulty);
        tilesOnBoard.add(checkPointTiles1);

        // add start point tiles
        Tile startPointTiles1 = new Tile(2, 0, TileTypeEnum.START);
        startPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.ROBOT_NORTH, difficulty);
        tilesOnBoard.add(startPointTiles1);

        Tile startPointTiles2 = new Tile(4, 0, TileTypeEnum.START);
        startPointTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.ROBOT_NORTH, difficulty);
        tilesOnBoard.add(startPointTiles2);



        return gameboard;
    }
}
