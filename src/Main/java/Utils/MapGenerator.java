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
        int[][] acidTilePositions = {{2,4}, {7,7}, {3,2}};

        for (int[] position : acidTilePositions) {
            Tile acidTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            acidTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
            gameboard.changeTile(acidTile);
        }

        //add finish tile
        Tile finishPointTiles1 = new Tile(10, 2, TileTypeEnum.FINISH);
        finishPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        //add checkpoint1
        Tile checkpointTiles1 = new Tile(1, 4, TileTypeEnum.CHECKPOINT);
        checkpointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.CHECKPOINTS_1, difficulty);
        gameboard.changeTile(checkpointTiles1);

        // add start point tiles
        int[][] startTilePositions = {{2,11}, {4,11}, {7,11}, {9,11}};

        for (int[] position : startTilePositions) {
            Tile startTile = new Tile(position[0], position[1], TileTypeEnum.START);
            startTile.setGraphicalElement(GraphicalElementEnum.START, difficulty);
            gameboard.changeTile(startTile);
        }

        // add pit tile
        int[][] pitTilePositions = {{1,5}, {10,3}, {4,6}, {7,4}};

        for (int[] position : pitTilePositions) {
            Tile pitTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            pitTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
            gameboard.changeTile(pitTile);
        }

        // add healing tiles
        int[][] healTilePositions = {{7,4}, {0,3}, {4,1}};

        for (int[] position : healTilePositions) {
            Tile healTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            healTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
            gameboard.changeTile(healTile);
        }

        //add upwards treadmill tiles
        int[][] upwardsTilePositions = {{0,10}, {10,10}, {9,9}, {2,9}, {10,0}};

        for (int[] position : upwardsTilePositions) {
            Tile upwardsTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            upwardsTile.setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
            gameboard.changeTile(upwardsTile);
        }

        //add downwards treadmill tiles
        Tile treadmillTileDown1 = new Tile(2, 0, TileTypeEnum.OBSTACLE);
        treadmillTileDown1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTileDown1);

        //add leftwards treadmill tiles
        int[][] leftwardsTilePositions = {{11,1}, {11,8}, {0,1}};

        for (int[] position : leftwardsTilePositions) {
            Tile leftwardsTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            leftwardsTile.setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
            gameboard.changeTile(leftwardsTile);
        }

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

        // add left treadmill tiles
        int[][] leftwardsTilePositions = {{8,4}, {9,4}, {8,7}, {9,7}};

        for (int[] position : leftwardsTilePositions) {
            Tile leftwardsTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            leftwardsTile.setGraphicalElement(GraphicalElementEnum.LEFT_TREADMILL, difficulty);
            gameboard.changeTile(leftwardsTile);
        }

        // add down treadmill tiles
        int[][] downwardsTilePositions = {{1,0}, {1,1}, {1,2}, {1,3}, {4,0}, {10,1}, {10,2}, {10,3}};

        for (int[] position : downwardsTilePositions) {
            Tile downwardsTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            downwardsTile.setGraphicalElement(GraphicalElementEnum.DOWNWARD_TREADMILL, difficulty);
            gameboard.changeTile(downwardsTile);
        }

        // add upwards treadmill tiles
        int[][] upwardsTilePositions = {{1,8}, {1,9}, {10,8}, {10,9}, {0,10}, {11,10}};

        for (int[] position : upwardsTilePositions) {
            Tile upwardsTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            upwardsTile.setGraphicalElement(GraphicalElementEnum.FORWARD_TREADMILL, difficulty);
            gameboard.changeTile(upwardsTile);
        }

        //add rightwards treadmill tiles
        int[][] rightwardsTilePositions = {{2,4}, {3,4}, {2,7}, {3,7}, {6,9}, {7,0}};

        for (int[] position : rightwardsTilePositions) {
            Tile rightwardsTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            rightwardsTile.setGraphicalElement(GraphicalElementEnum.RIGHT_TREADMILL, difficulty);
            gameboard.changeTile(rightwardsTile);
        }

        // add turn treadmill tiles
        Tile treadmillTile1 = new Tile(10, 4, TileTypeEnum.OBSTACLE);
        treadmillTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.BOTTOMRIGHT_REVERSE_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile1);

        Tile treadmillTile2 = new Tile(10, 7, TileTypeEnum.OBSTACLE);
        treadmillTile2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.UPRIGHT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile2);

        Tile treadmillTile3 = new Tile(1, 4, TileTypeEnum.OBSTACLE);
        treadmillTile3.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.BOTTOMLEFT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile3);

        Tile treadmillTile4 = new Tile(1, 7, TileTypeEnum.OBSTACLE);
        treadmillTile4.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.UPRIGHT_REVERSE_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile4);

        Tile treadmillTile5 = new Tile(7, 9, TileTypeEnum.OBSTACLE);
        treadmillTile5.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.BOTTOMRIGHT_TURN_TREADMILL, difficulty);
        gameboard.changeTile(treadmillTile5);

        // add acid tiles
        int[][] acidTilePositions = {{8,0}, {9,0}, {10,0}, {2,3}, {0,5}, {8,6}};

        for (int[] position : acidTilePositions) {
            Tile acidTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            acidTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_ACID, difficulty);
            gameboard.changeTile(acidTile);
        }

        //add finish tile
        Tile finishPointTiles1 = new Tile(4, 5, TileTypeEnum.FINISH);
        finishPointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        //add checkpoint1, 2
        Tile checkpointTiles1 = new Tile(7, 5, TileTypeEnum.CHECKPOINT);
        checkpointTiles1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.CHECKPOINTS_1, difficulty);
        gameboard.changeTile(checkpointTiles1);

        Tile checkpointTiles2 = new Tile(11, 0, TileTypeEnum.CHECKPOINT);
        checkpointTiles2.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.CHECKPOINTS_2, difficulty);
        gameboard.changeTile(checkpointTiles2);

        // add start point tiles
        int[][] startTilePositions = {{2,11}, {5,11}, {6,11}, {10,11}};

        for (int[] position : startTilePositions) {
            Tile startTile = new Tile(position[0], position[1], TileTypeEnum.START);
            startTile.setGraphicalElement(GraphicalElementEnum.START, difficulty);
            gameboard.changeTile(startTile);
        }

        // add pit tiles
        int[][] pitTilePositions = {{4,3}, {4,4}, {4,7}, {4,8}, {7,3}, {7,4}, {7,7}, {7,8}};

        for (int[] position : pitTilePositions) {
            Tile pitTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            pitTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_PIT, difficulty);
            gameboard.changeTile(pitTile);
        }

        // add healing tiles
        int[][] healTilePositions = {{2,2}, {0,3}, {6,5}, {11,6}, {4,9}};

        for (int[] position : healTilePositions) {
            Tile healTile = new Tile(position[0], position[1], TileTypeEnum.OBSTACLE);
            healTile.setGraphicalElement(GraphicalElementEnum.OBSTACLE_HEALING, difficulty);
            gameboard.changeTile(healTile);
        }

        //add explosive acid tiles
        Tile explosiveAcidTile1 = new Tile(7, 6, TileTypeEnum.OBSTACLE);
        explosiveAcidTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        gameboard.changeTile(explosiveAcidTile1);

        //add explosive radiation tile
        Tile explosiveRadiationTile1 = new Tile(2, 9, TileTypeEnum.OBSTACLE);
        explosiveRadiationTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_EXPLOSIVE_RADIATION, difficulty);
        gameboard.changeTile(explosiveRadiationTile1);

        //add radiation tile
        Tile radiationTile1 = new Tile(8, 3, TileTypeEnum.OBSTACLE);
        radiationTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_RADIATION, difficulty);
        gameboard.changeTile(radiationTile1);

        //add unknown explosive tile
        Tile unknownExplosiveTile1 = new Tile(2, 6, TileTypeEnum.OBSTACLE);
        unknownExplosiveTile1.getGraphicalElement().setGraphicalElement(GraphicalElementEnum.OBSTACLE_UNKNOWN_EXPLOSIVE, difficulty);
        gameboard.changeTile(unknownExplosiveTile1);


        return gameboard;
    }
}
