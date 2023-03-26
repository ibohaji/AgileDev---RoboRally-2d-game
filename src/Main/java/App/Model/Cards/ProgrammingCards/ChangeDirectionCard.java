package App.Model.Cards.ProgrammingCards;

import App.Model.Enums.TurnEnum;
import App.Model.GameObjects.Robot;
import App.Model.GameRunning.GameBrain;

public class ChangeDirectionCard extends ProgrammingCard{

    private final TurnEnum turn;
    public ChangeDirectionCard(TurnEnum turnEnum){
        turn = turnEnum;
    }
    @Override
    public void useCard(Robot robot, GameBrain gameBrain) {

    }
}
