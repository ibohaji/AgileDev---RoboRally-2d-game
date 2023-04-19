package App.RoborallyApplication.Model;

import App.DTO.ObstacleDTO;
import Utils.JsonHelper;

import java.util.UUID;

public class LObstacle implements IToDTO {
    private UUID id;
    private Integer damage;
    public GraphicalElementObstacle graphicalElement;
    private EnumObstacleType obstacleType;
    private EnumObstacle obstacle;

    public LObstacle(EnumObstacle obstacle, EnumObstacleType obstacleType) {
        this.id = UUID.randomUUID();
        this.obstacleType = obstacleType;
        this.obstacle = obstacle;
        this.graphicalElement = new GraphicalElementObstacle();
        this.damage = obstacle.getDamage();
    }

    public LRobot DoDamage(LRobot targetrobot) {
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

    public EnumObstacle getObstacleEnum() {
        return obstacle;
    }

    public EnumObstacleType getObstacleTypeEnum() {
        return obstacleType;
    }

}
