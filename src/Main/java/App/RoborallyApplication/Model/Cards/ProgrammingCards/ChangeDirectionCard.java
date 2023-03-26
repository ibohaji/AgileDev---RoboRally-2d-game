package App.RoborallyApplication.Model.Cards.ProgrammingCards;

import App.RoborallyApplication.Model.Enums.TurnEnum;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;

public class ChangeDirectionCard extends ProgrammingCard{

    private final TurnEnum turn;
    public ChangeDirectionCard(TurnEnum turnEnum){
        turn = turnEnum;
    }
    @Override
    public void useCard(Robot robot, GameBrain gameBrain) {

    }
}
