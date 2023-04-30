package App.RoborallyApplication.Model;

public class LObstacleTreadmill extends AbObstacle{
    private final EnumTreadmillDirection direction;
    public LObstacleTreadmill(EnumTreadmillDirection direction){
        super();
        this.direction = direction;
    }
    @Override
    public void applyEffect(LRobot robot, LGameBrain gameBrain) {
        EnumDirection start = robot.getCurrentDirection();
        switch (direction){
            case NORTH -> robot.setDirection(EnumDirection.NORTH);
            case SOUTH -> robot.setDirection(EnumDirection.SOUTH);
            case EAST -> robot.setDirection(EnumDirection.EAST);
            case WEST -> robot.setDirection(EnumDirection.WEST);
        }
        robot.useProgrammingCard(new LCardMovementProgramming(1), gameBrain);
        robot.setDirection(start);
    }
}
