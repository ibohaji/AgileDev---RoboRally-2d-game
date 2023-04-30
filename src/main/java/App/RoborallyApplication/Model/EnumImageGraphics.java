package App.RoborallyApplication.Model;

import java.io.File;

public enum EnumImageGraphics {
    DEFAULT_FLOOR("Tiles/floor.png"),
    OBSTACLE_UNKNOWN_EXPLOSIVE("Tiles/UnknownExplosiveTile.png"),
    OBSTACLE_EXPLOSIVE_ACID("Tiles/ExplosiveAcidTile.png"),
    OBSTACLE_EXPLOSIVE_RADIATION("Tiles/ExplosiveRadiationTile.png"),
    OBSTACLE_ACID("Tiles/acid.png"),
    OBSTACLE_PIT("Tiles/pit.png"),
    OBSTACLE_HEALING( "Tiles/HealingTile.png"),
    OBSTACLE_RADIATION("Tiles/radiation.png"),
    ROBOT_NORTH("RobotSkins/robotNorth.png"),
    ROBOT_SOUTH("RobotSkins/robotSouth.png"),
    ROBOT_EAST("RobotSkins/robotEast.png"),
    ROBOT_WEST("RobotSkins/robotWest.png"),
    RIGHT_TREADMILL("Tiles/rightTreadmill.png"),
    LEFT_TREADMILL("Tiles/leftTreadmill.png"),
    FORWARD_TREADMILL("Tiles/forwardTreadmill.png"),
    DOWNWARD_TREADMILL("Tiles/downwardTreadmill.png"),
    START("Tiles/StartingPoint.png"),
    CHECKPOINTS_1("Tiles/Checkpoints_1.png"),
    CHECKPOINTS_2("Tiles/Checkpoints_2.png"),
    FINISH("Tiles/finish.png"),
    UPLEFT_TURN_TREADMILL("Tiles/upleftTurnTreadmill.png"),
    UPRIGHT_TURN_TREADMILL("Tiles/uprightTurnTreadmill.png"),
    BOTTOMLEFT_TURN_TREADMILL( "Tiles/BottomleftTurnTreadmill.png"),
    BOTTOMRIGHT_TURN_TREADMILL("Tiles/bottomrightTurnTreadmill.png"),
    UPRIGHT_REVERSE_TREADMILL("Tiles/UprightReverseTreadmill.png"),
    AGAIN_CARD("Cards/AgainCard.png"),
    LEFT_TURN_CARD("Cards/LeftTurnCard.png"),
    MOVEMENT_CARD_1("Cards/MovementCard_1.png"),
    MOVEMENT_CARD_2("Cards/MovementCard_2.png"),
    MOVEMENT_CARD_3("Cards/MovementCard_3.png"),
    RIGHT_TURN_CARD("Cards/RightTurnCard.png"),
    U_TURN_CARD("Cards/UTurnCard.png");


    private final String pictureFile;

    EnumImageGraphics(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getElementLocation(){
        return "/" + this.pictureFile;
    }

}
