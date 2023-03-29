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

    public static JLabel generateImageWithRobot(ImageIcon tileImage, DirectionEnum directionEnum){
        JLabel labelToReturn = null;
        switch (directionEnum){
            case NORTH -> labelToReturn = new JLabel(ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotNorth));
            case SOUTH -> labelToReturn = new JLabel(ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotSouth));
            case EAST -> labelToReturn = new JLabel(ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotEast));
            case WEST -> labelToReturn = new JLabel(ImageUtils.mergeTwoImages(tileImage, GameViewHelper.robotWest));
        }
        return labelToReturn;
    }
}
