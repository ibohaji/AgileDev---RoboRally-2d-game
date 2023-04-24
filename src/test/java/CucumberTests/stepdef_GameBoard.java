package CucumberTests;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Model.ObstaclesFolder.Obstacles;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class stepdef_GameBoard {
    private LGameboard gameboard;
    private LGameBrain gamebrain;
    private ArrayList<LTile> tile = new ArrayList<>();
    private ArrayList<Obstacles> obstacle = new ArrayList<>();
    private ArrayList<LRobot> robot = new ArrayList<>();

    @Before
    public void setup(){
        gamebrain = new LGameBrain(new LGameConfiguration(1, EnumDifficulty.EASY, true));
        gameboard = gamebrain.getGameboard();
    }

    @Given("The point \\({double}) is an empty tile")
    public void the_point_is_an_empty_tile(Double double1) {
        setup();
    }
    @When("GameBrain wants to know the status of this tile")
    public void game_brain_wants_to_know_the_status_of_this_tile() {
        tile = gamebrain.getGameboard().getTiles();
    }
    @Then("The GameBoard returns an empty tile")
    public void the_game_board_returns_an_empty_tile() {
        assertEquals(gameboard.getTiles(),tile);
    }

    @Given("There is an obstacle at point \\({double})")
    public void there_is_an_obstacle_at_point(Double double1) {
        setup();
    }
    @When("the GameBrain wants to know the status of this obstacle")
    public void the_game_brain_wants_to_know_the_status_of_this_obstacle() {
        obstacle = gamebrain.getGameboard().getObstacles();
    }
    @Then("The GameBoard returns the information of this obstacle")
    public void the_game_board_returns_the_information_of_this_obstacle() {
        assertEquals(gameboard.getObstacles(),obstacle);
    }

    @Given("A robot is at point\\({double}) standing on the GameBoard")
    public void a_robot_is_at_point_standing_on_the_game_board(Double double1) {
        setup();
    }
    @When("GameBrain wants to move this robot")
    public void game_brain_wants_to_move_this_robot() {
        robot = gamebrain.getGameboard().getRobots();
    }
    @Then("The GameBoard returns the information of this robot")
    public void the_game_board_returns_the_information_of_this_robot() {
        assertEquals(gameboard.getRobots(),robot);
    }
}
