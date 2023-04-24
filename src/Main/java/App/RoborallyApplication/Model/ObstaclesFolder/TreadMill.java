package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.io.File;

public class TreadMill extends Obstacles {
    int damage;
    String location;


    TreadMill(){
        super(0);
    }

    @Override
    int getDamage() {
        return 0;
    }

    @Override
    void applyEffect(LRobot robot) {
    }
    @Override
    public String getGraphicalElement(){
        return location;
    }
}
