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
        System.out.println("Before stepping on treadmill:" + robot.getCords().x + " & " + robot.getCords().y);
        switch (direction){
            case NORTH -> robot.setDirection(EnumDirection.NORTH);
            case SOUTH -> robot.setDirection(EnumDirection.SOUTH);
            case EAST -> robot.setDirection(EnumDirection.EAST);
            case WEST -> robot.setDirection(EnumDirection.WEST);
        }
        robot.useProgrammingCard(new LCardMovementProgramming(1), gameBrain);
        System.out.println("After stepping on treadmill:" + robot.getCords().x + " & " + robot.getCords().y);
        Point cord1 = gameBrain.getGameboard().getRobots().stream().filter(x -> x.getCords().equals(robot.getCords())).findFirst().get().getCords();
        System.out.println("After stepping on treadmill in gamebrain:" + cord1.x + " & " + cord1.y);
        robot.setDirection(start);
        Point cords = robot.getCords();
        if(gameBrain.getGameboard().getObstacleFromCoordinate(cords.x, cords.y) != null){
            gameBrain.getGameboard().getObstacleFromCoordinateNEW(cords.x, cords.y).applyEffect(robot, gameBrain);
        }
    }
}
