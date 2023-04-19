package App.RoborallyApplication.Model;

import java.io.File;

public enum EnumGraphicalElementMain {
    DEFAULT_FLOOR("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "floor.png"),
    OBSTACLE_UNKNOWN_EXPLOSIVE("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "UnknownExplosiveTile.png"),
    OBSTACLE_EXPLOSIVE_ACID("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveAcidTile.png"),
    OBSTACLE_EXPLOSIVE_RADIATION("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveRadiationTile.png"),
    OBSTACLE_ACID("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "acid.png"),
    OBSTACLE_PIT("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "pit.png"),
    OBSTACLE_HEALING("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "HealingTile.png"),
    OBSTACLE_RADIATION("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "radiation.png"),
    ROBOT_NORTH("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotNorth.png"),
    ROBOT_SOUTH("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotSouth.png"),
    ROBOT_EAST("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotEast.png"),
    ROBOT_WEST("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotWest.png"),
    RIGHT_TREADMILL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "rightTreadmill.png"),
    LEFT_TREADMILL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "leftTreadmill.png"),
    FORWARD_TREADMILL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "forwardTreadmill.png"),
    DOWNWARD_TREADMILL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "downwardTreadmill.png"),
    START("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "StartingPoint.png"),
    CHECKPOINTS_1("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "Checkpoints_1.png"),
    CHECKPOINTS_2("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "Checkpoints_2.png"),
    FINISH("APP"+File.separator +"Resources"+File.separator+"Tiles"+File.separator+"finish.png"),
    UPLEFT_TURN_TREADMILL("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "upleftTurnTreadmill.png"),
    UPRIGHT_TURN_TREADMILL("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "uprightTurnTreadmill.png"),
    BOTTOMLEFT_TURN_TREADMILL("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "BottomleftTurnTreadmill.png"),
    BOTTOMRIGHT_TURN_TREADMILL("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "bottomrightTurnTreadmill.png"),
    UPRIGHT_REVERSE_TREADMILL("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "UprightReverseTreadmill.png"),
    BOTTOMRIGHT_REVERSE_TREADMILL("APP" +File.separator + "Resources" + File.separator + "Tiles" + File.separator + "BottomrightReverseTreadmill.png");


    private String pictureFile;

    private EnumGraphicalElementMain(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getPictureFile() {
        return pictureFile;
    }

    public String getElementLocation(){
        String imageLocationFolder = System.getProperty("user.dir") + File.separator + "src" + File.separator + "Main" + File.separator + "java";
        return imageLocationFolder + File.separator + this.pictureFile;
    }
    public int getElementOrdinal(){
        return this.ordinal();
    }

    public static void main(String[] args) {

    }

}
