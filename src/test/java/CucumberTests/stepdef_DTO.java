package CucumberTests;

import App.DTO.*;
import App.RoborallyApplication.Model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class stepdef_DTO {
/*

    // Helper method for card assignment (single player version)
    private void assigncards(LPlayer player, int nrofcards) {
        Random rnd = new Random();
        for (int i = 0; i < nrofcards; i++) {
            int choiceForCard = rnd.nextInt(12);
            int choiceForSpecific = rnd.nextInt(3);
            if(choiceForCard < 2){ // againCard
                player.assignCardToPlayer(new LCardAgainProgramming());
            } else if (choiceForCard < 7) { // movementCard
                if(choiceForSpecific == 0) {
                    player.assignCardToPlayer(new LCardMovementProgramming(1));
                } else if (choiceForSpecific == 1) {
                    player.assignCardToPlayer(new LCardMovementProgramming(2));
                } else {
                    player.assignCardToPlayer(new LCardMovementProgramming(3));
                }
            } else { // turn
                if(choiceForSpecific == 0) { // LEFT
                    player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.LEFT));
                } else if (choiceForSpecific == 1) { // RIGHT
                    player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.RIGHT));
                } else { // U-TURN
                    player.assignCardToPlayer(new LCardChangeDirectionProgramming(EnumTurnType.U_TURN));
                }
            }
        }
    }




    */
/*
    Tests for DTO
    *//*


    // Object data transfer for Gameboard
    private LGameboard t_gameboard;
    private GameboardDTO t_gameboarddto;

    @Given("Data prepared in Gameboard")
    public void data_prepared_in_Gameboard() {
        ArrayList<LTile> t_tiles = new ArrayList<>();
        LTile t_tile1 = new LTile(0, 0, EnumTileType.DEFAULT_FLOOR);
        LTile t_tile2 = new LTile(0, 1, EnumTileType.OBSTACLE);
        t_tile2.setNewObstacle(new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE));
        t_tile1.setGraphicalElement(EnumImageGraphics.DEFAULT_FLOOR, EnumDifficulty.EASY);
        t_tile2.setGraphicalElement(EnumImageGraphics.OBSTACLE_ACID, EnumDifficulty.EASY);
        t_tiles.add(t_tile1);
        t_tiles.add(t_tile2);
        ArrayList<LRobot> t_robots = new ArrayList<>();
        LRobot t_robot = new LRobot();
        t_robot.setDirection(EnumDirection.NORTH);
        t_robots.add(t_robot);
        t_gameboard = new LGameboard(new LGameBrain(new LGameConfiguration(1, EnumDifficulty.EASY, true)));
        t_gameboard.setTiles(t_tiles);
        t_gameboard.setRobots(t_robots);
    }

    @When("DTO for Gameboard is created")
    public void DTO_for_Gameboard_is_created() {
        t_gameboarddto = new GameboardDTO(t_gameboard);
    }

    @Then("Data exported out of Gameboard DTO")
    public void data_exported_out_of_Gameboard_DTO() {
        LGameboard t_dtogameboard = t_gameboarddto.returnObjectFromDTO();
        assertEquals(t_gameboard.getTiles().toString(), t_dtogameboard.getTiles().toString());
        assertEquals(t_gameboard.getRobots().toString(), t_dtogameboard.getRobots().toString());
        assertEquals(t_gameboard.getObstacles().toString(), t_dtogameboard.getObstacles().toString());
        assertEquals(t_gameboard.getGameBrain(), t_dtogameboard.getGameBrain());
        t_gameboard = null;
        t_gameboarddto = null;
    }

    // Object data transfer for GameBrain
    private LGameBrain t_gamebrain;
    private GameBrainDTO t_gamebraindto;

    @Given("Data prepared in GameBrain")
    public void data_prepared_in_GameBrain() {
        t_gamebrain = new LGameBrain(new LGameConfiguration(1, EnumDifficulty.EASY, true));

    }

    @When("DTO for GameBrain is created")
    public void DTO_for_GameBrain_is_created() {
        t_gamebraindto = new GameBrainDTO(t_gamebrain.getGameboard(),
                t_gamebrain.getGameConfig(),
                t_gamebrain.getCurrentGamePhase(),
                t_gamebrain);
    }

    @Then("Data exported out of GameBrain DTO")
    public void data_exported_out_of_GameBrain_DTO() {
        LGameBrain t_dtogamebrain = t_gamebraindto.returnObjectFromDTO();
        assertEquals(t_gamebrain.getGameboard().getTiles().toString(), t_dtogamebrain.getGameboard().getTiles().toString());
        assertEquals(t_gamebrain.getGameboard().getObstacles().toString(), t_dtogamebrain.getGameboard().getObstacles().toString());
        assertEquals(t_gamebrain.getGameboard().getRobots().toString(), t_dtogamebrain.getGameboard().getRobots().toString());
        assertEquals(t_gamebrain.getGameConfig(), t_dtogamebrain.getGameConfig());
        //assertEquals(t_gamebrain.getPlayers(), t_dtogamebrain.getPlayers());
        t_gamebrain = null;
        t_gamebraindto = null;
    }

    // Object data transfer for Obstacle
    private LObstacle t_obstacle;
    private ObstacleDTO t_obstacledto;

    @Given("Data prepared in Obstacle")
    public void data_prepared_in_Obstacle() {
        t_obstacle = new LObstacle(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE);
        t_obstacle.setGraphicalElement(new GraphicalElementObstacle());

    }

    @When("DTO for Obstacle is created")
    public void DTO_for_Obstacle_is_created() {
        t_obstacledto = new ObstacleDTO(t_obstacle);
    }

    @Then("Data exported out of Obstacle DTO")
    public void data_exported_out_of_Obstacle_DTO() {
        LObstacle t_dtoobstacle = t_obstacledto.returnObjectFromDTO();
        assertEquals(t_obstacle.getObstacleClassification(), t_dtoobstacle.getObstacleClassification());
        assertEquals(t_obstacle.getObstacleEnum(), t_dtoobstacle.getObstacleEnum());
        assertEquals(t_obstacle.getGraphicalElement(), t_dtoobstacle.getGraphicalElement());

        t_obstacle = null;
        t_obstacledto = null;
    }

    // Object data transfer for Player
    private LPlayer t_player;
    private PlayerDTO t_playerdto;

    @Given("Data prepared in Player")
    public void data_prepared_in_Player() {
        t_player = new LPlayer("player", false);
        this.assigncards(t_player, 5);
        LRobot t__robot = new LRobot();
        t__robot.setDirection(EnumDirection.NORTH);
        t_player.assignRobot(t__robot);

    }

    @When("DTO for Player is created")
    public void DTO_for_Player_is_created() {
        t_playerdto = new PlayerDTO(t_player);
    }

    @Then("Data exported out of Player DTO")
    public void data_exported_out_of_Player_DTO() {
        LPlayer t_dtoplayer = t_playerdto.returnObjectFromDTO();
        assertEquals(t_player.getDisplayName(), t_dtoplayer.getDisplayName());
        assertEquals(t_player.getProgrammingCards(), t_dtoplayer.getProgrammingCards());
        assertEquals(t_player.getRobot().toString(), t_dtoplayer.getRobot().toString());

        t_player = null;
        t_playerdto = null;
    }

    // Object data transfer for ProgrammingCard
    private AbCardProgramming t_programmingcard;
    private ProgrammingCardDTO t_programmingcarddto;

    @Given("Data prepared in ProgrammingCard")
    public void data_prepared_in_ProgrammingCard() {
        t_programmingcard = new LCardMovementProgramming(1);
    }

    @When("DTO for ProgrammingCard is created")
    public void DTO_for_ProgrammingCard_is_created() {
        t_programmingcarddto = new ProgrammingCardDTO(t_programmingcard);
    }

    @Then("Data exported out of ProgrammingCard DTO")
    public void data_exported_out_of_ProgrammingCard_DTO() {
        AbCardProgramming t_dtoprogrammingcard = t_programmingcarddto.returnObjectFromDTO();
        assertEquals(t_programmingcard, t_dtoprogrammingcard);

        t_programmingcard = null;
        t_programmingcarddto = null;
    }

    // Object data transfer for Robot
    private LRobot t_robot;
    private RobotDTO t_robotdto;

    @Given("Data prepared in Robot")
    public void data_prepared_in_Robot() {
        t_robot = new LRobot();
        t_robot.setDirection(EnumDirection.NORTH);
    }

    @When("DTO for Robot is created")
    public void DTO_for_Robot_is_created() {
        t_robotdto = new RobotDTO(t_robot);
    }

    @Then("Data exported out of Robot DTO")
    public void data_exported_out_of_Robot_DTO() {
        LRobot t_dtorobot = t_robotdto.returnObjectFromDTO();
        assertEquals(t_robot.getCords(), t_dtorobot.getCords());
        assertEquals(t_robot.getNrOfLives(), t_dtorobot.getNrOfLives());
        assertEquals(t_robot.getCurrentDirection(), t_dtorobot.getCurrentDirection());
        assertEquals(t_robot.getCheckpointsDone(), t_dtorobot.getCheckpointsDone());

        t_robot = null;
        t_robotdto = null;
    }

    // Object data transfer for Tile
    private LTile t_tile;
    private TileDTO t_tiledto;

    @Given("Data prepared in Tile")
    public void data_prepared_in_Tile() {
        t_tile = new LTile(0,0, EnumTileType.DEFAULT_FLOOR);
        t_tile.setGraphicalElement(new GraphicalElementTile());
    }

    @When("DTO for Tile is created")
    public void DTO_for_Tile_is_created() {
        t_tiledto = new TileDTO(t_tile);
    }

    @Then("Data exported out of Tile DTO")
    public void data_exported_out_of_Tile_DTO() {
        LTile t_dtotile = t_tiledto.returnObjectFromDTO();
        assertEquals(t_tile.doesTileHaveObstacle(), t_dtotile.doesTileHaveObstacle());
        assertEquals(t_tile.doesTileHaveCheckpoint(), t_dtotile.doesTileHaveCheckpoint());
        assertEquals(t_tile.getGraphicalElement(), t_dtotile.getGraphicalElement());
        assertEquals(t_tile.getCoordinates(), t_dtotile.getCoordinates());
        assertEquals(t_tile.getTileTypeEnum(), t_dtotile.getTileTypeEnum());

        t_tile = null;
        t_tiledto = null;
    }
*/

}
