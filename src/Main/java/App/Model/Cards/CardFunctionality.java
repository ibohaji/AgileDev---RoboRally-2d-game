package App.Model.Cards;

import App.Model.GameObjects.Robot;
import App.Model.GameRunning.GameBrain;

public interface CardFunctionality {
    void useCard(Robot robot, GameBrain gameBrain);
}
