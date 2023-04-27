package App.RoborallyApplication.Model;

import App.DTO.ObstacleDTO;
import Utils.JsonHelper;

import java.util.UUID;

public class LObstacle implements IToDTO {
    private UUID id;
    private Integer damage;
    private GraphicalElementObstacle graphicalElement;
    private EnumObstacleClassification obstacleType;
    private EnumObstacleType obstacle;

    public LObstacle(EnumObstacleType obstacle, EnumObstacleClassification obstacleType) {
        this.id = UUID.randomUUID();
        this.obstacleType = obstacleType;
        this.obstacle = obstacle;
        this.graphicalElement = new GraphicalElementObstacle();
        this.damage = obstacle.getDamage();
    }

    // For EXPLOSIVE_UNKNOWN we should not have the EnumObstacle parameter predefined
    public LObstacle(EnumObstacleClassification obstacleType) {
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

    public EnumObstacleType getObstacleEnum() {
        return obstacle;
    }
    public EnumObstacleClassification getObstacleClassification() {
        return obstacleType;
    }
    public GraphicalElementObstacle getGraphicalElement() { return this.graphicalElement; }
    public void setGraphicalElement(GraphicalElementObstacle graphicalElementObstacle) {this.graphicalElement = graphicalElementObstacle; }

}
