package App.RoborallyApplication.Model.Cards;

import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;

public interface CardFunctionality {
    void useCard(Robot robot, GameBrain gameBrain);
}
