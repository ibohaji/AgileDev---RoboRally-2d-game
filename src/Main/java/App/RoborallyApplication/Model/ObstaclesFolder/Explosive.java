package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

public class Explosive extends Obstacles{
    String location;

    Explosive(){
        super(2);
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
