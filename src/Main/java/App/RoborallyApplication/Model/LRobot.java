package App.RoborallyApplication.Model;

import Utils.JsonHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class LRobot{
    private Point cords = new Point();
    private GraphicalElementRobot graphicalElement;
    private ArrayList<Point> checkpointsDone;
    private EnumDirection currentDirection;
    private int lifeCount = 5;
    private LPlayer player;

    public LRobot(){
        this.currentDirection = null;
        this.checkpointsDone =  new ArrayList<>();
        this.graphicalElement = new GraphicalElementRobot("PLAYER", null);
        this.graphicalElement.setTileGraphicalElement(EnumImageGraphics.ROBOT_NORTH, EnumDifficulty.HARD);
    }

    public void setDirection(EnumDirection direction) {
        currentDirection = direction;
    }

    public EnumDirection getCurrentDirection() {return this.currentDirection;}

    public void changeDirection(EnumTurnType enumTurnType){
        switch (enumTurnType) {
            case LEFT -> this.currentDirection = currentDirection.getLeft();
            case RIGHT -> {
                this.currentDirection = currentDirection.getRight();
                this.graphicalElement.changeDirection(currentDirection.getRight());
            }
            case U_TURN -> {
                this.currentDirection = currentDirection.getUTurn();
                this.graphicalElement.changeDirection(currentDirection.getUTurn());
            }
        }
    }

    public void addCheckpoint(Point point){
        boolean isAlreadyChecked = false;
        for (Point pointDone : checkpointsDone) {
            if(pointDone.x == point.x && pointDone.y == point.y){
                isAlreadyChecked = true;
            }
        }
        if(!isAlreadyChecked){
            checkpointsDone.add(point);
        }
    }

    public ArrayList<Point> getCheckpointsDone(){
        return this.checkpointsDone;
    }

    public Point getCords(){
        return cords;
    }

    public void setCords(Point newCords){
        cords = newCords;
    }

    public int getNrOfLives(){
        return lifeCount;
    }
    public void setNrOfLives(int lifeCount) {this.lifeCount = lifeCount;}

    /**
     * This method is used when calling the againCard method because you don't want
     * to add the again card to the sequence
     * @param card card to use
     * @param gameBrain gamebrain reference
     */
    public void useProgrammingCard(AbCardProgramming card, LGameBrain gameBrain){
        if(card != null){
            card.useCard(this, gameBrain);
        }
    }
    public void decreaseNumberOfLives(){
        lifeCount-=1;
    }
    public void assignPlayer(LPlayer player){
        this.player = player;
    }
    public LPlayer getPlayer(){
        return player;
    }
}

