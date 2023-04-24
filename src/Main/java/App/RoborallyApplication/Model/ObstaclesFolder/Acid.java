package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.io.File;

public class Acid extends Obstacles {
    private int damage;
    boolean known;
    boolean explosive;
    String imgPath;

    public Acid() {
        super(1);
        this.known = true;
        this.imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "acid.png";
    }
    public Acid(boolean known) {
        super(1);
        this.known = known;
        this.imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "acid.png";
    }



    public void setExplosive(){
        this.explosive = true;
        imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveAcidTile.png";

    }


    @Override
    int getDamage() {
        return 0;
    }

    @Override
    public void applyEffect(LRobot robot) {
        // TODO -> i'am not sure how you want it to damage if explosive
        int numberOflives = robot.getNrOfLives();
        robot.setNrOfLives(numberOflives - damage);
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
