package CucumberTests;

import App.RoborallyApplication.Model.Cards.ProgrammingCards.ChangeDirectionCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.MovementCard;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.Gameboard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;

import java.awt.*;

import static org.junit.Assert.assertEquals;


public class stepsDefinition {

    DirectionEnum direction;
    Robot robot = new Robot();

    @ParameterType("NORTH|SOUTH|WEST|EAST")
    public DirectionEnum direction(String value) {
        return DirectionEnum.valueOf(value);
    }

    @Given("the robot's initial direction as NORTH")
    public void the_robot_s_initial_direction_as_north() {
        robot.setDirection(DirectionEnum.NORTH);
    }

    @When("the robot get the LEFT direction card")
    public void the_robot_get_the_left_direction_card() {
        robot.changeDirection(TurnEnum.LEFT);
    }
    @Then("the expected direction get updated to {direction}")
    public void the_expected_direction_get_updated_to(DirectionEnum direction) {
        assertEquals(robot.getCurrentDirection(), direction);
    }

    @Given("a game board with a robot placed at a position")
    public void a_game_board_with_a_robot_placed_at_a_position() {
        Gameboard board = new Gameboard();
        Point initialCords = new Point(1,1);
        robot.setCords(initialCords);
    }

    @When("the robot receives the MOVEMENT card with a specific number of steps")
    public void the_robot_receives_the_movement_card_with_a_specific_number_of_steps() {
        GameBrain brain = new GameBrain();
        ProgrammingCard card = new MovementCard(3);
        robot.useProgrammingCard(card,brain);
    }

    @Then("the robot should move the specific number of steps in its current direction")
    public void the_robot_should_move_the_specific_number_of_steps_in_its_current_direction() {
        
    }

}

