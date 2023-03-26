package App.Model.Cards.ProgrammingCards;

import App.Model.GameObjects.Robot;
import App.Model.GameRunning.GameBrain;

public class MovementCard extends ProgrammingCard{
    private final int steps;

    public MovementCard(int steps){
        this.steps = steps;
    }

    @Override
    public void useCard(Robot robot, GameBrain gameBrain) {

    }
}
