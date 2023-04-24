package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.io.File;

public class Pit extends Obstacles {
    int damage;
    String imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "pit.png";
    boolean known;


    public Pit(){
        super(2);
        this.known = true;

    }


    public Pit(boolean known){
        super(2);
        this.known = known;
        this.imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "pit.png";
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
        return false;
    }

}
