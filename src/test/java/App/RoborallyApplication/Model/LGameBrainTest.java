package App.RoborallyApplication.Model;

import Utils.Tuple;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class LGameBrainTest extends TestCase {
LGameBrain gameBrain;

@Before
public void setup() {
    ArrayList<Tuple<String, Boolean>> playerInfo = new ArrayList<>();
    playerInfo.add(new Tuple<>("Player1", true));
    LGameConfiguration configuration = new LGameConfiguration(1, EnumDifficulty.EASY, true);
    configuration.createPlayersFromLobby(playerInfo);
    gameBrain = new LGameBrain(configuration);
}

@Test
    public void testSetCurrentGamePhase() {
        setup();
        gameBrain.setCurrentGamePhase(EnumGamePhase.ROUND_START);
        assertTrue(gameBrain.getCurrentGamePhase() == EnumGamePhase.ROUND_START);

    gameBrain.setCurrentGamePhase(EnumGamePhase.GAME_OVER);
        assertTrue(gameBrain.getCurrentGamePhase() == EnumGamePhase.GAME_OVER);

    gameBrain.setCurrentGamePhase(EnumGamePhase.ROUND_END);
        assertTrue(gameBrain.getCurrentGamePhase() == EnumGamePhase.ROUND_END);


    gameBrain.setCurrentGamePhase(EnumGamePhase.PROGRAMMING_PHASE);
        assertTrue(gameBrain.getCurrentGamePhase() == EnumGamePhase.PROGRAMMING_PHASE);

    gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
        assertTrue(gameBrain.getCurrentGamePhase() == EnumGamePhase.MOVEMENT_PHASE);

}



    public void testGivePlayersCardsForRound() {
    }

    public void testMakeMovement_norobots() {
        // Case: No robots
        LGameBrain brain = new LGameBrain(new LGameConfiguration(0,EnumDifficulty.EASY,true));
        brain.makeMovement();
        assertEquals(EnumGamePhase.GAME_OVER,brain.getCurrentGamePhase());

    }

    public void testMakeMovement() {
        setup();
        // Case one or more robots
        gameBrain.startRound();
        LPlayer player = gameBrain.getPlayers().get(0);
        ArrayList<AbCardProgramming> playersCards = player.getProgrammingCards();
        LCardSequence sequence = new LCardSequence(player);
        for(AbCardProgramming card:playersCards){
            sequence.addCard(card);
        }

        player.setOrderedCardSequence(sequence);
        Point positionBeforeMovement = player.getRobot().getCords();
        gameBrain.makeMovement();
        Point positionAfterMovement = player.getRobot().getCords();

        //assertNotSame(positionAfterMovement,positionBeforeMovement);
    }


    public void testAreThereMovementsLeftInThisRound() {
        setup();
        gameBrain.startRound();
        LPlayer player = gameBrain.getPlayers().get(0);
        ArrayList<AbCardProgramming> playersCards = player.getProgrammingCards();
        LCardSequence sequence = new LCardSequence(player);
        for (AbCardProgramming card : playersCards) {
            sequence.addCard(card);
        }

        player.setOrderedCardSequence(sequence);
        assertTrue(gameBrain.areThereMovementsLeftInThisRound());
    }

        public void testEndRound() {
        setup();
        gameBrain.endRound();
        assertEquals(EnumGamePhase.ROUND_END,gameBrain.getCurrentGamePhase());
    }

    public void testCanGameContinue() {
    }

    public void testCanRobotCollectCheckpoint() {
    }

    public void testSetWinner() {
    }

    public void testGetPlayerWhoWon() {
    }

    public void testIsThereAWinner() {
    }

    public void testSetCardSequenceForPlayer() {
    }

    public void testHaveAllPlayersSubmittedSequence() {
    }

    public void testGetPlayerWithoutCardSequence() {
    }

    public void testSetCardSequencesForAi() {
    }

    public void testGetRandomObstacleTypeToExplode() {
    }

    public void testGetObstacleFromCoordinateNEW() {
    }

    public void testExplodeObstacleToTilesNEW() {
    }

    public void testUpdateGraphicalElementOnTile() {
    }

    public void testRemovePlayer() {
    }

    public void testGetPlayerWhoIsCurrentlyMoving() {
    }

    public void testRemoveRobot() {
    }

    public void testPushRobot() {
    }

    public void testRobotStepOnObstacleNEW() {
    }

    public void testIsPositionOnBoard() {
    }

    public void testPutRobotToRandomStartPoint() {
    }

    public void testRestoreGameboard() {
    }

    public void testGetCurrentGamePhase() {
    }

    public void testGetGameConfig() {
    }

    public void testGetPlayers() {
    }

    public void testGetGameboard() {
    }
}
//TODO
// 1) get damage test from EnumObstacleType
// 2) graphicalElement getImage test -> check that image not null
// 3) againcCard class useCard method test all if cases
// 4) CardChangeDirection test getTurnType correct
// 5) CardMovement test pushIfOccupied method works
// 6) CardMovement test getSteps and getStepsMade -> create new MovementCard and check if getStepsMade == 0 and getSteps != 0
// 7) GameBrain
//      7.3) Test endRound -> check gamePhase is ROUND_END
//      7.4) Test canGameContinue ->
//      7.5) Test all winner methods -> Set a random player to winner, see if isThereAWinner() and getPlayerWhoWon are correct
//      7.6) Test haveAllPlayersSubmittedSequences()
//      7.7) Test setCardSequencesForAi -> Create game with 1 real, 1 AI player, give them cards and call this method,
//                                          AI should have a sequence after setCardSequenceForAi()
//      7.8) Test getRandomObstacleTypeToExplode() -> do a for loop (maybe 10 loops) where you and check that you
//                                          can get both ACID and RADIATION
//      7.9) Test pushRobot
//      7.10) Test moveRobotWithCard