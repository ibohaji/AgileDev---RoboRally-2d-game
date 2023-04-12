package App.RoborallyApplication.Model.GameObjects;

import App.DTO.ObstacleDTO;
import App.RoborallyApplication.Model.Enums.ObstacleEnum;
import App.RoborallyApplication.Model.Enums.ObstacleTypeEnum;
import App.RoborallyApplication.Model.iToDTO;
import App.RoborallyApplication.Model.GraphicalElements.ObstacleGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

import java.util.UUID;

public class Obstacle implements iToDTO {
    private UUID id;
    private Integer damage;
    public ObstacleGraphicalElement graphicalElement;
    private ObstacleTypeEnum obstacleType;
    private ObstacleEnum obstacle;

    public Obstacle(ObstacleEnum obstacle, ObstacleTypeEnum obstacleType) {
        this.id = UUID.randomUUID();
        this.obstacleType = obstacleType;
        this.obstacle = obstacle;
        this.graphicalElement = new ObstacleGraphicalElement();
        this.damage = obstacle.getDamage();
    }

    public Robot DoDamage(Robot targetrobot) {
        targetrobot.setNrOfLives(
                targetrobot.getNrOfLives() - this.damage
        );
        return targetrobot;
    }

    @Override
    public String toString() {
        return "Obstacle object. Graphical element on obstacle: " + this.graphicalElement.getElementName();
    }

    @Override
    public String DTOasJson() {
        ObstacleDTO obstacledto = new ObstacleDTO(this);
        return JsonHelper.serializeObjectToJson(obstacledto);
    }

    @Override
    public UUID getID() {
        return this.id;
    }

    public ObstacleEnum getObstacleEnum() {
        return obstacle;
    }

    public ObstacleTypeEnum getObstacleTypeEnum() {
        return obstacleType;
    }

}
