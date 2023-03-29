package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameObjects.Tile;
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

        // Get all robots images ready for pasting
        ImageIcon robotNorth = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_NORTH.getElementLocation()));
        ImageIcon robotSouth = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_SOUTH.getElementLocation()));
        ImageIcon robotEast = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_EAST.getElementLocation()));
        ImageIcon robotWest = new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.ROBOT_WEST.getElementLocation()));

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                try{
                    Tile tileAtCoordinate = super.gameBrain.getGameboard().getTileFromCoordinate(x, y);
                    if(gameBrain.getGameboard().isTileOccupiedByRobot(x, y)){
                        ImageIcon tileImage = tileAtCoordinate.getGraphicalElement().getImage();
                        DirectionEnum directionOfRobot  = gameBrain.getGameboard().getRobotFromCoordinate(x, y).getCurrentDirection();
                        ImageIcon robotImage = null;
                        switch (directionOfRobot){
                            case NORTH -> robotImage = robotNorth;
                            case SOUTH -> robotImage = robotSouth;
                            case EAST -> robotImage = robotEast;
                            case WEST -> robotImage = robotWest;
                        }
                        add(new JLabel(ImageUtils.mergeTwoImages(tileImage, robotImage)), new GridBagConstraintsBuilder(x + 1, y + 1).build());
                    } else {
                        add(new JLabel(tileAtCoordinate.getGraphicalElement().getImage()),
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
