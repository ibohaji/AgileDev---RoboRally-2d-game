package CucumberTests;

import App.RoborallyApplication.Model.Cards.ProgrammingCards.AgainCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ChangeDirectionCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.MovementCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.Gameboard;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;
import org.junit.Before;

import java.awt.Point;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class stepsDefinition {
    MovementCard card;
    GameBrain brain;
    DirectionEnum direction;
    Robot robot;
    Point startCords;



    Robot robotOne;
    Robot robotTwo;

    @Before
    public void setup(){
        brain = new GameBrain(1, DifficultyEnum.EASY);
        robot = brain.getPlayers().get(0).getRobot();
        startCords = robot.getCords(); // Currently the only starting position available is (2,0)
        robot.setDirection(DirectionEnum.NORTH);
    }

    @ParameterType("NORTH|SOUTH|WEST|EAST")
    public DirectionEnum direction(String value) {
        return DirectionEnum.valueOf(value);
    }

    @Given("the robot's initial direction as NORTH")
    public void the_robot_s_initial_direction_as_north() {
        setup();
    }

    @When("the robot get the LEFT direction card")
    public void the_robot_get_the_left_direction_card() {
        robot.changeDirection(TurnEnum.LEFT);
    }
    @Then("the expected direction get updated to {direction}")
    public void the_expected_direction_get_updated_to(DirectionEnum direction) {
        assertEquals(robot.getCurrentDirection(), direction);
    }


    @Given("a robot placed at position {string} and direction NORTH")
    public void a_robot_placed_at_position_and_direction_north(String string) {
        setup();
    }

    @When("the robot receives the MovementCard with {int} steps")
    public void the_robot_receives_the_movement_card_with_steps(Integer int1){
        card = new MovementCard(int1);
        card.useCard(robot,brain);
    }

    @Then("the robot should move the specific number of steps in its current direction")
    public void the_robot_should_move_the_specific_number_of_steps_in_its_current_direction() {
        Point expected = new Point(2,3);
        assertEquals(expected,robot.getCords());
    }


    @Given("the robot has previously moved a certain number of steps in a certain direction")
    public void the_robot_has_previously_moved_a_certain_number_of_steps_in_a_certain_direction() {
        setup();
        card = new MovementCard(1);
        card.useCard(robot,brain);

    }


    @And("an AGAIN card is played")
    public void an_again_card_is_played() {
        ProgrammingCard againCard = new AgainCard();
        againCard.useCard(robot,brain);
    }

    //This should only re-play the last played cards and not ALL THE STEPS taken.
    @Then("the robot should move the same number of steps in the same direction as its previous movement")
    public void the_robot_should_move_the_same_number_of_steps_in_the_same_direction_as_its_previous_movement() {
        assertEquals(new Point(2,2),robot.getCords());
    }

    @Given("the game board is set up with robots at positions \\({int}, {int}) and \\({int}, {int})")
    public void the_game_board_is_set_up_with_robots_at_positions_and(Integer int1, Integer int2, Integer int3, Integer int4) {
        brain = new GameBrain(2, DifficultyEnum.EASY);
        robotOne = brain.getPlayers().get(0).getRobot();
        robotTwo = brain.getPlayers().get(1).getRobot();
        robotTwo.setDirection(DirectionEnum.SOUTH);
        robotOne.setCords(new Point(int1,int2));
        robotTwo.setCords(new Point(int3,int4));

    }

    @And("the first robot is facing NORTH")
    public void the_first_robot_is_facing_north() {
        robotOne.setDirection(DirectionEnum.NORTH);

    }
    @And("the first robot uses a movement card with {int} steps")
    public void the_first_robot_uses_a_movement_card_with_steps(Integer int1) {
        card = new MovementCard(int1);
        card.useCard(robotOne,brain);

    }
    @Then("the second robot should be pushed one tile in the direction of the first robot")
    public void the_second_robot_should_be_pushed_one_tile_in_the_direction_of_the_first_robot() {
        Point expected = new Point(2,1);
        assertEquals(expected,robotTwo.getCords());
    }
    @Then("the first robot should end up in the tile previously occupied by the second robot")
    public void the_first_robot_should_end_up_in_the_tile_previously_occupied_by_the_second_robot() {
        Point expected = new Point(2,3);
        assertEquals(expected,robotOne.getCords());
    }

    @Given("a game board with difficulty Easy")
    public void a_game_board_with_difficulty_easy() {
        setup();
        robotOne = brain.getPlayers().get(0).getRobot();
    }
    @And("Robot1 at position x={int},y={int}")
    public void robot1_at_position_x_y(Integer int1, Integer int2) {
    robot.setCords(new Point(int1,int2));
    }
    @Given("Robot1 is facing EAST")
    public void robot1_is_facing_east() {
        robot.setDirection(DirectionEnum.EAST);
    }
    @When("Robot1 moves forward one step")
    public void robot1_moves_forward_one_step() {
        card = new MovementCard(1);
        card.useCard(robot,brain);
    }
    @Then("Robot1 should fall off the board")
    public void robot1_should_fall_off_the_board() {

    }
    @Then("Robot1 should lose a life")
    public void robot1_should_lose_a_life() {
        int lives = robot.getNrOfLives();
        int expected = 4;
        assertEquals(expected,lives);
    }
    @Then("Robot1 should be restored to a random position on the board")
    public void robot1_should_be_restored_to_a_random_position_on_the_board() {
        assertNotEquals(robot.getCords(),new Point(8,8));
    }

    @Then("if Robot1 has no lives left, Robot1 should be removed from the game")
    public void if_robot1_has_no_lives_left_robot1_should_be_removed_from_the_game() {
        robot.setNrOfLives(1);
        robot.setCords(new Point(8,8));
        robot.setDirection(DirectionEnum.EAST);
        card = new MovementCard(1);
        card.useCard(robot,brain);
        Gameboard board = brain.getGameboard();
        ArrayList<Robot> robots = board.getRobots();
        assertFalse(robots.contains(robot));

    }

}

