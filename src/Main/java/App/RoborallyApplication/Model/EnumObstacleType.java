package App.RoborallyApplication.Model;

public enum EnumObstacleType {
    ACID(-1),
    RADIATION(-2),
    HEALING(2),
    PIT(-1);
    private int damage;

    public int getDamage(){
        return this.damage;
    }
    private EnumObstacleType(int damage) {
        this.damage = damage;
    }
}
