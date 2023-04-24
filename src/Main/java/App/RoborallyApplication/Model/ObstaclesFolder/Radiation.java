package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.io.File;

public class Radiation extends Obstacles{
    private int damage;
    private String imgPath;
    boolean known;
    boolean explosive;

    public Radiation() {
        super(2);
        String imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveAcidTile.png";
        this.known = true;
    }
    public Radiation(boolean known) {
        super(2);
        this.known = known;
        String imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveAcidTile.png";
    }

    public void setExplosive(){
        this.explosive = true;
    }

    @Override
    int getDamage() {
        return 0;
    }

    @Override
    public void applyEffect(LRobot robot) {

    }

    @Override
    public String getGraphicalElement() {
        String imageLocationFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        return imageLocationFolder + File.separator + imgPath;

    }

    @Override
    public boolean isKnown() {
        return known;
    }

    @Override
    public boolean isExplosive() {
        return explosive;
    }

}
