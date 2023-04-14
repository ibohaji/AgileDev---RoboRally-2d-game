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

import static org.junit.Assert.assertEquals;


public class stepsDefinition {
    MovementCard card;
    GameBrain brain;
    DirectionEnum direction;
    Robot robot;
    Point startCords;

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



}

