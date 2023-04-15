package DTO;

import App.DTO.PlayerDTO;
import App.DTO.ProgrammingCardDTO;
import App.DTO.RobotDTO;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
//import App.RoborallyApplication.Model.Enums.RobotID;
import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameObjects.Robot;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerDTOJunitTest {

    @Test
    public void testConstructor() {
        Robot robot = new Robot();
        Player player = new Player("Player 1");
        player.assignRobot(robot);

        PlayerDTO playerDTO = new PlayerDTO(player);

        assertNotNull(playerDTO);
        assertEquals("Player 1", playerDTO.displayName);
        assertNotNull(playerDTO.robotDTO);
    }

    @Test
    public void testReturnObjectFromDTO() {
        Robot robot1 = new Robot();
        robot1.setNrOfLives(5);
        robot1.setCords(new Point(1, 5));
        robot1.setDirection(DirectionEnum.WEST);
        RobotDTO robotDTO = new RobotDTO(robot1);
        ProgrammingCardDTO cardDTO1 = new ProgrammingCardDTO();
        ProgrammingCardDTO cardDTO2 = new ProgrammingCardDTO();

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.displayName = "Player 2";
        playerDTO.robotDTO = robotDTO;
        playerDTO.cards = new ArrayList<>();
        playerDTO.cards.add(cardDTO1);
        playerDTO.cards.add(cardDTO2);

        Player player = playerDTO.returnObjectFromDTO();

        assertNotNull(player);
        assertEquals("Player 2", player.getDisplayName());
        assertNotNull(player.getRobot());
        //assertEquals(RobotID.R2, player.getRobot().getRobotID());
        //assertEquals(Direction.WEST, player.getRobot().getDirection());
        //assertEquals(5, player.getRobot().getHealth());
        //assertEquals(2, player.getCards().size());
    }
}

enum numPlayers {
    Player 1, Player 2, Player 3, Player 4, Player 5
}
enum Difficulty {
    EASY, MEDIUM, HARD
}
enum LobbyType {
    SINGLE, MULTI
}
enum numAIPlayers {
    0, 1, 2, 3, 4
}
public class PlayerDTOJunitTest {
    @Test
    public void testSetNumPlayer() {
        Game game = new Game()
        game.setNumPlayers(3);
        assertEquals(3, game.getNumPlayers());
    }


    @Test
    public void testSetDifficuly() {
        Game game = new Game()
        game.setDifficulty(MEDIUM);
        assertEquals(Difficulty.MEDIUM, game.getDifficulty());
    }

    @Test
    public void testSetLobbyType() {
        Game game = new Game()
        game.SetLobbyType(MULTI);
        assertEquals(LobbyType.MULTI, game.getLobbyType());
    }

    @Test
    public void testSetnumAIPlayers() {
        Game game = new Game()
        game.SetnumAIPlayers(2);
        assertEquals(numAIPlayers .2, game.getnumAIPlayers())
    }
}