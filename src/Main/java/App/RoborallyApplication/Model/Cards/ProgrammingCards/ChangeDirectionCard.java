package App.RoborallyApplication.Model.Cards.ProgrammingCards;

import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;

import java.awt.*;

public class ChangeDirectionCard extends ProgrammingCard{
    private final TurnEnum turn;
    public ChangeDirectionCard(TurnEnum turnEnum){
        turn = turnEnum;
    }

    @Override
    public String getCardImageFileName() {
        String imageName = null;
        switch (turn) {
            case LEFT:
                imageName = "App.Resources.Cards.LeftTurnCard.png";
                break;
            case RIGHT:
                imageName = "App.Resources.Cards.RightTurnCard.png";
                break;
            case U_TURN:
                imageName = "App.Resources.Cards.UTurnCard.png";
                break;
        }
        if (imageName == null) {
            throw new IllegalStateException("Invalid turn value: " + turn);
        }
        return imageName;
    }
    @Override
    public void useCard(Robot robot, GameBrain gameBrain) {
        robot.changeDirection(turn);
    }
}
