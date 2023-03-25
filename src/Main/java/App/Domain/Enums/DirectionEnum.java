package App.Domain.Enums;


public enum DirectionEnum {
    NORTH("WEST", "EAST", "SOUTH"),
    SOUTH("EAST", "WEST", "NORTH"),
    WEST("SOUTH", "NORTH", "EAST"),
    EAST("NORTH", "SOUTH", "WEST");

    private final String leftDirection;
    private final String rightDirection;
    private final String uTurnDirection;

    DirectionEnum(String leftDirection, String rightDirection, String uTurnDirection) {
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
        this.uTurnDirection = uTurnDirection;
    }

    /**
     *
     * @return Return the next direction to the left
     */
    public DirectionEnum getLeft(){
        return DirectionEnum.valueOf(leftDirection);
    }

    /**
     * @return Return the next direction to the left
     */
    public DirectionEnum getRight(){
        return DirectionEnum.valueOf(rightDirection);
    }

    public DirectionEnum getUTurn(){
        return DirectionEnum.valueOf(uTurnDirection);
    }

}
