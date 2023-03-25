package App.Domain.GraphicalElements;

import App.Domain.Enums.DirectionEnum;

public class RobotGraphicalElement extends GraphicalElement{
    private String playerName;
    private DirectionEnum direction;
    public RobotGraphicalElement(String playerName, DirectionEnum direction){
        if(direction == null){
            // Default to north in the beginning of the game?
            direction = DirectionEnum.NORTH;
        }
        this.playerName = playerName;
        this.direction = direction;
    }

    private void addPlayerNameToGraphicalElement(){
        // TODO
    }

    public void changeDirection(DirectionEnum directionToChangeTo){
        this.direction = directionToChangeTo;
    }
}
