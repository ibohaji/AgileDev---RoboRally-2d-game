package CucumberTests;

import App.RoborallyApplication.Model.*;
import Utils.Tuple;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class stepdef_GameBoard {
    private LGameboard gameboard;
    private LGameBrain gamebrain;
    private ArrayList<LTile> tiles = new ArrayList<>();
    private ArrayList<LRobot> robots = new ArrayList<>();
    AbObstacle obstacle;
    Point coordinates;
    LTile tile;
    LRobot robot;
    boolean Actual;

    @Before
    public void setup() {
        ArrayList<Tuple<String, Boolean>> playerInfo = new ArrayList<>();
        playerInfo.add(new Tuple<>("Player1", true));
        LGameConfiguration configuration = new LGameConfiguration(1, EnumDifficulty.EASY, true);
        configuration.createPlayersFromLobby(playerInfo);
        gamebrain = new LGameBrain(configuration);
        gameboard = gamebrain.getGameboard();

    }

    @Given("an initialized gameboard with difficulty set to easy")
    public void an_initialized_gameboard_with_difficulty_set_to_easy() {
        setup();
    }

    @Given("a tile with an acid obstacle at coordinate x={int} and y ={int}")
    public void a_tile_with_an_acid_obstacle_at_coordinate_x_and_y(Integer int1, Integer int2) {
        tile = new LTile(int1, int2, EnumTileType.OBSTACLE);
        coordinates = new Point(int1, int2);
        AbObstacle obstacle = new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN);
        tile.setNewObstacle(obstacle);
    }

    @When("a request to change the current tile with the acid obstacle tile")
    public void a_request_to_change_the_current_tile_with_the_acid_obstacle_tile() {
        gameboard.changeTile(tile);
    }

    @Then("the gameboard's tile at coordinate x={int} and y ={int} should have an acid obstacle")
    public void the_gameboard_s_tile_at_coordinate_x_and_y_should_have_an_acid_obstacle(Integer int1, Integer int2) {
        AbObstacle obstacle = gameboard.getObstacleFromCoordinateNEW(int1, int2);
        assertEquals(tile.getNewObstacle(), obstacle);
    }

    @Given("gameboard and an initiated game")
    public void gameboard_and_an_initiated_game() {
        setup();
    }

    @Given("a robot is standing at coordinate x={int} and y={int}")
    public void a_robot_is_standing_at_coordinate_x_and_y(Integer int1, Integer int2) {
        robot = gamebrain.getPlayers().get(0).getRobot();
        robot.setCords(new Point(int1, int2));
    }

    @Given("a request to detect if the tile is occupied is called on x={int} and y ={int}")
    public void a_request_to_detect_if_the_tile_is_occupied_is_called_on_x_and_y(Integer int1, Integer int2) {
        Actual = gameboard.isTileOccupiedByRobot(int1, int2);
    }

    @Then("the gameboard should return True")
    public void the_gameboard_should_return_true() {
        assertTrue(Actual);
    }

    @Given("an initialized game")
    public void an_initialized_game() {
        setup();
    }

    @Given("a robot at coordinate x={int} and y ={int}")
    public void a_robot_at_coordinate_x_and_y(Integer int1, Integer int2) {
        robot = gameboard.getRobots().get(0);
        robot.setCords(new Point(int1, int2));
    }

    @Given("an explosive obstacle at coordinate x={int} and y ={int}")
    public void an_explosive_obstacle_at_coordinate_x_and_y(Integer int1, Integer int2) {
        tile = new LTile(int1, int2, EnumTileType.OBSTACLE);
        obstacle = new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.EXPLOSIVE_KNOWN);
        tile.setNewObstacle(obstacle);
        gameboard.changeTile(tile);
    }

    @When("the robot steps on the obstacle at the coordinate x={int} and y={int}")
    public void the_robot_steps_on_the_obstacle_at_the_coordinate_x_and_y(Integer int1, Integer int2) {
        robot.setCords(new Point(int1, int2));
        coordinates = new Point(int1, int2);
    }

    @Then("the obstacle explodes and infectes all the surrodning tiles")
    public void the_obstacle_explodes_and_infectes_all_the_surrodning_tiles() {

        // Define the surrounding tiles
        ArrayList<Point> surroundingTiles = new ArrayList<Point>(Arrays.asList(
                new Point(coordinates.x - 1, coordinates.y - 1),
                new Point(coordinates.x - 1, coordinates.y),
                new Point(coordinates.x, coordinates.y - 1),
                new Point(coordinates.x + 1, coordinates.y + 1),
                new Point(coordinates.x, coordinates.y + 1),
                new Point(coordinates.x + 1, coordinates.y - 1),
                new Point(coordinates.x - 1, coordinates.y + 1),
                new Point(coordinates.x +1,coordinates.y)
        ));

        //filtering the tiles that already contains obstacles
        Iterator<Point> iterator = surroundingTiles.iterator();

       while(iterator.hasNext()){
           Point point = iterator.next();
           AbObstacle obs = gameboard.getObstacleFromCoordinateNEW(point.x,point.y);
           if(obs != null){
               iterator.remove();
           }
       }

       //apply effect of robot stepping on obstacle
        obstacle.applyEffect(robot, gamebrain);


        // Check each surrounding tile for infection
        LObstacleRegular expected = new LObstacleRegular(EnumObstacleType.ACID, EnumObstacleClassification.KNOWN_OBSTACLE);
        for (Point point : surroundingTiles) {
            AbObstacle infectedTile = gameboard.getObstacleFromCoordinateNEW(point.x, point.y);
            assertEquals(expected, infectedTile);
        }


    }
}