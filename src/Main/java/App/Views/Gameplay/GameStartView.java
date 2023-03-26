package App.Views.Gameplay;

import App.Controllers.GameController.GameController;
import App.Model.GameRunning.DirectionEnum;
import App.Model.Enums.GraphicalElementEnum;
import App.Model.GameRunning.GameBrain;
import App.Model.GameObjects.Tile;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GameStartView extends GameView{
    public GameStartView(GameController controller, GameBrain gameBrain) {
        super(controller, gameBrain);
        createView();
    }

    private void createView(){
        setLayout(new GridBagLayout());
        int gridSize = super.gameConfiguration.getBoardDimensions().first();
        // GAME NAME
        add(generateGameNameLabel(), new GridBagConstraintsBuilder(0, 0).gridWidth(gridSize).build());
        // Create grid

        // Get all robots ready
        ImageIcon robotNorth = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_NORTH.getElementLocation()));
        ImageIcon robotSouth = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_SOUTH.getElementLocation()));
        ImageIcon robotEast = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_EAST.getElementLocation()));
        ImageIcon robotWest = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_WEST.getElementLocation()));

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                try{
                    Tile tileAtCoordinate = super.gameBrain.getTileFromCoordinate(x, y);
                    if(gameBrain.isTileOccupiedByRobot(x, y)){
                        ImageIcon tileImage = tileAtCoordinate.graphicalElement.getImage();
                        DirectionEnum directionOfRobot  = gameBrain.getRobotFromCoordinate(x, y).getCurrentDirection();
                        ImageIcon robotImage = null;
                        switch (directionOfRobot){
                            case NORTH -> robotImage = robotNorth;
                            case SOUTH -> robotImage = robotSouth;
                            case EAST -> robotImage = robotEast;
                            case WEST -> robotImage = robotWest;
                        }
                        add(new JLabel(ImageUtils.mergeTwoImages(tileImage, robotImage)), new GridBagConstraintsBuilder(x + 1, y + 1).build());
                    } else {
                        add(new JLabel(tileAtCoordinate.graphicalElement.getImage()),
                                new GridBagConstraintsBuilder(x + 1, y + 1).build());
                    }

                } catch (NullPointerException exception){
                    throw new RuntimeException("Will never happen");
                }
            }
        }

    }

    private JLabel generateGameNameLabel(){
        JLabel gameNameLabel = new JLabel("ROBORALLY");
        gameNameLabel.setFont(Fonts.TITLE);
        return gameNameLabel;
    }
}
