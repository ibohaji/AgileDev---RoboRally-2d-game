package App.RoborallyApplication.Model.Enums;

public enum TileTypeEnum {
    START,
    FINISH,
    CHECKPOINT,
    OBSTACLE,
    DEFAULT_FLOOR;

    private TileTypeEnum(){}

    public boolean equals(TileTypeEnum typeEnum){
        if(this.ordinal() == typeEnum.ordinal()){
            return true;
        } else {
            return false;
        }
    }

}
