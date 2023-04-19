package App.RoborallyApplication.Model;

public enum EnumTileType {
    START,
    FINISH,
    CHECKPOINT,
    OBSTACLE,
    DEFAULT_FLOOR;

    private EnumTileType(){}

    public boolean equals(EnumTileType typeEnum){
        if(this.ordinal() == typeEnum.ordinal()){
            return true;
        } else {
            return false;
        }
    }

}
