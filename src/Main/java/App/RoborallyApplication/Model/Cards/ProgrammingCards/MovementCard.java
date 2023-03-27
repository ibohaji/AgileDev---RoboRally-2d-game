package App.RoborallyApplication.Model.Cards.ProgrammingCards;

import App.RoborallyApplication.Model.GameObjects.Robot;
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
                case NORTH -> newPos.y = currentPos.y + 1;
                case SOUTH -> newPos.y = currentPos.y - 1;
                case EAST -> newPos.x = currentPos.x + 1;
                case WEST -> newPos.x = currentPos.x - 1;
            }
            boolean isTileOccupied = gameBrain.isTileOccupiedByRobot(newPos.x, newPos.y);
            if (isTileOccupied){
                Robot robotAtCoordinate = gameBrain.getRobotFromCoordinate(newPos.x, newPos.y);
                Point currentCoordiantesOfOtherRobot = robotAtCoordinate.getCords();
                Point newCoordiantesOfOtherRobot = new Point(currentCoordiantesOfOtherRobot);
                // push robotAtCoordinate
                switch (directionOfRobot){
                    case NORTH -> newCoordiantesOfOtherRobot.y = currentCoordiantesOfOtherRobot.y + 1;
                    case SOUTH -> newCoordiantesOfOtherRobot.y = currentCoordiantesOfOtherRobot.y - 1;
                    case EAST -> newCoordiantesOfOtherRobot.x = currentCoordiantesOfOtherRobot.y + 1;
                    case WEST -> newCoordiantesOfOtherRobot.x = currentCoordiantesOfOtherRobot.y - 1;
                }
                // check if this pushes any other robot
                // check if other robots position has an obstacle and do what the obstacle does
                // check for explosive obastacle, all types of damage and so on...
            } else {
                // check if new position is on gameboard or did it drive overboard
                // check for obstacle of the robot thats turn it is
            }
        }

    }
}
