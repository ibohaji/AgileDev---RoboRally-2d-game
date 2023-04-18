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

        Tile pitTile1 = new Tile(6, 3, TileTypeEnum.OBSTACLE);
        pitTile1.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
        gameboard.changeTile(pitTile1);

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

    public static Gameboard generateMediumMap(GameBrain gameBrain) {
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

        // add left treadmill tiles
        for (int i = 4; i < 8; i++) {
            Tile treadmillTurnTile1 = new Tile(i, 3, TileTypeEnum.OBSTACLE);
            Tile treadmillTurnTile2 = new Tile(i, 8, TileTypeEnum.OBSTACLE);
            treadmillTurnTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
            treadmillTurnTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
            gameboard.changeTile(treadmillTurnTile1);
            gameboard.changeTile(treadmillTurnTile2);

        }

        // add down treadmill tiles
        for (int i = 4; i < 8; i++) {
            Tile treadmillTurnTile3 = new Tile(3, i, TileTypeEnum.OBSTACLE);
            Tile treadmillTurnTile4 = new Tile(8, i, TileTypeEnum.OBSTACLE);
            treadmillTurnTile3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
            treadmillTurnTile4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
            gameboard.changeTile(treadmillTurnTile3);
            gameboard.changeTile(treadmillTurnTile4);
        }

        // add turn treadmill tiles
        Tile treadmillTile1 = new Tile(3, 3, TileTypeEnum.OBSTACLE);
        treadmillTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.UPLEFT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile1);

        Tile treadmillTile2 = new Tile(8, 3, TileTypeEnum.OBSTACLE);
        treadmillTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.UPRIGHT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile2);

        Tile treadmillTile3 = new Tile(3, 8, TileTypeEnum.OBSTACLE);
        treadmillTile3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.BOTTOMLEFT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile3);

        Tile treadmillTile4 = new Tile(8, 8, TileTypeEnum.OBSTACLE);
        treadmillTile4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.BOTTOMRIGHT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile4);

        // add acid tiles
        Tile acidTiles1 = new Tile(2, 4, TileTypeEnum.OBSTACLE);
        acidTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
        gameboard.changeTile(acidTiles1);

        Tile acidTiles2 = new Tile(7, 7, TileTypeEnum.OBSTACLE);
        acidTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
        gameboard.changeTile(acidTiles2);

        Tile acidTiles3 = new Tile(3, 2, TileTypeEnum.OBSTACLE);
        acidTiles3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
        gameboard.changeTile(acidTiles3);

        //add finish tile
        Tile finishPointTiles1 = new Tile(10, 2, TileTypeEnum.FINISH);
        finishPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        //add checkpoint1
        Tile checkpointTiles1 = new Tile(1, 4, TileTypeEnum.CHECKPOINT);
        checkpointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.CHECKPOINTS_1, difficulty);
        gameboard.changeTile(checkpointTiles1);

        // add start point tiles
        Tile startPointTiles1 = new Tile(2, 11, TileTypeEnum.START);
        startPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(startPointTiles1);

        Tile startPointTiles2 = new Tile(4, 11, TileTypeEnum.START);
        startPointTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(startPointTiles2);

        Tile starterPointTiles3 = new Tile(7,11,TileTypeEnum.START);
        starterPointTiles3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(starterPointTiles3);

        Tile startPointTiles4 = new Tile(9, 11, TileTypeEnum.START);
        startPointTiles4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.START, difficulty);
        gameboard.changeTile(startPointTiles4);


        // add pit tile
        Tile pitTile1 = new Tile(1, 5, TileTypeEnum.OBSTACLE);
        pitTile1.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
        gameboard.changeTile(pitTile1);

        Tile pitTile2 = new Tile(10, 3, TileTypeEnum.OBSTACLE);
        pitTile2.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
        gameboard.changeTile(pitTile2);

        Tile pitTile3 = new Tile(4, 6, TileTypeEnum.OBSTACLE);
        pitTile3.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
        gameboard.changeTile(pitTile3);

        // add healing tiles
        Tile healTile1 = new Tile(7, 4, TileTypeEnum.OBSTACLE);
        healTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        gameboard.changeTile(healTile1);

        Tile healTile2 = new Tile(0, 3, TileTypeEnum.OBSTACLE);
        healTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        gameboard.changeTile(healTile2);

        Tile healTile3 = new Tile(4, 1, TileTypeEnum.OBSTACLE);
        healTile3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
        gameboard.changeTile(healTile3);

        //add upwards treadmill tiles
        Tile treadmillTileUp1 = new Tile(0, 10, TileTypeEnum.OBSTACLE);
        treadmillTileUp1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileUp1);

        Tile treadmillTileUp2 = new Tile(10, 10, TileTypeEnum.OBSTACLE);
        treadmillTileUp2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileUp2);

        Tile treadmillTileUp3 = new Tile(9, 9, TileTypeEnum.OBSTACLE);
        treadmillTileUp3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileUp3);

        Tile treadmillTileUp4 = new Tile(2, 9, TileTypeEnum.OBSTACLE);
        treadmillTileUp4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileUp4);

        Tile treadmillTileUp5 = new Tile(10, 0, TileTypeEnum.OBSTACLE);
        treadmillTileUp5.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileUp5);

        //add downwards treadmill tiles
        Tile treadmillTileDown1 = new Tile(2, 0, TileTypeEnum.OBSTACLE);
        treadmillTileDown1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileDown1);

        //add leftwards treadmill tiles
        Tile treadmillTileLeft1 = new Tile(11, 1, TileTypeEnum.OBSTACLE);
        treadmillTileLeft1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileLeft1);

        Tile treadmillTileLeft2 = new Tile(11, 8, TileTypeEnum.OBSTACLE);
        treadmillTileLeft2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileLeft2);

        Tile treadmillTileLeft3 = new Tile(0, 1, TileTypeEnum.OBSTACLE);
        treadmillTileLeft3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileLeft3);

        //add rightwards treadmill tiles
        Tile treadmillTileRight1 = new Tile(0, 8, TileTypeEnum.OBSTACLE);
        treadmillTileRight1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.RIGHT_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileRight1);

        //add explosive acid tiles
        Tile explosiveAcidTile1 = new Tile(4, 9, TileTypeEnum.OBSTACLE);
        explosiveAcidTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        gameboard.changeTile(explosiveAcidTile1);

        Tile explosiveAcidTile2 = new Tile(7, 9, TileTypeEnum.OBSTACLE);
        explosiveAcidTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        gameboard.changeTile(explosiveAcidTile2);

        //add explosive radiation tile
        Tile explosiveRadiationTile1 = new Tile(8, 2, TileTypeEnum.OBSTACLE);
        explosiveRadiationTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_RADIATION, difficulty);
        gameboard.changeTile(explosiveRadiationTile1);


        return gameboard;
    }

    public static Gameboard generateHardMap(GameBrain gameBrain) {
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
        return gameboard;
    }
}
