package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.EnumGraphicalElementMain;
import App.RoborallyApplication.Model.EnumDirection;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LTile;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GameBoardView extends GameView{
    public GameBoardView(GameController controller, LGameBrain gameBrain) {
        super(controller, gameBrain);
        createView();
    }

    private void createView(){
        setLayout(new GridBagLayout());
        int gridSize = super.gameConfiguration.getBoardDimensions().first();
        // GAME NAME
        add(generateGameNameLabel(), new GridBagConstraintsBuilder(0, 0).gridWidth(gridSize).build());
        // Create grid
        int scaling = gameConfiguration.getScalingSizeForTile();
        ImageIcon scaledDefaultFloor = ImageUtils.scaledImageIcon(new ImageIcon(
                ImageUtils.getImage(EnumGraphicalElementMain.DEFAULT_FLOOR.getElementLocation())),
                scaling,
                scaling);

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                try{
                    LTile tileAtCoordinate = super.gameBrain.getGameboard().getTileFromCoordinate(x, y);
                    if(gameBrain.getGameboard().isTileOccupiedByRobot(x, y)){
                        if(gameBrain.getGameboard().getTileFromCoordinate(x,y).doesTileHaveObstacle()){
                            ImageIcon tileImage = tileAtCoordinate.getGraphicalElement().getImage();
                            EnumDirection directionOfRobot  = gameBrain.getGameboard().getRobotFromCoordinate(x, y).getCurrentDirection();
                            ImageIcon robotWithTile = GameViewHelper.generateImageWithRobot(tileImage, directionOfRobot);
                            add(new JLabel(ImageUtils.mergeTwoImages(scaledDefaultFloor, robotWithTile)), new GridBagConstraintsBuilder(x + 1, y + 1).build());
                        } else {
                            ImageIcon tileImage = tileAtCoordinate.getGraphicalElement().getImage();
                            EnumDirection directionOfRobot  = gameBrain.getGameboard().getRobotFromCoordinate(x, y).getCurrentDirection();
                            add(new JLabel(GameViewHelper.generateImageWithRobot(tileImage, directionOfRobot)), new GridBagConstraintsBuilder(x + 1, y + 1).build());
                        }
                    } else if(gameBrain.getGameboard().getTileFromCoordinate(x,y).doesTileHaveObstacle()) {
                        add(new JLabel(ImageUtils.mergeTwoImages(scaledDefaultFloor,
                                tileAtCoordinate.getGraphicalElement().getImage()
                                )), new GridBagConstraintsBuilder(x + 1, y + 1).build());
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
}
