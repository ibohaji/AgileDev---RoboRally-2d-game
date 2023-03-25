package App.Domain.Enums;

import java.io.File;

public enum GraphicalElementEnum {

    DEFAULT_FLOOR("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "floor.png"),
    OBSTACLE_UNKNOWN_EXPLOSIVE("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "UnknownExplosiveTile.png"),
    OBSTACLE_EXPLOSIVE_ACID("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveAcidTile.png"),
    OBSTACLE_EXPLOSIVE_RADIATION("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "ExplosiveRadiationTile.png"),
    OBSTACLE_ACID("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "acid.png"),
    OBSTACLE_PIT("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "pit.png"),
    OBSTACLE_RADIATION("App" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "radiation.png"),
    ROBOT_NORTH("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotNorth.png"),
    ROBOT_SOUTH("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotSouth.png"),
    ROBOT_EAST("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotEast.png"),
    ROBOT_WEST("App" + File.separator + "Resources" + File.separator + "RobotSkins" + File.separator + "robotWest.png"),
    RIGHT_TREADMIL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "rightTreadmill.png"),
    LEFT_TREADMIL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "leftTreadmill.png"),
    FORWARD_TREADMIL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "forwardTreadmill.png"),
    DOWNARD_TREADMIL("APP" + File.separator + "Resources" + File.separator + "Tiles" + File.separator + "downwardTreadmill.png");

    private String pictureFile;
    private GraphicalElementEnum(String pictureFile) {
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
        System.out.println(DEFAULT_FLOOR.ordinal());
    }

}
