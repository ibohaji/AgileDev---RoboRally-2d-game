package App.Domain;


public enum DirectionEnum {
    NORTH("WEST", "EAST"),
    SOUTH("EAST", "WEST"),
    WEST("SOUTH", "NORTH"),
    EAST("NORTH", "SOUTH");

    private final String leftDirection;
    private final String rightDirection;

    DirectionEnum(String leftDirection, String rightDirection) {
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
    }

    public DirectionEnum getLeft(){
        return DirectionEnum.valueOf(leftDirection);
    }

    public DirectionEnum getRight(){
        return DirectionEnum.valueOf(rightDirection);
    }

}
