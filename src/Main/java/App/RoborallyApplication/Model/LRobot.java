package App.RoborallyApplication.Model;


import App.DTO.RobotDTO;
import Utils.JsonHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class LRobot implements IToDTO {

    private UUID id;
    private Point cords = new Point(0,0);
    private GraphicalElementRobot graphicalElement;
    private ArrayList<Point> checkpointsDone = new ArrayList<>();
    private EnumDirection currentDirection;
    private int lifeCount = 5;
    LPlayer player;
    public LRobot(){
        this.id = UUID.randomUUID();
        this.currentDirection = null;

        this.graphicalElement = new GraphicalElementRobot("PLAYER", null);
        this.graphicalElement.setTileGraphicalElement(EnumGraphicalElementMain.ROBOT_NORTH, EnumDifficulty.HARD);
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
        cords.x = newCords.x;
        cords.y =newCords.y;
    }

    public int getNrOfLives(){
        return lifeCount;
    }
    public void setNrOfLives(int lifeCount) {this.lifeCount = lifeCount;}

    public void useProgrammingCard(AbCardProgramming card, LGameBrain gameBrain){
        card.useCard(this, gameBrain);
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
    @Override
    public String toString() {
        return "Robot. Direction: " + this.currentDirection.name() + ". X: " + this.cords.x + " Y: " + this.cords.y +
                ". Lives: " + this.lifeCount;
    }

    @Override
    public String DTOasJson() {
        RobotDTO robotDTO = new RobotDTO(this);
        return JsonHelper.serializeObjectToJson(robotDTO);
    }

    @Override
    public UUID getID() {
        return null;
    }
}

