package App.RoborallyApplication.Model.Enums;

public enum ObstacleEnum {
    ACID(-1),
    RADIATION(-1),

    HEALING(2),

    PIT(-2);
    private int damage;

    public int getDamage(){
        return this.damage;
    }
    private ObstacleEnum(int damage) {
        this.damage = damage;
    }
}
