package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import Utils.ImageUtils;

import javax.swing.*;

public class GameViewHelper {

    public static ImageIcon robotNorth = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_NORTH.getElementLocation()));
    public static ImageIcon robotSouth = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_SOUTH.getElementLocation()));
    public static ImageIcon robotEast = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_EAST.getElementLocation()));
    public static ImageIcon robotWest = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_WEST.getElementLocation()));

    public static ImageIcon generateImageWithRobot(ImageIcon tileImage, DirectionEnum directionEnum){
        ImageIcon imageToReturn = null;
        switch (directionEnum){
            case NORTH -> imageToReturn = ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotNorth);
            case SOUTH -> imageToReturn = ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotSouth);
            case EAST -> imageToReturn = ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotEast);
            case WEST -> imageToReturn = ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotWest);
        }
        return imageToReturn;
    }
}
