package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.awt.*;

public class Healing extends Obstacles {
    private final int healing = 2;
    String location;

    public Healing() {
        super(0);
    }

    @Override
    int getDamage() {
        return 0;
    }

    @Override
    void applyEffect(LRobot robot) {
        int currentLives = robot.getNrOfLives();
        robot.setNrOfLives(currentLives+healing);
    }

    @Override
    public String getGraphicalElement() {
        return location;
    }
}
