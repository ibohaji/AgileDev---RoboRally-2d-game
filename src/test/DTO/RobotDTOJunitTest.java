package App.DTO;

import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RobotDTOJunitTest {

    @Test
    void testReturnObjectFromDTO() {
        // Arrange
        Robot robot = new Robot();
        robot.setNrOfLives(2);
        robot.setCords(new Point(3, 4));
        robot.SetDirection(DirectionEnum.WEST);

        RobotDTO robotDTO = new RobotDTO(robot);

        // Act
        Robot newRobot = robotDTO.returnObjectFromDTO();

        // Assert
        assertEquals(2, newRobot.getNrOfLives());
        assertEquals(DirectionEnum.WEST, newRobot.getCurrentDirection());
        assertEquals(new Point(3, 4), newRobot.getCords());
    }
}
