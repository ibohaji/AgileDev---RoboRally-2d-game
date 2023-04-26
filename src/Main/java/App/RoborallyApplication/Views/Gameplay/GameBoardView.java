package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.*;
import Utils.Fonts;
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
        add(generateGameNameLabel(), new GridBagConstraintsBuilder(0, 0).inset(0,0,20,0).gridWidth(gridSize).fill(GridBagConstraints.BOTH).build());

        // GRID
        int scaling = gameConfiguration.getScalingSizeForTile();
        ImageIcon scaledDefaultFloor = ImageUtils.scaledImageIcon(new ImageIcon(
                ImageUtils.getImage(EnumGraphicalElementMain.DEFAULT_FLOOR.getElementLocation())),
                scaling,
                scaling);

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                try{
                    LTile tileAtCoordinate = super.gameBrain.getGameboard().getTileFromCoordinate(x, y);
                    if(gameBrain.getGameboard().isTileOccupiedByRobot(x, y)){ // ROBOT ON TILE
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
                    } else if(tileAtCoordinate.doesTileHaveObstacle()) {
                        ImageIcon img2 = ImageUtils.scaledImageIcon(tileAtCoordinate.getGraphicalElement().getImage(), gameBrain.getGameConfig().getScalingSizeForTile(), gameConfiguration.getScalingSizeForTile());
                        add(new JLabel(ImageUtils.mergeTwoImages(scaledDefaultFloor,img2)), new GridBagConstraintsBuilder(x + 1, y + 1).build());

                    } else {
                        add(new JLabel(tileAtCoordinate.getGraphicalElement().getImage()),
                                new GridBagConstraintsBuilder(x + 1, y + 1).build());
                    }

                } catch (NullPointerException exception){
                    throw new RuntimeException("Should never happen, but this exception is thrown in GameBoardView when setting up graphics");
                }
            }
        }


        // PLAYER INFO
        if(this.gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)){
            LPlayer player = this.gameBrain.getPlayerWhoIsCurrentlyMoving();
            JLabel playerName = new JLabel(player.getDisplayName());
            playerName.setFont(Fonts.BOLDMEDIUM);
            JLabel robotLives = new JLabel("Number of lives: " + player.getRobot().getNrOfLives());
            robotLives.setFont(Fonts.BOLDMEDIUM);

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
            infoPanel.add(Box.createHorizontalGlue());
            infoPanel.add(playerName);
            infoPanel.add(Box.createRigidArea(new Dimension(20, 0)));
            infoPanel.add(robotLives);
            infoPanel.add(Box.createHorizontalGlue());

            add(infoPanel, new GridBagConstraintsBuilder(0, gridSize + 1).inset(25, 0, 0, 0).gridWidth(gridSize).fill(GridBagConstraints.HORIZONTAL).build());
        }

    }
}
