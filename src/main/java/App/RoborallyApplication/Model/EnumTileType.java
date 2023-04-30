package App.RoborallyApplication.Model;

public enum EnumTileType {
    START,
    FINISH,
    CHECKPOINT,
    OBSTACLE,
    DEFAULT_FLOOR;

    EnumTileType(){}

    public boolean equals(EnumTileType typeEnum){
        return this.ordinal() == typeEnum.ordinal();
    }

}
