package App.Model.GameObjects;


import App.DTO.RobotDTO;
import App.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.Model.GameRunning.GameBrain;
import App.Model.IReloadable;
import App.Model.GameRunning.DifficultyEnum;
import App.Model.GameRunning.DirectionEnum;
import App.Model.Enums.GraphicalElementEnum;
import App.Model.GraphicalElements.RobotGraphicalElement;
import Utils.JsonHelper;

import java.awt.*;
import java.util.UUID;

public class Robot implements IReloadable {

    private UUID id;
    private Point cords = new Point(0,0);
    private RobotGraphicalElement graphicalElement;
    private DirectionEnum currentDirection;
    private int lifeCount = 5;

    public Robot(){
        this.id = UUID.randomUUID();
        this.currentDirection = null;
        this.graphicalElement = new RobotGraphicalElement("PLAYER", null);
        this.graphicalElement.changeGraphicalElement(GraphicalElementEnum.ROBOT_NORTH, DifficultyEnum.HARD);
    }

    public void SetDirection(DirectionEnum direction) {
        currentDirection = direction;
    }

    public DirectionEnum getCurrentDirection() {return this.currentDirection;}

    public void changeDirection(String direction){
        switch (direction){
            case "left" :
                this.currentDirection = currentDirection.getLeft();
                break;
            case "right":
                this.currentDirection = currentDirection.getRight();
                this.graphicalElement.changeDirection(currentDirection.getRight());
                break;
            case "u-turn":
                this.currentDirection = currentDirection.getUTurn();
                this.graphicalElement.changeDirection(currentDirection.getUTurn());
                break;
        }
    }

    public Point getCords(){
        return cords;
    }

    public void setCords(Point newCords){
        cords.x += newCords.x;
        cords.y+=newCords.y;
    }

    public int getNrOfLives(){
        return this.lifeCount;
    }
    public void setNrOfLives(int lifeCount) {this.lifeCount = lifeCount;}

    public void useProgrammingCard(ProgrammingCard card, GameBrain gameBrain){
        card.useCard(this, gameBrain);
    }

    @Override
    public String toString() {
        return "Robot. Direction: " + this.currentDirection.name() + ". X: " + this.cords.x + " Y: " + this.cords.y +
                ". Lives: " + this.lifeCount;
    }

    @Override
    public String toJson() {
        RobotDTO robotDTO = new RobotDTO(this);
        return JsonHelper.serializeObjectToJson(robotDTO);
    }

    @Override
    public UUID getID() {
        return null;
    }
}

