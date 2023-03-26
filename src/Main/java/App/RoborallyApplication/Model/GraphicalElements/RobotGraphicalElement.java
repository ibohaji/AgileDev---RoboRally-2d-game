package App.RoborallyApplication.Model.GraphicalElements;

import App.RoborallyApplication.Model.GameRunning.DirectionEnum;

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

    private void addPlayerNumberToRobot(){
        // TODO
    }

    public void changeDirection(DirectionEnum directionToChangeTo){
        this.direction = directionToChangeTo;
    }
}
