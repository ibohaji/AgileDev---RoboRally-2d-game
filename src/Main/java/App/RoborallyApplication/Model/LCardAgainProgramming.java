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
            lastCardUsed.useCard(robot, gameBrain);;
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


