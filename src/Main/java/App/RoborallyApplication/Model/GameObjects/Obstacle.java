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
    private Integer xCoordinate;
    private Integer yCoordinate;
    public ObstacleGraphicalElement graphicalElement;
    private ObstacleTypeEnum obstacleType;
    private ObstacleEnum obstacle;

    public Obstacle(int xCoordinate, int yCoordinate, ObstacleEnum obstacle, ObstacleTypeEnum obstacleType) {
        this.id = UUID.randomUUID();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.obstacleType = obstacleType;
        this.obstacle = obstacle;
        this.graphicalElement = new ObstacleGraphicalElement();
        this.damage = obstacle.getDamage();
    }

    public Tuple<Integer, Integer> getCoordinates() {
        return new Tuple<>(this.xCoordinate, this.yCoordinate);
    }

    public Robot DoDamage(Robot targetrobot) {
        targetrobot.setNrOfLives(
                targetrobot.getNrOfLives() - this.damage
        );
        return targetrobot;
    }

    @Override
    public String toString() {
        return "Obstacle object. X coordinate: " + this.xCoordinate + " Y coordinate: " + this.yCoordinate +
                ". Graphical element on obstacle: " + this.graphicalElement.getElementName();
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
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().isInstance(Obstacle.class)){
            return ((Obstacle) obj).xCoordinate.intValue() == this.xCoordinate.intValue() &&
                    ((Obstacle) obj).yCoordinate.intValue() == this.yCoordinate.intValue();
        } else {
            return false;
        }
    }
}
