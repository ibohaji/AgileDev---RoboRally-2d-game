package App.Domain;


import App.DTO.RobotDTO;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.DirectionEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.GraphicalElements.RobotGraphicalElement;
import Utils.JsonHelper;

import java.awt.*;
public class Robot implements serializable {
    private Point cords = new Point(0,0);

    private RobotGraphicalElement graphicalElement;
    private DirectionEnum currentDirection;
    private int lifeCount = 5;

    public Robot(){
        this.currentDirection = null;
        this.graphicalElement = new RobotGraphicalElement("PLAYER", null);
        this.graphicalElement.changeGraphicalElement(GraphicalElementEnum.DEFAULT_ROBOT, DifficultyEnum.EASY);
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
}

