package App.RoborallyApplication.Model.ObstaclesFolder;

import App.RoborallyApplication.Model.LRobot;

import java.io.File;

public class TreadMill extends Obstacles {
    int damage;
    String imgPath;
    String rightTreadmill = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "rightTreadmill.png";
    String leftTreadmill =  "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "leftTreadmill.png";
    String downWardTreadmill  = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "downwardTreadmill.png";

    boolean known;

    public TreadMill(){
        super(0);
        this.known = true;
        imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "downwardTreadmill.png";

    }

    public TreadMill(boolean known){
        super(0);
        this.known = known;
        imgPath = "App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "downwardTreadmill.png";

    }



    public String getRightTreadmill(){
        String imageLocationFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        return imageLocationFolder + File.separator + rightTreadmill;

    }
    public String getDownTreadmill(){

        String imageLocationFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        return imageLocationFolder + File.separator + downWardTreadmill;

    }
    public String getLeftTreadmill()       {
        String imageLocationFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        return imageLocationFolder + File.separator + leftTreadmill;

}


    @Override
    int getDamage() {
        return 0;
    }

    @Override
    public void applyEffect(LRobot robot) {
    }
    @Override
    public String getGraphicalElement(){
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
