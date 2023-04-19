package App.DTO;

import App.RoborallyApplication.Model.EnumObstacle;
import App.RoborallyApplication.Model.EnumObstacleType;
import App.RoborallyApplication.Model.LObstacle;

public class ObstacleDTO {
    public int graphicalElementOrdinal;

    public EnumObstacle enumObstacle;

    public EnumObstacleType enumObstacleType;

    public ObstacleDTO() {

    }
    public ObstacleDTO(LObstacle obstacle) {
        this.graphicalElementOrdinal = obstacle.graphicalElement.getGraphicalElementOrdinal();
        this.enumObstacle = obstacle.getObstacleEnum();
        this.enumObstacleType = obstacle.getObstacleTypeEnum();
    }

}
