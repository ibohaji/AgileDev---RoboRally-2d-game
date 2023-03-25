package App.DTO;

import App.Domain.Enums.DirectionEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.Obstacle;
import App.Domain.Robot;
import App.Domain.Tile;
import Utils.Tuple;

import java.awt.*;

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
