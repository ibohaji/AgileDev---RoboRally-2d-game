package App.RoborallyApplication.Model;

import javax.swing.*;

public class LCardChangeDirectionProgramming extends AbCardProgramming {
    private final EnumTurnType turn;

    private final GraphicalElement graphicalElement = new GraphicalElement();
    public LCardChangeDirectionProgramming(EnumTurnType enumTurnType){
        turn = enumTurnType;
        switch (turn) {
            case LEFT -> this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.LEFT_TURN_CARD);
            case RIGHT -> this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.RIGHT_TURN_CARD);
            case U_TURN -> this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.U_TURN_CARD);
        }
    }

    public EnumTurnType getTurnType(){
        return this.turn;
    }
    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        robot.changeDirection(turn);
    }

    @Override
    public ImageIcon getCardImageIcon() {
        return this.graphicalElement.getImage();
    }
}
