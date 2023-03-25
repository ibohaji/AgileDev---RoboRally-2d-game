package App.Domain;

import App.DTO.ObstacleDTO;
import App.Domain.GraphicalElements.ObstacleGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

public class Obstacle implements serializable {

    private Integer damage;

    private Integer xCoordinate;
    private Integer yCoordinate;
    public ObstacleGraphicalElement graphicalElement;

    public Obstacle(int xCoordinate, int yCoordinate, int damage) {
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

}
