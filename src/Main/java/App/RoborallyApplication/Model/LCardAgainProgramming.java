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
            AbCardProgramming cardToUse = null;
            if (lastCardUsed instanceof LCardChangeDirectionProgramming){
                cardToUse = new LCardChangeDirectionProgramming(((LCardChangeDirectionProgramming) lastCardUsed).getTurnType());
            } else if (lastCardUsed instanceof  LCardMovementProgramming) {
                cardToUse = new LCardMovementProgramming(((LCardMovementProgramming) lastCardUsed).getSteps());
            }
            playerOfRobot.addCardFromAgain(cardToUse);
        }
    }
    @Override
    public ImageIcon getCardImageIcon() {
        return this.graphicalElement.getImage();
    }
}


