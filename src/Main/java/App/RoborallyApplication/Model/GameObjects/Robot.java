package App.RoborallyApplication.Model.GameObjects;


import App.DTO.RobotDTO;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.iToDTO;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.GraphicalElements.RobotGraphicalElement;
import Utils.JsonHelper;

import java.awt.*;
import java.util.UUID;

public class Robot implements iToDTO {

    private UUID id;
    private Point cords = new Point(0,0);
    private RobotGraphicalElement graphicalElement;
    private DirectionEnum currentDirection;
    private int lifeCount = 5;

    public Robot(){
        this.id = UUID.randomUUID();
        this.currentDirection = null;
        this.graphicalElement = new RobotGraphicalElement("PLAYER", null);
        this.graphicalElement.setGraphicalElement(GraphicalElementEnum.ROBOT_NORTH, DifficultyEnum.HARD);
    }

    public void SetDirection(DirectionEnum direction) {
        currentDirection = direction;
    }

    public DirectionEnum getCurrentDirection() {return this.currentDirection;}

    public void changeDirection(TurnEnum turnEnum){
        switch (turnEnum){
            case LEFT:
                this.currentDirection = currentDirection.getLeft();
                break;
            case RIGHT:
                this.currentDirection = currentDirection.getRight();
                this.graphicalElement.changeDirection(currentDirection.getRight());
                break;
            case U_TURN:
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
    public String DTOasJson() {
        RobotDTO robotDTO = new RobotDTO(this);
        return JsonHelper.serializeObjectToJson(robotDTO);
    }

    @Override
    public UUID getID() {
        return null;
    }
}

