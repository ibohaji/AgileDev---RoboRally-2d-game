package App.DTO;

import App.Domain.Enums.DirectionEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.Robot;

import java.awt.*;

public class RobotDTO {

    public int xCoordinate;
    public int yCoordinate;
    public int nrOfLives;
    public int graphicalElementOrdinal;
    public int directionEnumOrdinal;
    public RobotDTO(){}

    public RobotDTO(Robot robot){
        this.nrOfLives = robot.getNrOfLives();
        this.directionEnumOrdinal = robot.getCurrentDirection().ordinal();
        this.graphicalElementOrdinal = GraphicalElementEnum.DEFAULT_ROBOT.getElementOrdinal();
        this.xCoordinate = robot.getCords().x;
        this.yCoordinate = robot.getCords().y;
    }

    public Robot createRobotFromDto(){
        Robot newRobot = new Robot();
        newRobot.setCords(new Point(this.xCoordinate, this.yCoordinate));
        newRobot.SetDirection(DirectionEnum.values()[this.directionEnumOrdinal]);
        return newRobot;
    }
}
