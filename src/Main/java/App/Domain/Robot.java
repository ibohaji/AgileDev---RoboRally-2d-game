package App.Domain;


import App.Domain.Cards.ProgrammingCard;

public class Robot {

    // skin graphics

    private Integer xCoordinate = 0 ;
    private Integer yCoordinate = 0;

    private DirectionEnum currentDirection;
    private int lifeCount = 5;

    public Robot(){
        this.currentDirection = null;}

    protected void move(ProgrammingCard card) {  }

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
}




    // TODO
    // make movement
    // get direction
    //

