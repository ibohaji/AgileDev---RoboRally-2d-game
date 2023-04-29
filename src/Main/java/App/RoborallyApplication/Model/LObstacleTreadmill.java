package App.RoborallyApplication.Model;

import java.awt.*;

public class LObstacleTreadmill extends AbObstacle{
    private EnumTreadmillDirection direction;
    public LObstacleTreadmill(EnumTreadmillDirection direction){
        super();
        this.direction = direction;
    }
    @Override
    public void applyEffect(LRobot robot, LGameBrain gameBrain) {
        EnumDirection start = robot.getCurrentDirection();
        int x = robot.getCords().x;
        int y = robot.getCords().y;
        switch (direction){
            case NORTH ->{
                robot.setDirection(EnumDirection.NORTH);
                y -= 1;
            }
            case SOUTH -> {
                robot.setDirection(EnumDirection.SOUTH);
                y += 1;
            }
            case EAST -> {
                robot.setDirection(EnumDirection.EAST);
                x += 1;
            }
            case WEST -> {
                robot.setDirection(EnumDirection.WEST);
                x -= 1;
            }
        }
        robot.useProgrammingCard(new LCardMovementProgramming(1), gameBrain);
        robot.setDirection(start);
    }
}
