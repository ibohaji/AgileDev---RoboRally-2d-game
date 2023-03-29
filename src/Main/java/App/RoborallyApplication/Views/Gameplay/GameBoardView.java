package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameObjects.Tile;
import Utils.GridBagConstraintsBuilder;
import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GameBoardView extends GameView{
    public GameBoardView(GameController controller, GameBrain gameBrain) {
        super(controller, gameBrain);
        createView();
    }

    private void createView(){
        setLayout(new GridBagLayout());
        int gridSize = super.gameConfiguration.getBoardDimensions().first();
        // GAME NAME
        add(generateGameNameLabel(), new GridBagConstraintsBuilder(0, 0).gridWidth(gridSize).build());
        // Create grid

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                try{
                    Tile tileAtCoordinate = super.gameBrain.getGameboard().getTileFromCoordinate(x, y);
                    if(gameBrain.getGameboard().isTileOccupiedByRobot(x, y)){
                        ImageIcon tileImage = tileAtCoordinate.getGraphicalElement().getImage();
                        DirectionEnum directionOfRobot  = gameBrain.getGameboard().getRobotFromCoordinate(x, y).getCurrentDirection();
                        add(GameViewHelper.generateImageWithRobot(tileImage, directionOfRobot), new GridBagConstraintsBuilder(x + 1, y + 1).build());
                    } else if(gameBrain.getGameboard().getTileFromCoordinate(x,y).doesTileHaveObstacle()) {
                        add(new JLabel(ImageUtils.mergeTwoImages(new ImageIcon(ImageUtils.getImage(GraphicalElementEnum.DEFAULT_FLOOR.getElementLocation())),
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
