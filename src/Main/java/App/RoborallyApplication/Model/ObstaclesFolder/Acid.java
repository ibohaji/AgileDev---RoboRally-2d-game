package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

public class Acid extends Obstacles {
    private int damage;
    String location;

    public Acid() {
        super(1);
    }


    @Override
    int getDamage() {
        return 0;
    }

    @Override
    void applyEffect(LRobot robot) {

    }

    @Override
    public String getGraphicalElement() {
        return location;
    }
}
