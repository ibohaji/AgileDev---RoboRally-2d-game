package App.RoborallyApplication.Model;

public class LCardChangeDirectionProgramming extends AbCardProgramming {
    private final EnumTurnType turn;
    public LCardChangeDirectionProgramming(EnumTurnType enumTurnType){
        turn = enumTurnType;
    }

    @Override
    public String getCardImageFileName() {
        String imageName = null;
        switch (turn) {
            case LEFT:
                imageName = "App.Resources.Cards.LeftTurnCard.png";
                break;
            case RIGHT:
                imageName = "App.Resources.Cards.RightTurnCard.png";
                break;
            case U_TURN:
                imageName = "App.Resources.Cards.UTurnCard.png";
                break;
        }
        if (imageName == null) {
            throw new IllegalStateException("Invalid turn value: " + turn);
        }
        return imageName;
    }
    @Override
    public void useCard(LRobot robot, LGameBrain gameBrain) {
        robot.changeDirection(turn);
    }
}
