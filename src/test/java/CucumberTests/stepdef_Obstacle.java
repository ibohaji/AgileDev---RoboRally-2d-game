package CucumberTests;

import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Model.LRobot;
import App.RoborallyApplication.Model.LTile;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdef_Obstacle {
    private LGameBrain t_gamebrain;
    private LPlayer t_player;
    private LRobot t_robot;
    private LTile t_tile;

    @Before

    @Given("a tile with explosive known obstacle and default floor tiles surrounding it")
    public void aTileWithExplosiveKnownObstacleAndDefaultFloorTilesSurroundingIt() {

    }

    @When("robot steps on the explosive obstacle")
    public void robotStepsOnTheExplosiveObstacle() {
    }

    @Then("the surrounding tiles will get infected with that obstacle")
    public void theSurroundingTilesWillGetInfectedWithThatObstacle() {
    }

    @Given("a tile with explosive unknown obstacle and default floor tiles surrounding it")
    public void aTileWithExplosiveUnknownObstacleAndDefaultFloorTilesSurroundingIt() {
    }

    @When("robot steps on the unknown explosive obstacle")
    public void robotStepsOnTheUnknownExplosiveObstacle() {
    }

    @Then("the surrounding tiles will get infected with a randomly chosen obstacle type")
    public void theSurroundingTilesWillGetInfectedWithARandomlyChosenObstacleType() {
    }

    @Given("a tile with a forward treadmill pointing NORTH obstacle on it")
    public void aTileWithAForwardTreadmillPointingNORTHObstacleOnIt() {
    }

    @When("robot that has an initial direction of SOUTH steps on the treadmill")
    public void robotThatHasAnInitialDirectionOfSOUTHStepsOnTheTreadmill() {
    }

    @Then("the robot will get moved by the treadmill and the the robots direction will remain SOUTH")
    public void theRobotWillGetMovedByTheTreadmillAndTheTheRobotsDirectionWillRemainSOUTH() {
    }
}
