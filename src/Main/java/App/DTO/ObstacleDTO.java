package App.DTO;

import App.RoborallyApplication.Model.Enums.ObstacleEnum;
import App.RoborallyApplication.Model.Enums.ObstacleTypeEnum;
import App.RoborallyApplication.Model.GameObjects.Obstacle;
import Utils.Tuple;

public class ObstacleDTO {
    public int graphicalElementOrdinal;

    public ObstacleEnum obstacleEnum;

    public ObstacleTypeEnum obstacleTypeEnum;

    public ObstacleDTO() {

    }
    public ObstacleDTO(Obstacle obstacle) {
        this.graphicalElementOrdinal = obstacle.graphicalElement.getGraphicalElementOrdinal();
        this.obstacleEnum = obstacle.getObstacleEnum();
        this.obstacleTypeEnum = obstacle.getObstacleTypeEnum();
    }

}
