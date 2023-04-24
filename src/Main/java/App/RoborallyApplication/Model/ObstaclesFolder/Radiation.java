package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.io.File;

public class Radiation extends Obstacles{
    private int damage;
    private String location;
    public Radiation() {
        super(2);
        String location = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveAcidTile.png";

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
