package App.Domain;

import App.Domain.Cards.Card;

public class Robot extends InGameObject{

    // skin graphics
    //
    private Integer xCoordinate = 0 ;
    private Integer yCoordinate = 0;
    private DirectionEnum direction;

    private int lifeCount;
    public Robot(int lifeCount, DirectionEnum direction){
        this.lifeCount = lifeCount;
        this.direction = direction;
    }

    protected void makeMove(Card card){
        // check cardtype
    }



    // TODO
    // make movement
    // get direction
    //
}
