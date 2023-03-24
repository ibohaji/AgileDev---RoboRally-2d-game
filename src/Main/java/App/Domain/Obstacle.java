package App.Domain;

public class Obstacle implements serializable {

    private Point cords = new Point(0,0);

    private TileGraphicalElement graphicalElement;
    private int lifeCount = 5;

    public Obstacle() {
        this.graphicalElement = new TileGraphicalElement();
    }

    public Point getCords(){
        return cords;
    }

    public void setCords(Point newCords){
        cords.x += newCords.x;
        cords.y += newCords.y;
    }

    @Override
    public String toJson() {
        return "";
    }
}
