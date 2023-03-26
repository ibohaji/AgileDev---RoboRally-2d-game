package App.RoborallyApplication.Model.GameObjects;

import App.DTO.ObstacleDTO;
import App.RoborallyApplication.Model.Enums.ObstacleEnum;
import App.RoborallyApplication.Model.Enums.ObstacleTypeEnum;
import App.RoborallyApplication.Model.IReloadable;
import App.RoborallyApplication.Model.GraphicalElements.ObstacleGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

import java.util.UUID;

public class Obstacle implements IReloadable {
    private UUID id;
    private Integer damage;
    private Integer xCoordinate;
    private Integer yCoordinate;
    public ObstacleGraphicalElement graphicalElement;
    private ObstacleTypeEnum obstacleTypeEnum;

    private ObstacleEnum obstacleEnum;

    public Obstacle(int xCoordinate, int yCoordinate, int damage) {
        this.id = UUID.randomUUID();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new ObstacleGraphicalElement();
        this.damage = damage;
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
    public String toJson() {
        ObstacleDTO obstacledto = new ObstacleDTO(this);
        return JsonHelper.serializeObjectToJson(obstacledto);
    }

    @Override
    public UUID getID() {
        return this.id;
    }

    public ObstacleEnum getObstacleEnum() {
        return obstacleEnum;
    }

    public ObstacleTypeEnum getObstacleTypeEnum() {
        return obstacleTypeEnum;
    }
}
