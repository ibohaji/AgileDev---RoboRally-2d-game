package App.RoborallyApplication.Model;


public enum EnumDirection {
    NORTH("WEST", "EAST", "SOUTH"),
    SOUTH("EAST", "WEST", "NORTH"),
    WEST("SOUTH", "NORTH", "EAST"),
    EAST("NORTH", "SOUTH", "WEST");

    private final String leftDirection;
    private final String rightDirection;
    private final String uTurnDirection;

    EnumDirection(String leftDirection, String rightDirection, String uTurnDirection) {
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
        this.uTurnDirection = uTurnDirection;
    }

    /**
     *
     * @return Return the next direction to the left
     */
    public EnumDirection getLeft(){
        return EnumDirection.valueOf(leftDirection);
    }

    /**
     * @return Return the next direction to the left
     */
    public EnumDirection getRight(){
        return EnumDirection.valueOf(rightDirection);
    }

    public EnumDirection getUTurn(){
        return EnumDirection.valueOf(uTurnDirection);
    }

}
