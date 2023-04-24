package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.awt.*;
import java.io.File;

public class Healing extends Obstacles {
    private final int healing = 2;
    String location;
    boolean known;
    String imgPath;

    public Healing() {
        super(0);
        this.known = true;
        this.imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "HealingTile.png";

    }
    public Healing(boolean known) {
        super(0);
        this.known = known;
        this.imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "HealingTile.png";

    }

    @Override
    int getDamage() {
        return 0;
    }

    @Override
    public void applyEffect(LRobot robot) {
        int currentLives = robot.getNrOfLives();
        robot.setNrOfLives(currentLives+healing);
    }

    @Override
    public String getGraphicalElement() {
        String imageLocationFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        return imageLocationFolder + File.separator + imgPath;

    }

    @Override
    public boolean isKnown() {
        return false;
    }

    @Override
    public boolean isExplosive() {
        return false;
    }
}
