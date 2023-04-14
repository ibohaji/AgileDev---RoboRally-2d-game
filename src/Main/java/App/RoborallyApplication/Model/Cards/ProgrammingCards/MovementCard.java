package App.RoborallyApplication.Model.Cards.ProgrammingCards;

import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.GameRunning.DirectionEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;

import java.awt.*;

public class MovementCard extends ProgrammingCard{
    private final int steps;
    public MovementCard(int steps){
        this.steps = steps;
    }

    @Override
    public void useCard(Robot robot, GameBrain gameBrain) {
        Point currentPos = robot.getCords();
        Point newPos = new Point(currentPos);
        DirectionEnum directionOfRobot= robot.getCurrentDirection();
        for (int i = 0; i < steps; i++) {
            switch (directionOfRobot){
                case NORTH -> newPos.y +=   1;
                case SOUTH -> newPos.y -=   1;
                case EAST -> newPos.x +=   1;
                case WEST -> newPos.x -=  1;
            }
            if(!gameBrain.isPositionOnBoard(newPos)){
                continue;
            }
            boolean isTileOccupiedByAnotherRobot = gameBrain.getGameboard().isTileOccupiedByRobot(newPos.x, newPos.y);
            if (isTileOccupiedByAnotherRobot){
                Robot robotAtCoordinate = gameBrain.getGameboard().getRobotFromCoordinate(newPos.x, newPos.y);
                // push robotAtCoordinate
                switch (directionOfRobot){
                    case NORTH -> gameBrain.pushRobot(robotAtCoordinate, DirectionEnum.SOUTH);
                    case SOUTH -> gameBrain.pushRobot(robotAtCoordinate, DirectionEnum.NORTH);
                    case EAST -> gameBrain.pushRobot(robotAtCoordinate, DirectionEnum.WEST);
                    case WEST -> gameBrain.pushRobot(robotAtCoordinate, DirectionEnum.EAST);
                }
            }
            Tile newPositionTile = gameBrain.getGameboard().getTileFromCoordinate(newPos.x, newPos.y);
            // check for obstacle on tile

            // set new coordinate
            robot.setCords(newPos);
        }

    }
}
