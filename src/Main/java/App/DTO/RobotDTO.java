package App.DTO;

import App.RoborallyApplication.Model.EnumDirection;
import App.RoborallyApplication.Model.LRobot;

import java.awt.*;

public class RobotDTO implements iFromDTO{


    public int xCoordinate;
    public int yCoordinate;
    public int nrOfLives;
    public int directionEnumOrdinal;
    public RobotDTO(){}

    public RobotDTO(LRobot robot){
        this.nrOfLives = robot.getNrOfLives();
        this.directionEnumOrdinal = robot.getCurrentDirection().ordinal();
        this.xCoordinate = robot.getCords().x;
        this.yCoordinate = robot.getCords().y;
    }

    @Override
    public LRobot returnObjectFromDTO() {
        LRobot newRobot = new LRobot();
        newRobot.setCords(new Point(this.xCoordinate, this.yCoordinate));
        newRobot.setDirection(EnumDirection.values()[this.directionEnumOrdinal]);
        newRobot.setNrOfLives(nrOfLives);
        return newRobot;
    }
}
