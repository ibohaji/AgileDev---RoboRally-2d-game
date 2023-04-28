package App.RoborallyApplication.Model;

import javax.swing.*;
import java.awt.*;

public class LCardMovementProgramming extends AbCardProgramming {
    private int steps;
    private int stepsMade;
    private GraphicalElement graphicalElement = new GraphicalElement();
    public LCardMovementProgramming(int steps){
        this.steps = steps;
        this.stepsMade = 0;
        if (steps == 1) {
            this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.MOVEMENT_CARD_1);
        } else if (steps == 2) {
            this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.MOVEMENT_CARD_2);
        } else if (steps == 3) {
            this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.MOVEMENT_CARD_3);
        }    }


    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        stepsMade += 1;
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
                //gameBrain.removePlayer(gameBrain.findPlayerByRobot(robot));
            } else {
                gameBrain.putRobotToRandomStartPoint(robot);
            }
        } else {
            pushIfOccupied(gameBrain, newPos, directionOfRobot);
            LTile newPositionTile = gameBrain.getGameboard().getTileFromCoordinate(newPos.x, newPos.y);
            if(newPositionTile.doesTileHaveObstacle()){
                robot.setCords(newPos);
                gameBrain.robotStepOnObstacleNEW(gameBrain.getObstacleFromCoordinateNEW(newPos.x, newPos.y), robot);
            } else {
                robot.setCords(newPos);
            }

        }
        if(stepsMade == steps){
            robot.getPlayer().addCardToUsedSequence(this);
        }
    }

    private void pushIfOccupied(LGameBrain gameBrain, Point newPos, EnumDirection directionOfRobot) {
        boolean isTileOccupiedByAnotherRobot = gameBrain.getGameboard().isTileOccupiedByRobot(newPos.x, newPos.y);
        if (isTileOccupiedByAnotherRobot){
            LRobot robotAtCoordinate = gameBrain.getGameboard().getRobotFromCoordinate(newPos.x, newPos.y);
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

    protected int getSteps(){return steps;}
    protected int getStepsMade(){return stepsMade;}
    @Override
    public String toString() {
        return "MOVEMENT CARD, steps: " + this.steps;
    }


}
