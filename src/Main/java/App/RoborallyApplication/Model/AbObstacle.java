package App.RoborallyApplication.Model;

import javax.swing.*;

public abstract class AbObstacle {
    public AbObstacle(){
    }
    public abstract void applyEffect(LRobot robot, LGameBrain gameBrain);
}
