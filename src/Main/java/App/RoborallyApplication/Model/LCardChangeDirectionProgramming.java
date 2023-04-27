package App.RoborallyApplication.Model;

import javax.swing.*;

public class LCardChangeDirectionProgramming extends AbCardProgramming {
    private final EnumTurnType turn;

    private GraphicalElement graphicalElement = new GraphicalElement();
    public LCardChangeDirectionProgramming(EnumTurnType enumTurnType){
        turn = enumTurnType;
        switch (turn) {
            case LEFT:
                this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.LEFT_TURN_CARD);
                break;
            case RIGHT:
                this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.RIGHT_TURN_CARD);
                break;
            case U_TURN:
                this.graphicalElement.setCardGraphicalElement(EnumImageGraphics.U_TURN_CARD);
                break;
        }

    }
    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        robot.changeDirection(turn);
    }

    @Override
    public ImageIcon getCardImageIcon() {
        return this.graphicalElement.getImage();
    }

    @Override
    public String toString() {
        return "Turn card. Turn:" + this.turn.toString();
    }
}
