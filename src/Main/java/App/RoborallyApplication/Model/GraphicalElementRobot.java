package App.RoborallyApplication.Model;

public class GraphicalElementRobot extends GraphicalElement {
    private String playerName;
    private EnumDirection direction;
    public GraphicalElementRobot(String playerName, EnumDirection direction){
        if(direction == null){
            // Default to north in the beginning of the game?
            direction = EnumDirection.NORTH;
        }
        this.playerName = playerName;
        this.direction = direction;
    }

    private void addPlayerNumberToRobot(){
        // TODO
    }

    public void changeDirection(EnumDirection directionToChangeTo){
        this.direction = directionToChangeTo;
    }
}
