package App.DTO;

import App.RoborallyApplication.Model.ObstaclesFolder.EnumObstacle;
import App.RoborallyApplication.Model.ObstaclesFolder.EnumObstacleType;
import App.RoborallyApplication.Model.GraphicalElementObstacle;
import App.RoborallyApplication.Model.LObstacle;

public class ObstacleDTO {
    private GraphicalElementObstacle graphicalElementObstacle;
    private EnumObstacle enumObstacle;
    private EnumObstacleType enumObstacleType;

    public ObstacleDTO(LObstacle obstacle) {
        this.graphicalElementObstacle = obstacle.getGraphicalElement();
        this.enumObstacle = obstacle.getObstacleEnum();
        this.enumObstacleType = obstacle.getObstacleTypeEnum();
    }

    public LObstacle returnObjectFromDTO() {
        LObstacle Obstacle = new LObstacle(this.enumObstacle, this.enumObstacleType);
        Obstacle.setGraphicalElement(this.graphicalElementObstacle);

        return Obstacle;
    }

}
