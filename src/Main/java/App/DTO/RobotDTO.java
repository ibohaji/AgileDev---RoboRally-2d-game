package App.DTO;

import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;

import java.awt.*;

public class RobotDTO implements iFromDTO{


    public int xCoordinate;
    public int yCoordinate;
    public int nrOfLives;
    public int directionEnumOrdinal;
    public RobotDTO(){}

    public RobotDTO(Robot robot){
        this.nrOfLives = robot.getNrOfLives();
        this.directionEnumOrdinal = robot.getCurrentDirection().ordinal();
        this.xCoordinate = robot.getCords().x;
        this.yCoordinate = robot.getCords().y;
    }

    @Override
    public Robot returnObjectFromDTO() {
        Robot newRobot = new Robot();
        newRobot.setCords(new Point(this.xCoordinate, this.yCoordinate));
        newRobot.setDirection(DirectionEnum.values()[this.directionEnumOrdinal]);
        newRobot.setNrOfLives(nrOfLives);
        return newRobot;
    }
}
