import App.RoborallyApplication.Model.Enums.Direction;
import App.RoborallyApplication.Model.Enums.RobotID;
import App.RoborallyApplication.Model.GameObjects.Robot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerDTOJunitTest {

    @Test
    public void testConstructor() {
        Robot robot = new Robot(RobotID.R1, Direction.EAST, 3);
        Player player = new Player("Player 1");
        player.assignRobot(robot);

        PlayerDTO playerDTO = new PlayerDTO(player);

        assertNotNull(playerDTO);
        assertEquals("Player 1", playerDTO.displayName);
        assertNotNull(playerDTO.robotDTO);
    }

    @Test
    public void testReturnObjectFromDTO() {
        RobotDTO robotDTO = new RobotDTO(new Robot(RobotID.R2, Direction.WEST, 5));
        ProgrammingCardDTO cardDTO1 = new ProgrammingCardDTO(1);
        ProgrammingCardDTO cardDTO2 = new ProgrammingCardDTO(2);

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
        assertEquals(RobotID.R2, player.getRobot().getRobotID());
        assertEquals(Direction.WEST, player.getRobot().getDirection());
        assertEquals(5, player.getRobot().getHealth());
        assertEquals(2, player.getCards().size());
    }
}
