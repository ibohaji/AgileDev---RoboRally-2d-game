package App.RoborallyApplication.Model;

import Utils.ImageUtils;

import javax.swing.*;

public class LCardAgainProgramming extends AbCardProgramming {
    private GraphicalElement graphicalElement = new GraphicalElement();

    public LCardAgainProgramming(){
        this.graphicalElement.setCardGraphicalElement(EnumGraphicalElementMain.AGAIN_CARD);
    }

    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        LPlayer playerOfRobot = robot.getPlayer();
        System.out.println(playerOfRobot.getDisplayName());
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
