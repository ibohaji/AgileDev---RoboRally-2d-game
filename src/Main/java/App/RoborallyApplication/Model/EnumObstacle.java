package App.RoborallyApplication.Model;

public enum EnumObstacle {
    ACID(-1),
    RADIATION(-1),

    HEALING(2),

    PIT(-2);
    private int damage;

    public int getDamage(){
        return this.damage;
    }
    private EnumObstacle(int damage) {
        this.damage = damage;
    }
}
