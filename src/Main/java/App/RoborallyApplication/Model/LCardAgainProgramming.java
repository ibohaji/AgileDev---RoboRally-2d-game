package App.RoborallyApplication.Model;

import javax.swing.*;

public class LCardAgainProgramming extends AbCardProgramming {
    private GraphicalElement graphicalElement = new GraphicalElement();

    public LCardAgainProgramming(){
        this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.AGAIN_CARD);
    }

    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        LPlayer playerOfRobot = robot.getPlayer();
        AbCardProgramming lastCardUsed = playerOfRobot.getLastCard();
        if(lastCardUsed != null){
            //TODO
            // fix here
            // 1)take last movementCard
            // 2)set to first card of unused sequence
            // 3)add again card to used sequence as a new programming card?
            // 3)reuse the same card but set steps = 0?
            // create new instance of the card
            AbCardProgramming cardToUse = null;
            if (lastCardUsed instanceof LCardChangeDirectionProgramming){
                cardToUse = new LCardChangeDirectionProgramming(((LCardChangeDirectionProgramming) lastCardUsed).getTurnType());
            } else if (lastCardUsed instanceof  LCardMovementProgramming) {
                cardToUse = new LCardMovementProgramming(((LCardMovementProgramming) lastCardUsed).getSteps());
            }
            playerOfRobot.addCardFromAgain(cardToUse);
            //lastCardUsed.useCard(robot, gameBrain);
        }
    }
    @Override
    public ImageIcon getCardImageIcon() {
        return this.graphicalElement.getImage();
    }

    @Override
    public String toString() {
        return "Again card";
    }
}


