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
    private int robotLives;

    private LObstacleRegular obstacleRegular;

    @Given("a tile with explosive known obstacle and default floor tiles surrounding it")
    public void aTileWithExplosiveKnownObstacleAndDefaultFloorTilesSurroundingIt() {
        setup();
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

    @Given("a tile with explosive unknown obstacle with default floor tiles and some other obstacles surrounding it")
    public void aTileWithExplosiveUnknownObstacleAndDefaultFloorTilesSurroundingIt() {
        setup();
        t_robot = t_gamebrain.getPlayers().get(0).getRobot();
        t_robot.setNrOfLives(10);
        t_robot.setCords(new Point(1, 7));
        t_robot.setDirection(EnumDirection.EAST);
        t_player.setCardSequenceToNull();
        LCardSequence newSeq = new LCardSequence(t_player);
        newSeq.addCard(new LCardMovementProgramming(1));
        t_player.setOrderedCardSequence(newSeq);
    }

    @When("robot steps on the unknown explosive obstacle")
    public void robotStepsOnTheUnknownExplosiveObstacle() {
        t_gamebrain.makeMovement();
    }

    @Then("the surrounding tiles will get infected with a randomly chosen obstacle type and the tiles with existing obstacles will remain the same")
    public void theSurroundingTilesWillGetInfectedWithARandomlyChosenObstacleType() {
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(1, 6).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(2, 6).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(3, 6).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(1, 7).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(2, 7).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(3, 7).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(1, 8).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(2, 8).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getGameboard().getTileFromCoordinate(3, 8).doesTileHaveObstacle());
        assertTrue(t_gamebrain.getObstacleFromCoordinateNEW(1,8) instanceof LObstacleTreadmill);
        assertTrue(t_gamebrain.getObstacleFromCoordinateNEW(2,8) instanceof LObstacleTreadmill);
        assertTrue(t_gamebrain.getObstacleFromCoordinateNEW(3,8) instanceof LObstacleTreadmill);
    }

    @Given("a tile with a forward treadmill pointing NORTH obstacle on it")
    public void aTileWithAForwardTreadmillPointingNORTHObstacleOnIt() {
        setup();
        t_robot = t_gamebrain.getPlayers().get(0).getRobot();
    }

    @When("robot that has an initial direction of SOUTH steps on the treadmill")
    public void robotThatHasAnInitialDirectionOfSOUTHStepsOnTheTreadmill() {
        t_robot.setDirection(EnumDirection.SOUTH);
        t_robot.setCords(new Point(11, 11));
        t_gamebrain.pushRobot(t_robot, EnumDirection.SOUTH);
    }

    @Then("the robot will get moved by the treadmill and the the robots direction will remain SOUTH")
    public void theRobotWillGetMovedByTheTreadmillAndTheTheRobotsDirectionWillRemainSOUTH() {
        assertEquals(EnumDirection.SOUTH, t_robot.getCurrentDirection());
        assertEquals(11, t_robot.getCords().x);
        assertEquals(9, t_robot.getCords().y);
    }

    @Given("a tile with a known regular obstacle")
    public void aTileWithAKnownRegularObstacle() {
        setup();
        t_robot = t_gamebrain.getPlayers().get(0).getRobot();
        robotLives = t_robot.getNrOfLives();
        obstacleRegular = (LObstacleRegular)t_gamebrain.getObstacleFromCoordinateNEW(11, 6);
    }

    @When("robot steps on the obstacle")
    public void robotStepsOnTheObstacle() {
        t_robot.setCords(new Point(11, 7));
        t_robot.setDirection(EnumDirection.NORTH);
        t_gamebrain.makeMovement();
    }

    @Then("robot gets the damage from the obstacle")
    public void robotGetsTheDamageFromTheObstacle() {
        assertEquals(robotLives + obstacleRegular.getObstacleType().getDamage(), t_robot.getNrOfLives());
    }

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
}
