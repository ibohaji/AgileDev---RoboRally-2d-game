package App.DTO;

import App.Model.GameObjects.Obstacle;
import Utils.Tuple;

public class ObstacleDTO {

    public Integer xCoordinate;
    public Integer yCoordinate;
    public int graphicalElementOrdinal;

    public ObstacleDTO() {

    }

    public ObstacleDTO(Obstacle obstacle) {
        Tuple<Integer, Integer> coordinates = obstacle.getCoordinates();
        this.xCoordinate = coordinates.first();
        this.yCoordinate = coordinates.second();
        this.graphicalElementOrdinal = obstacle.graphicalElement.getGraphicalElementOrdinal();
    }

}
