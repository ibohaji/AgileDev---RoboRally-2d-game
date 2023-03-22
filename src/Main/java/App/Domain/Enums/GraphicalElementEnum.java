package App.Domain.Enums;

public enum GraphicalElementEnum {

    DEFAULT_FLOOR("../Resources/Tiles/floor.png"),

    OBSTACLE_UNKNOWN_EXPLOSIVE("../Resources/Tiles/UnknownExplosiveTile.png"),
    OBSTACLE_EXPLOSIVE_ACID("../Resources/Tiles/ExplosiveAcidTile.png"),
    OBSTACLE_EXPLOSIVE_RADIATION("../Resources/Tiles/ExplosiveRadiationTile.png"),
    OBSTACLE_ACID("../Resources/Tiles/acid.png"),
    OBSTACLE_PIT("../Resources/Tiles/pit.png"),
    OBSTACLE_RADIATION("../Resources/Tiles/radiation.png"),
    DEFAULT_ROBOT("../Resources/RobotSkins/robot.png");

    private String pictureFile;

    private GraphicalElementEnum(String pictureFile) {
        this.pictureFile = pictureFile;
    }
    public String getPictureFile() {
        return pictureFile;
    }

}
