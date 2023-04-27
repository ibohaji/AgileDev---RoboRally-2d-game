package App.RoborallyApplication.Model;

import javax.swing.*;
import java.awt.*;

public class LCardMovementProgramming extends AbCardProgramming {
    private int steps;
    private GraphicalElement graphicalElement = new GraphicalElement();
    public LCardMovementProgramming(int steps){
        this.steps = steps;
        if (steps == 1) {
            this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.MOVEMENT_CARD_1);
        } else if (steps == 2) {
            this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.MOVEMENT_CARD_2);
        } else if (steps == 3) {
            this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.MOVEMENT_CARD_3);
        }    }


    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        for (int i = 0; i < steps; i++) {
            Point currentPos = robot.getCords();
            Point newPos = new Point(currentPos);
            EnumDirection directionOfRobot= robot.getCurrentDirection();
            switch (directionOfRobot){
                case NORTH -> newPos.y -= 1;
                case SOUTH -> newPos.y += 1;
                case EAST -> newPos.x += 1;
                case WEST -> newPos.x -= 1;
            }
            if(!gameBrain.isPositionOnBoard(newPos)){
                robot.decreaseNumberOfLives();
                if(robot.getNrOfLives() < 1){
                    gameBrain.removeRobot(robot);
                    gameBrain.removePlayer(gameBrain.findPlayerByRobot(robot));
                } else {
                    gameBrain.putRobotToRandomStartPoint(robot);
                }
            } else {
                System.out.println("OLD POSITION: " + currentPos);
                System.out.println("NEW POSITION: " + newPos);
                pushIfOccupied(gameBrain, newPos, directionOfRobot);
                System.out.println("Looking for tile in: " + newPos.x + "&" + newPos.y);
                LTile newPositionTile = gameBrain.getGameboard().getTileFromCoordinate(newPos.x, newPos.y);
                System.out.println("Did we find newpositionTile? => " + (newPositionTile != null));
                // check for obstacle on tile
                if(newPositionTile.doesTileHaveObstacle()){
                    robot.setCords(newPos);
                    gameBrain.robotStepOnObstacleNEW(gameBrain.getObstacleFromCoordinateNEW(newPos.x, newPos.y), robot);
                } else {
                    robot.setCords(newPos);
                }

            }
        }
    }

    private void pushIfOccupied(LGameBrain gameBrain, Point newPos, EnumDirection directionOfRobot) {
        boolean isTileOccupiedByAnotherRobot = gameBrain.getGameboard().isTileOccupiedByRobot(newPos.x, newPos.y);
        if (isTileOccupiedByAnotherRobot){
            System.out.println("Tile was occupied by another robot, pushing");
            LRobot robotAtCoordinate = gameBrain.getGameboard().getRobotFromCoordinate(newPos.x, newPos.y);
            // push robotAtCoordinate
            switch (directionOfRobot){
                case NORTH -> gameBrain.pushRobot(robotAtCoordinate, EnumDirection.SOUTH);
                case SOUTH -> gameBrain.pushRobot(robotAtCoordinate, EnumDirection.NORTH);
                case EAST -> gameBrain.pushRobot(robotAtCoordinate, EnumDirection.WEST);
                case WEST -> gameBrain.pushRobot(robotAtCoordinate, EnumDirection.EAST);
            }
        }
    }

    @Override
    public ImageIcon getCardImageIcon() {
        return this.graphicalElement.getImage();
    }

    @Override
    public String toString() {
        return "MOVEMENT CARD, steps: " + this.steps;
    }


}
