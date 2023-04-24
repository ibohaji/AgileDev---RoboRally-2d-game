
package App.DTO;

import App.RoborallyApplication.Model.ObstaclesFolder.EnumObstacleType;
import App.RoborallyApplication.Model.GraphicalElementObstacle;
import App.RoborallyApplication.Model.ObstaclesFolder.Obstacles;


public class ObstacleDTO {
    private String graphicalElementObstacle;
    private boolean enumObstacleType;

    public ObstacleDTO(Obstacles obstacle) {
        this.graphicalElementObstacle = obstacle.getGraphicalElement();
        this.enumObstacleType = obstacle.isExplosive();
    }

    //public Obstacles returnObjectFromDTO() {

    //}

}
