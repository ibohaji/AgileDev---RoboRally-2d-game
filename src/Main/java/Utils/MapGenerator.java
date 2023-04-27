package Utils;

import App.RoborallyApplication.Model.*;


import java.util.ArrayList;


public class MapGenerator {

    public static LGameboard generateEasyMap(LGameBrain gameBrain) {
        LGameConfiguration gameConfiguration = gameBrain.getGameConfig();
        EnumDifficulty difficulty = gameConfiguration.getDifficulty();
        Tuple<Integer, Integer> boardDimensions = gameConfiguration.getBoardDimensions();
        LGameboard gameboard = new LGameboard(gameBrain);

        ArrayList<LTile> tiles = new ArrayList<>();

        EnumTileType floor = EnumTileType.DEFAULT_FLOOR;

        // create floor tiles
        for (int i = 0; i < boardDimensions.first(); i++) {
            for (int j = 0; j < boardDimensions.second(); j++) {
                LTile nextTile = new LTile(i, j, floor);
                nextTile.setGraphicalElement(EnumImageGraphics.DEFAULT_FLOOR, difficulty);
                tiles.add(nextTile);
            }
        }
        gameboard.setTiles(tiles);
        // add pit tile
        LTile pitTile = new LTile(4, 2, EnumTileType.OBSTACLE);
        pitTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_PIT, difficulty);
        pitTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
        //pitTile.setObstacle(new LObstacle(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(pitTile);

        LTile pitTile1 = new LTile(6, 3, EnumTileType.OBSTACLE);
        pitTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_PIT, difficulty);
        pitTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
        //pitTile1.setObstacle(new LObstacle(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(pitTile1);

        // add healing tiles
        LTile healTile1 = new LTile(7, 4, EnumTileType.OBSTACLE);
        healTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_HEALING, difficulty);
        healTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
        //healTile1.setObstacle(new LObstacle(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(healTile1);


        LTile healTile2 = new LTile(0, 3, EnumTileType.OBSTACLE);
        healTile2.setGraphicalElement(EnumImageGraphics.OBSTACLE_HEALING, difficulty);
        healTile2.setNewObstacle(new LObstacleRegular(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
        //healTile2.setObstacle(new LObstacle(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(healTile2);

        // TREADMILL TILES
        LTile treadmillTile1 = new LTile(4, 0, EnumTileType.OBSTACLE);
        treadmillTile1.setGraphicalElement(EnumImageGraphics.RIGHT_TREADMILL, difficulty);
        treadmillTile1.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.EAST));
        //treadmillTile1.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile1);

        LTile treadmillTile2 = new LTile(5, 5, EnumTileType.OBSTACLE);
        treadmillTile2.setGraphicalElement(EnumImageGraphics.RIGHT_TREADMILL, difficulty);
        treadmillTile2.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.EAST));
        //treadmillTile2.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile2);

        LTile treadmillTile3 = new LTile(2, 3, EnumTileType.OBSTACLE);
        treadmillTile3.setGraphicalElement(EnumImageGraphics.DOWNWARD_TREADMILL, difficulty);
        treadmillTile3.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.SOUTH));
        //treadmillTile3.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile3);

        LTile treadmillTile4 = new LTile(1, 1, EnumTileType.OBSTACLE);
        treadmillTile4.setGraphicalElement(EnumImageGraphics.LEFT_TREADMILL, difficulty);
        treadmillTile4.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
        //treadmillTile4.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile4);

        // add acid tiles
        LTile acidTiles1 = new LTile(3, 4, EnumTileType.OBSTACLE);
        acidTiles1.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, difficulty);
        acidTiles1.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
        //acidTiles1.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(acidTiles1);

        LTile acidTiles2 = new LTile(1, 5, EnumTileType.OBSTACLE);
        acidTiles2.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, difficulty);
        acidTiles2.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
        //acidTiles2.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(acidTiles2);

        //add finish tile
        LTile finishPointTiles1 = new LTile(4, 1, EnumTileType.FINISH);
        finishPointTiles1.setGraphicalElement(EnumImageGraphics.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        // add start point tiles
        LTile startPointTiles1 = new LTile(6, 7, EnumTileType.START);
        startPointTiles1.setGraphicalElement(EnumImageGraphics.START, difficulty);
        gameboard.changeTile(startPointTiles1);

        LTile startPointTiles2 = new LTile(5, 7, EnumTileType.START);
        startPointTiles2.setGraphicalElement(EnumImageGraphics.START, difficulty);
        gameboard.changeTile(startPointTiles2);

        LTile starterPointTiles3 = new LTile(3,7, EnumTileType.START);
        starterPointTiles3.setGraphicalElement(EnumImageGraphics.START, difficulty);
        gameboard.changeTile(starterPointTiles3);

        LTile startPointTiles4 = new LTile(1, 7, EnumTileType.START);
        startPointTiles4.setGraphicalElement(EnumImageGraphics.START, difficulty);
        gameboard.changeTile(startPointTiles4);

        return gameboard;
    }

    public static LGameboard generateMediumMap(LGameBrain gameBrain) {
        LGameConfiguration gameConfiguration = gameBrain.getGameConfig();
        EnumDifficulty difficulty = gameConfiguration.getDifficulty();
        Tuple<Integer, Integer> boardDimensions = gameConfiguration.getBoardDimensions();
        LGameboard gameboard = new LGameboard(gameBrain);

        ArrayList<LTile> tiles = new ArrayList<>();

        EnumTileType floor = EnumTileType.DEFAULT_FLOOR;

        // create floor tiles
        for (int i = 0; i < boardDimensions.first(); i++) {
            for (int j = 0; j < boardDimensions.second(); j++) {
                LTile nextTile = new LTile(i, j, floor);
                nextTile.setGraphicalElement(EnumImageGraphics.DEFAULT_FLOOR, difficulty);
                tiles.add(nextTile);
            }
        }
        gameboard.setTiles(tiles);

        // add left treadmill tiles
        for (int i = 4; i < 8; i++) {
            LTile treadmillTurnTile1 = new LTile(i, 3, EnumTileType.OBSTACLE);
            LTile treadmillTurnTile2 = new LTile(i, 8, EnumTileType.OBSTACLE);
            treadmillTurnTile1.setGraphicalElement(EnumImageGraphics.LEFT_TREADMILL, difficulty);
            treadmillTurnTile2.setGraphicalElement(EnumImageGraphics.LEFT_TREADMILL, difficulty);
            treadmillTurnTile1.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
            treadmillTurnTile2.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
            //treadmillTurnTile1.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            //treadmillTurnTile2.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(treadmillTurnTile1);
            gameboard.changeTile(treadmillTurnTile2);

        }

        // add down treadmill tiles
        for (int i = 4; i < 8; i++) {
            LTile treadmillTurnTile3 = new LTile(3, i, EnumTileType.OBSTACLE);
            LTile treadmillTurnTile4 = new LTile(8, i, EnumTileType.OBSTACLE);
            treadmillTurnTile3.setGraphicalElement(EnumImageGraphics.DOWNWARD_TREADMILL, difficulty);
            treadmillTurnTile4.setGraphicalElement(EnumImageGraphics.DOWNWARD_TREADMILL, difficulty);
            treadmillTurnTile3.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.SOUTH));
            treadmillTurnTile4.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.SOUTH));
            //treadmillTurnTile3.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            //treadmillTurnTile4.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(treadmillTurnTile3);
            gameboard.changeTile(treadmillTurnTile4);
        }

        // add turn treadmill tiles
        LTile treadmillTile1 = new LTile(3, 3, EnumTileType.OBSTACLE);
        treadmillTile1.setGraphicalElement(EnumImageGraphics.UPLEFT_TURN_TREADMILL, difficulty);
        treadmillTile1.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.SOUTH));
        //treadmillTile1.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile1);

        LTile treadmillTile2 = new LTile(8, 3, EnumTileType.OBSTACLE);
        treadmillTile2.setGraphicalElement(EnumImageGraphics.UPRIGHT_TURN_TREADMILL, difficulty);
        treadmillTile2.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
        //treadmillTile2.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile2);

        LTile treadmillTile3 = new LTile(3, 8, EnumTileType.OBSTACLE);
        treadmillTile3.setGraphicalElement(EnumImageGraphics.BOTTOMLEFT_TURN_TREADMILL, difficulty);
        treadmillTile3.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.EAST));
        //treadmillTile3.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile3);

        LTile treadmillTile4 = new LTile(8, 8, EnumTileType.OBSTACLE);
        treadmillTile4.setGraphicalElement(EnumImageGraphics.BOTTOMRIGHT_TURN_TREADMILL, difficulty);
        treadmillTile4.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.NORTH));
        //treadmillTile4.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile4);

        // add acid tiles
        int[][] acidTilePositions = {{2,4}, {7,7}, {3,2}};

        for (int[] position : acidTilePositions) {
            LTile acidTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            acidTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, difficulty);
            acidTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
            //acidTile.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(acidTile);
        }

        //add finish tile
        LTile finishPointTiles1 = new LTile(10, 2, EnumTileType.FINISH);
        finishPointTiles1.setGraphicalElement(EnumImageGraphics.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        //add checkpoint1
        LTile checkpointTiles1 = new LTile(1, 4, EnumTileType.CHECKPOINT);
        checkpointTiles1.setGraphicalElement(EnumImageGraphics.CHECKPOINTS_1, difficulty);
        gameboard.changeTile(checkpointTiles1);
        gameboard.addCheckpoint(checkpointTiles1);

        // add start point tiles
        int[][] startTilePositions = {{2,11}, {4,11}, {7,11}, {9,11}};

        for (int[] position : startTilePositions) {
            LTile startTile = new LTile(position[0], position[1], EnumTileType.START);
            startTile.setGraphicalElement(EnumImageGraphics.START, difficulty);
            gameboard.changeTile(startTile);
        }

        // add pit tile
        int[][] pitTilePositions = {{1,5}, {10,3}, {4,6}, {7,4}};

        for (int[] position : pitTilePositions) {
            LTile pitTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            pitTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_PIT, difficulty);
            pitTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
            //pitTile.setObstacle(new LObstacle(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(pitTile);
        }

        // add healing tiles
        int[][] healTilePositions = {{7,4}, {0,3}, {4,1}};

        for (int[] position : healTilePositions) {
            LTile healTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            healTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_HEALING, difficulty);
            healTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
            //healTile.setObstacle(new LObstacle(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(healTile);
        }

        //add upwards treadmill tiles
        int[][] upwardsTilePositions = {{0,10}, {10,10}, {9,9}, {2,9}, {10,0}};

        for (int[] position : upwardsTilePositions) {
            LTile upwardsTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            upwardsTile.setGraphicalElement(EnumImageGraphics.FORWARD_TREADMILL, difficulty);
            upwardsTile.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.NORTH));
            //upwardsTile.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(upwardsTile);
        }

        //add downwards treadmill tiles
        LTile treadmillTileDown1 = new LTile(2, 0, EnumTileType.OBSTACLE);
        treadmillTileDown1.setGraphicalElement(EnumImageGraphics.DOWNWARD_TREADMILL, difficulty);
        treadmillTileDown1.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.SOUTH));
        //treadmillTileDown1.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTileDown1);

        //add leftwards treadmill tiles
        int[][] leftwardsTilePositions = {{11,1}, {11,8}, {0,1}};

        for (int[] position : leftwardsTilePositions) {
            LTile leftwardsTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            leftwardsTile.setGraphicalElement(EnumImageGraphics.LEFT_TREADMILL, difficulty);
            leftwardsTile.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
            //leftwardsTile.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(leftwardsTile);
        }

        //add rightwards treadmill tiles
        LTile treadmillTileRight1 = new LTile(0, 8, EnumTileType.OBSTACLE);
        treadmillTileRight1.setGraphicalElement(EnumImageGraphics.RIGHT_TREADMILL, difficulty);
        treadmillTileRight1.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.EAST));
        //treadmillTileRight1.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTileRight1);

        //add explosive acid tiles
        LTile explosiveAcidTile1 = new LTile(4, 9, EnumTileType.OBSTACLE);
        explosiveAcidTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        explosiveAcidTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        //explosiveAcidTile1.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        gameboard.changeTile(explosiveAcidTile1);

        LTile explosiveAcidTile2 = new LTile(7, 9, EnumTileType.OBSTACLE);
        explosiveAcidTile2.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        explosiveAcidTile2.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        //explosiveAcidTile2.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        gameboard.changeTile(explosiveAcidTile2);

        //add explosive radiation tile
        LTile explosiveRadiationTile1 = new LTile(8, 2, EnumTileType.OBSTACLE);
        explosiveRadiationTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_RADIATION, difficulty);
        explosiveRadiationTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.RADIATION, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        //explosiveRadiationTile1.setObstacle(new LObstacle(EnumObstacleType.RADIATION, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        gameboard.changeTile(explosiveRadiationTile1);


        return gameboard;
    }

    public static LGameboard generateHardMap(LGameBrain gameBrain) {
        LGameConfiguration gameConfiguration = gameBrain.getGameConfig();
        EnumDifficulty difficulty = gameConfiguration.getDifficulty();
        Tuple<Integer, Integer> boardDimensions = gameConfiguration.getBoardDimensions();
        LGameboard gameboard = new LGameboard(gameBrain);

        ArrayList<LTile> tiles = new ArrayList<>();

        EnumTileType floor = EnumTileType.DEFAULT_FLOOR;

        // create floor tiles
        for (int i = 0; i < boardDimensions.first(); i++) {
            for (int j = 0; j < boardDimensions.second(); j++) {
                LTile nextTile = new LTile(i, j, floor);
                nextTile.setGraphicalElement(EnumImageGraphics.DEFAULT_FLOOR, difficulty);
                tiles.add(nextTile);
            }
        }
        gameboard.setTiles(tiles);

        // add left treadmill tiles
        int[][] leftwardsTilePositions = {{8,4}, {9,4}, {8,7}, {9,7}};

        for (int[] position : leftwardsTilePositions) {
            LTile leftwardsTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            leftwardsTile.setGraphicalElement(EnumImageGraphics.LEFT_TREADMILL, difficulty);
            leftwardsTile.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
            //leftwardsTile.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(leftwardsTile);
        }

        // add down treadmill tiles
        int[][] downwardsTilePositions = {{1,0}, {1,1}, {1,2}, {1,3}, {4,0}, {10,1}, {10,2}, {10,3}};

        for (int[] position : downwardsTilePositions) {
            LTile downwardsTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            downwardsTile.setGraphicalElement(EnumImageGraphics.DOWNWARD_TREADMILL, difficulty);
            downwardsTile.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.SOUTH));
            //downwardsTile.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(downwardsTile);
        }

        // add upwards treadmill tiles
        int[][] upwardsTilePositions = {{1,8}, {1,9}, {10,8}, {10,9}, {0,10}, {11,10}};

        for (int[] position : upwardsTilePositions) {
            LTile upwardsTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            upwardsTile.setGraphicalElement(EnumImageGraphics.FORWARD_TREADMILL, difficulty);
            upwardsTile.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.NORTH));
            //upwardsTile.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(upwardsTile);
        }

        //add rightwards treadmill tiles
        int[][] rightwardsTilePositions = {{2,4}, {3,4}, {2,7}, {3,7}, {6,9}, {7,0}};

        for (int[] position : rightwardsTilePositions) {
            LTile rightwardsTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            rightwardsTile.setGraphicalElement(EnumImageGraphics.RIGHT_TREADMILL, difficulty);
            rightwardsTile.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.EAST));
            //rightwardsTile.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(rightwardsTile);
        }

        // add turn treadmill tiles
        LTile treadmillTile1 = new LTile(10, 4, EnumTileType.OBSTACLE);
        treadmillTile1.setGraphicalElement(EnumImageGraphics.BOTTOMRIGHT_REVERSE_TREADMILL, difficulty);
        treadmillTile1.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
        //treadmillTile1.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile1);

        LTile treadmillTile2 = new LTile(10, 7, EnumTileType.OBSTACLE);
        treadmillTile2.setGraphicalElement(EnumImageGraphics.UPRIGHT_TURN_TREADMILL, difficulty);
        treadmillTile2.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
        //treadmillTile2.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile2);

        LTile treadmillTile3 = new LTile(1, 4, EnumTileType.OBSTACLE);
        treadmillTile3.setGraphicalElement(EnumImageGraphics.BOTTOMLEFT_TURN_TREADMILL, difficulty);
        treadmillTile3.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.EAST));
        //treadmillTile3.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile3);

        LTile treadmillTile4 = new LTile(1, 7, EnumTileType.OBSTACLE);
        treadmillTile4.setGraphicalElement(EnumImageGraphics.UPRIGHT_REVERSE_TREADMILL, difficulty);
        treadmillTile4.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.WEST));
        //treadmillTile4.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile4);

        LTile treadmillTile5 = new LTile(7, 9, EnumTileType.OBSTACLE);
        treadmillTile5.setGraphicalElement(EnumImageGraphics.BOTTOMRIGHT_TURN_TREADMILL, difficulty);
        treadmillTile5.setNewObstacle(new LObstacleTreadmill(EnumTreadmillDirection.NORTH));
        //treadmillTile5.setObstacle(new LObstacle(EnumObstacleType.TREADMILL, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(treadmillTile5);

        // add acid tiles
        int[][] acidTilePositions = {{8,0}, {9,0}, {10,0}, {2,3}, {0,5}, {8,6}};

        for (int[] position : acidTilePositions) {
            LTile acidTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            acidTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, difficulty);
            acidTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
            //acidTile.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(acidTile);
        }

        //add finish tile
        LTile finishPointTiles1 = new LTile(4, 5, EnumTileType.FINISH);
        finishPointTiles1.setGraphicalElement(EnumImageGraphics.FINISH, difficulty);
        gameboard.changeTile(finishPointTiles1);

        //add checkpoint1, 2
        LTile checkpointTiles1 = new LTile(7, 5, EnumTileType.CHECKPOINT);
        checkpointTiles1.setGraphicalElement(EnumImageGraphics.CHECKPOINTS_1, difficulty);
        gameboard.changeTile(checkpointTiles1);
        gameboard.addCheckpoint(checkpointTiles1);

        LTile checkpointTiles2 = new LTile(11, 0, EnumTileType.CHECKPOINT);
        checkpointTiles2.setGraphicalElement(EnumImageGraphics.CHECKPOINTS_2, difficulty);
        gameboard.changeTile(checkpointTiles2);
        gameboard.addCheckpoint(checkpointTiles2);

        // add start point tiles
        int[][] startTilePositions = {{2,11}, {5,11}, {6,11}, {10,11}};

        for (int[] position : startTilePositions) {
            LTile startTile = new LTile(position[0], position[1], EnumTileType.START);
            startTile.setGraphicalElement(EnumImageGraphics.START, difficulty);
            gameboard.changeTile(startTile);
        }

        // add pit tiles
        int[][] pitTilePositions = {{4,3}, {4,4}, {4,7}, {4,8}, {7,3}, {7,4}, {7,7}, {7,8}};

        for (int[] position : pitTilePositions) {
            LTile pitTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            pitTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_PIT, difficulty);
            pitTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
            //pitTile.setObstacle(new LObstacle(EnumObstacleType.PIT, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(pitTile);
        }

        // add healing tiles
        int[][] healTilePositions = {{2,2}, {0,3}, {6,5}, {11,6}, {4,9}};

        for (int[] position : healTilePositions) {
            LTile healTile = new LTile(position[0], position[1], EnumTileType.OBSTACLE);
            healTile.setGraphicalElement(EnumImageGraphics.OBSTACLE_HEALING, difficulty);
            healTile.setNewObstacle(new LObstacleRegular(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
            //healTile.setObstacle(new LObstacle(EnumObstacleType.HEALING, EnumObstacleClassification.KNOWN_OBSTACLE));
            gameboard.changeTile(healTile);
        }

        //add explosive acid tiles
        LTile explosiveAcidTile1 = new LTile(7, 6, EnumTileType.OBSTACLE);
        explosiveAcidTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_ACID, difficulty);
        explosiveAcidTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        //explosiveAcidTile1.setObstacle(new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        gameboard.changeTile(explosiveAcidTile1);

        //add explosive radiation tile
        LTile explosiveRadiationTile1 = new LTile(2, 9, EnumTileType.OBSTACLE);
        explosiveRadiationTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_EXPLOSIVE_RADIATION, difficulty);
        explosiveRadiationTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.RADIATION, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        //explosiveRadiationTile1.setObstacle(new LObstacle(EnumObstacleType.RADIATION, EnumObstacleClassification.EXPLOSIVE_KNOWN));
        gameboard.changeTile(explosiveRadiationTile1);

        //add radiation tile
        LTile radiationTile1 = new LTile(8, 3, EnumTileType.OBSTACLE);
        radiationTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_RADIATION, difficulty);
        radiationTile1.setNewObstacle(new LObstacleRegular(EnumObstacleType.RADIATION, EnumObstacleClassification.KNOWN_OBSTACLE));
        //radiationTile1.setObstacle(new LObstacle(EnumObstacleType.RADIATION, EnumObstacleClassification.KNOWN_OBSTACLE));
        gameboard.changeTile(radiationTile1);

        //add unknown explosive tile
        LTile unknownExplosiveTile1 = new LTile(2, 6, EnumTileType.OBSTACLE);
        unknownExplosiveTile1.setGraphicalElement(EnumImageGraphics.OBSTACLE_UNKNOWN_EXPLOSIVE, difficulty);
        unknownExplosiveTile1.setNewObstacle(new LObstacleRegular(EnumObstacleClassification.EXPLOSIVE_UNKNOWN));
        //unknownExplosiveTile1.setObstacle(new LObstacle(EnumObstacleClassification.EXPLOSIVE_UNKNOWN));
        gameboard.changeTile(unknownExplosiveTile1);


        return gameboard;
    }
}
