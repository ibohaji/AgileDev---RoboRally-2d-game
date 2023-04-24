package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

public abstract class Obstacles {
    private int damage;
    private String graphicalElement;
    Obstacles(int damage){
        this.damage = damage;
    }

    abstract int getDamage();

    abstract void applyEffect(LRobot robot);
    public abstract String getGraphicalElement();
}
