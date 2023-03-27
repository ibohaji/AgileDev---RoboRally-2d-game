package App.RoborallyApplication.Model.Cards.ProgrammingCards;

import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameRunning.GameBrain;

public class AgainCard extends ProgrammingCard{
    @Override
    public void useCard(Robot robot, GameBrain gameBrain) {
        ProgrammingCard lastCardUsed = gameBrain.getLastCardUsedByRobot(robot);
        // write logic for when first card is the again card then nothing happens
        // basically return null for last card used
        if(lastCardUsed != null){
            robot.useProgrammingCard(lastCardUsed, gameBrain);
        }
    }
}


   /* Repeat the programming in your previous register.
        If your previous register was a damage card, draw a card from the top of your deck,
        and play that card this register.
     //
     If you used an upgrade in your previous register that allowed you to play multiple programming cards,
     re-execute the second card. This card cannot be played in the first register.

    */
