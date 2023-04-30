package App.RoborallyApplication.Model;

public abstract class AbObstacle {
    public AbObstacle(){
    }
    public abstract void applyEffect(LRobot robot, LGameBrain gameBrain);
}
