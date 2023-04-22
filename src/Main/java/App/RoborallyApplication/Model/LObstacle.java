package App.RoborallyApplication.Model;

import App.DTO.ObstacleDTO;
import Utils.JsonHelper;

import java.util.UUID;

public class LObstacle implements IToDTO {
    private UUID id;
    private Integer damage;
    private GraphicalElementObstacle graphicalElement;
    private EnumObstacleType obstacleType;
    private EnumObstacle obstacle;

    public LObstacle(EnumObstacle obstacle, EnumObstacleType obstacleType) {
        this.id = UUID.randomUUID();
        this.obstacleType = obstacleType;
        this.obstacle = obstacle;
        this.graphicalElement = new GraphicalElementObstacle();
        this.damage = obstacle.getDamage();
    }

    // For EXPLOSIVE_UNKNOWN we should not have the EnumObstacle parameter predefined
    public LObstacle(EnumObstacleType obstacleType) {
        this.id = UUID.randomUUID();
        this.obstacleType = obstacleType;
        this.graphicalElement = new GraphicalElementObstacle();
    }

    public LRobot DoDamage(LRobot targetrobot) {
        targetrobot.setNrOfLives(targetrobot.getNrOfLives() + this.damage);
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
    //public void setObstacleEnum(EnumObstacle enumObstacle) { this.obstacle = enumObstacle; }
    public EnumObstacleType getObstacleTypeEnum() {
        return obstacleType;
    }
    //public void setObstacleTypeEnum(EnumObstacleType enumObstacleType) { this.obstacleType = enumObstacleType; }
    public GraphicalElementObstacle getGraphicalElement() { return this.graphicalElement; }
    public void setGraphicalElement(GraphicalElementObstacle graphicalElementObstacle) {this.graphicalElement = graphicalElementObstacle; }

}