package App.DTO;

import App.RoborallyApplication.Model.EnumDirection;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Model.LRobot;

import java.awt.*;
import java.util.ArrayList;

public class RobotDTO implements iFromDTO{

    private Point cords = new Point();
    private int xCoordinate;
    private int yCoordinate;
    private int nrOfLives;
    private EnumDirection enumDirection;
    private ArrayList<Point> checkpointsDone = new ArrayList<>();

    public RobotDTO(LRobot robot){
        this.cords = robot.getCords();
        this.xCoordinate = robot.getCords().x;
        this.yCoordinate = robot.getCords().y;
        this.nrOfLives = robot.getNrOfLives();
        this.enumDirection = robot.getCurrentDirection();
        this.checkpointsDone = robot.getCheckpointsDone();

    }

    @Override
    public LRobot returnObjectFromDTO() {
        LRobot newRobot = new LRobot();

        newRobot.setCords(this.cords);
        newRobot.setNrOfLives(nrOfLives);
        newRobot.setDirection(this.enumDirection);
        newRobot.setCheckpointsDone(this.checkpointsDone);

        return newRobot;
    }
}
