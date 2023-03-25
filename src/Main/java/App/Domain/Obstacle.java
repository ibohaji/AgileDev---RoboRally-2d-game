package App.Domain;

import App.DTO.ObstacleDTO;
import App.Domain.GraphicalElements.ObstacleGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

public class Obstacle implements serializable {

    private Integer xCoordinate;
    private Integer yCoordinate;
    public ObstacleGraphicalElement graphicalElement;

    public Obstacle(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new ObstacleGraphicalElement();
    }

    public Tuple<Integer, Integer> getCoordinates() {
        return new Tuple<>(this.xCoordinate, this.yCoordinate);
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
