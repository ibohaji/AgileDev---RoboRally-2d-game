package CucumberTests;

import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;

import static org.junit.Assert.assertEquals;


public class stepsDefinition {


    @ParameterType("NORTH|SOUTH|WEST|EAST")
    public DirectionEnum direction(String value) {
        return DirectionEnum.valueOf(value);
    }
    DirectionEnum direction;
    Robot robot = new Robot();

    @Given("the robot's initial direction as NORTH")
    public void the_robot_s_initial_direction_as() {
        // Write code here that turns the phrase above into concrete actions
        robot.setDirection(DirectionEnum.NORTH);
    }
    @When("the robot get the {string} card")
    public void the_robot_get_the_left_direction_card(String orientation) {
        // Write code here that turns the phrase above into concrete actions
        if(orientation.equals("left")){
            robot.changeDirection(TurnEnum.LEFT);
        } else if (orientation.equals("right")) {
            robot.changeDirection(TurnEnum.RIGHT);
        } else {
            robot.changeDirection(TurnEnum.U_TURN);
        }


    }
    @Then("the expected direction get updated to {direction}")
    public void the_expected_direction_get_updated_to(DirectionEnum direction) {
        assertEquals(robot.getCurrentDirection(), direction);
    }


    @Given("a robot with initial position at \\({double})")
    public void a_robot_with_initial_position_at(Double double1) {
        DirectionEnum direction;
        Robot robot = new Robot();

    }
    @When("the robot moves forward")
    public void the_robot_moves_forward() {
        // Write code here that turns the phrase above into concrete actions

    }
    @Then("the expected position is \\({double})")
    public void the_expected_position_is(Double double1) {
    }
}

