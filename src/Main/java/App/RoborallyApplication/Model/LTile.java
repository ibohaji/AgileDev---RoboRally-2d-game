package App.RoborallyApplication.Model;

import App.DTO.TileDTO;
import Utils.JsonHelper;

import java.awt.*;
import java.util.UUID;

public class LTile implements IToDTO {
    private UUID id;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private GraphicalElementTile graphicalElement;
    protected EnumTileType enumTileType;
    private LObstacle obstacle;

    public LTile(int xCoordinate, int yCoordinate, EnumTileType enumTileType){
        this.id = UUID.randomUUID();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new GraphicalElementTile();
        this.enumTileType = enumTileType;
        this.id = UUID.randomUUID();
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

    public void setGraphicalElement(EnumGraphicalElementMain enumGraphicalElementMain, EnumDifficulty difficulty){
        this.graphicalElement.setTileGraphicalElement(enumGraphicalElementMain, difficulty);
    }

    public boolean doesTileHaveObstacle(){
        return this.obstacle == null;
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

    @Override
    public String toString() {
        return "Tile object. X coordinate: " + this.xCoordinate + " Y coordinate: " + this.yCoordinate +
                ". Graphical element on tile: " + this.graphicalElement.getElementName();
    }

    @Override
    public String DTOasJson() {
        TileDTO tileDTO = new TileDTO(this);
        return JsonHelper.serializeObjectToJson(tileDTO);
    }

    @Override
    public UUID getID() {
        return null;
    }

    public LObstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(LObstacle obstacle) {
        this.obstacle = obstacle;
    }


}
