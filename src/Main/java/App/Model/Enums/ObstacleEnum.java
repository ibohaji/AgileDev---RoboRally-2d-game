package App.Model.Enums;

public enum ObstacleEnum {
    ACID(-1),
    RADIATION(-2),

    HEALING(2),

    PIT(1);
    private int damage;
    private ObstacleEnum(int damage) {
        this.damage = damage;
    }
}
