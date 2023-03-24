package App.DTO;

import App.Domain.Enums.DirectionEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.Obstacle;
import App.Domain.Robot;

import java.awt.*;

public class ObstacleDTO {

    public int xCoordinate;
    public int yCoordinate;
    public int nrOfLives;
    public int graphicalElementOrdinal;
    public ObstacleDTO(){

    }

    public ObstacleDTO(Obstacle obstacle){

    }
}
