package App.RoborallyApplication.Model;

import Utils.JsonHelper;

import java.awt.*;
import java.util.UUID;

public class LTile{
    private Integer xCoordinate;
    private Integer yCoordinate;
    private GraphicalElementTile graphicalElement;
    protected EnumTileType enumTileType;
    private AbObstacle newObstacle;

    public LTile(int xCoordinate, int yCoordinate, EnumTileType enumTileType){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new GraphicalElementTile();
        this.enumTileType = enumTileType;
    }

    public Point getCoordinates(){
        return new Point(this.xCoordinate, this.yCoordinate);
    }

    public EnumTileType getTileTypeEnum() {
        return enumTileType;
    }

    public GraphicalElementTile getGraphicalElement() {
        return graphicalElement;
    }

    public void setGraphicalElement(EnumImageGraphics enumImageGraphics, EnumDifficulty difficulty){
        this.graphicalElement.setTileGraphicalElement(enumImageGraphics, difficulty);
    }

    public boolean doesTileHaveObstacle(){
        return this.newObstacle != null;
    }
    public boolean doesTileHaveCheckpoint(){
        return this.enumTileType.equals(EnumTileType.CHECKPOINT);
    }

    public boolean isTileFinishPoint(){
        return this.enumTileType.equals(EnumTileType.FINISH);
    }

    public boolean isTileStartPoint(){
        return this.enumTileType.equals(EnumTileType.START);
    }
    public void setNewObstacle(AbObstacle obstacle){
        this.newObstacle = obstacle;
    }
    public AbObstacle getNewObstacle(){
        return this.newObstacle;
    }


}
