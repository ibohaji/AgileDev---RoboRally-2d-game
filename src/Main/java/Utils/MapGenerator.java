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

    public static Gameboard generateEasyMap(GameBrain gameBrain) {
        GameConfiguration gameConfiguration = gameBrain.getGameConfig();
        DifficultyEnum difficulty = gameConfiguration.getDifficulty();
        Tuple<Integer, Integer> boardDimensions = gameConfiguration.getBoardDimensions();
        Gameboard gameboard = new Gameboard(gameBrain);

        ArrayList<Tile> tiles = new ArrayList<>();

        TileTypeEnum floor = TileTypeEnum.DEFAULT_FLOOR;

        // create floor tiles
        for (int i = 0; i < boardDimensions.first(); i++) {
            for (int j = 0; j < boardDimensions.second(); j++) {
                Tile nextTile = new Tile(i, j, floor);
                nextTile.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DEFAULT_FLOOR, difficulty);
                tiles.add(nextTile);
            }
        }
        gameboard.setTiles(tiles);
        // add pit tile
        Tile pitTile = new Tile(4, 2, TileTypeEnum.OBSTACLE);
        pitTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
        gameboard.changeTile(pitTile);

        // add healing tiles
        Tile healTile1 = new Tile(7, 4, TileTypeEnum.OBSTACLE);
        healTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        gameboard.changeTile(healTile1);


        Tile healTile2 = new Tile(0, 3, TileTypeEnum.OBSTACLE);
        healTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        gameboard.changeTile(healTile2);

        // add treadmill tiles
        Tile treadmillTile1 = new Tile(4, 0, TileTypeEnum.OBSTACLE);
        treadmillTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.RIGHT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile1);

        Tile treadmillTile2 = new Tile(5, 5, TileTypeEnum.OBSTACLE);
        treadmillTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.RIGHT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile2);

        Tile treadmillTile3 = new Tile(2, 3, TileTypeEnum.OBSTACLE);
        treadmillTile3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile3);

        Tile treadmillTile4 = new Tile(1, 1, TileTypeEnum.OBSTACLE);
        treadmillTile4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile4);

        // add acid tiles
        Tile acidTiles1 = new Tile(3, 4, TileTypeEnum.OBSTACLE);
        acidTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
        gameboard.changeTile(acidTiles1);

        Tile acidTiles2 = new Tile(1, 5, TileTypeEnum.OBSTACLE);
        acidTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
        gameboard.changeTile(acidTiles2);

        //add finish tile
        Tile finishPointTiles1 = new Tile(4, 1, TileTypeEnum.FINISH);
        finishPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        // add start point tiles
        Tile startPointTiles1 = new Tile(6, 7, TileTypeEnum.START);
        startPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(startPointTiles1);

        Tile startPointTiles2 = new Tile(5, 7, TileTypeEnum.START);
        startPointTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(startPointTiles2);

        Tile starterPointTiles3 = new Tile(3,7,TileTypeEnum.START);
        starterPointTiles3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(starterPointTiles3);

        Tile startPointTiles4 = new Tile(1, 7, TileTypeEnum.START);
        startPointTiles4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(startPointTiles4);

        return gameboard;
    }
}
