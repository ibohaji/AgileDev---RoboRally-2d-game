package CucumberTests;

import App.RoborallyApplication.Model.*;
import Utils.Tuple;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class stepdef_Obstacle {
    private LGameBrain t_gamebrain;
    private LPlayer t_player;
    private LRobot t_robot;
    private LTile t_tile;

    @Before()
    public void setup(){
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(1, EnumDifficulty.HARD, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < 1; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
        t_player = t_gamebrain.getPlayers().get(0);
        LCardSequence movementSequence = new LCardSequence(t_player);
        movementSequence.addCard(new LCardMovementProgramming(1));
        t_player.setOrderedCardSequence(movementSequence);
    }

    @Given("a tile with explosive known obstacle and default floor tiles surrounding it")
    public void aTileWithExplosiveKnownObstacleAndDefaultFloorTilesSurroundingIt() {
        t_tile = t_gamebrain.getGameboard().getTileFromCoordinate(6, 3);
    }

    @When("robot steps on the explosive obstacle")
    public void robotStepsOnTheExplosiveObstacle() {
        t_robot = t_gamebrain.getPlayers().get(0).getRobot();
        t_robot.setCords((new Point(6, 2)));
        t_robot.setDirection(EnumDirection.SOUTH);
        t_gamebrain.makeMovement();

    }

    @Then("the surrounding tiles will get infected with that obstacle")
    public void theSurroundingTilesWillGetInfectedWithThatObstacle() {
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(5, 2).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(6, 2).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(7, 2).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(5, 3).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(6, 3).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(7, 3).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(5, 4).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(6, 4).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(7, 4).doesTileHaveObstacle());
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
