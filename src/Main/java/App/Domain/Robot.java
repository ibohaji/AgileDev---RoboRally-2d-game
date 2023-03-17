package App.Domain;


import App.Domain.Cards.ProgrammingCard;
import java.awt.*;
public class Robot {

    // skin graphics
    //
    private Point cords = new Point(0,0);

    private DirectionEnum currentDirection;
    private int lifeCount = 5;

    public Robot(){
        this.currentDirection = null;}

    public void SetDirection(DirectionEnum direction) { currentDirection = direction; }

    public DirectionEnum getCurrentDirection() {return this.currentDirection;}


    public void changeDirection(String direction){
        switch (direction){
            case "left" :
                this.currentDirection = currentDirection.getLeft();
                break;
            case "right":
                this.currentDirection = currentDirection.getRight();
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






}

