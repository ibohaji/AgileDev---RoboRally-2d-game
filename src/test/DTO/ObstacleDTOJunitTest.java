import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import App.RoborallyApplication.Model.Enums.ObstacleEnum;
import App.RoborallyApplication.Model.Enums.ObstacleTypeEnum;
import App.RoborallyApplication.Model.GameObjects.Obstacle;
import App.Utils.Tuple;

public class ObstacleDTOJunitTest {

    @Test
    public void testObstacleDTOConstructor() {
        // create a new obstacle
        Tuple<Integer, Integer> coordinates = new Tuple<>(1, 2);
        Obstacle obstacle = new Obstacle(coordinates, ObstacleEnum.GEAR, ObstacleTypeEnum.CONVEYOR);

        // create an ObstacleDTO object
        ObstacleDTO obstacleDTO = new ObstacleDTO(obstacle);

        // check if the ObstacleDTO object has been created correctly
        assertEquals(obstacleDTO.xCoordinate, coordinates.first());
        assertEquals(obstacleDTO.yCoordinate, coordinates.second());
        assertEquals(obstacleDTO.graphicalElementOrdinal, obstacle.graphicalElement.getGraphicalElementOrdinal());
        assertEquals(obstacleDTO.obstacleEnum, obstacle.getObstacleEnum());
        assertEquals(obstacleDTO.obstacleTypeEnum, obstacle.getObstacleTypeEnum());
    }
}
