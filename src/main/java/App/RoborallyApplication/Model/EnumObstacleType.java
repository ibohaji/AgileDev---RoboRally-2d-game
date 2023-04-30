package App.RoborallyApplication.Model;

public enum EnumObstacleType {
    ACID(-1),
    RADIATION(-2),
    HEALING(2),
    PIT(-1);
    private final int damage;

    public int getDamage() {return this.damage;
    }
    EnumObstacleType(int damage) {
        this.damage = damage;
    }
}