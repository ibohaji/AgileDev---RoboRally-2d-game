package App.RoborallyApplication.Model.GameObjects;

import App.DTO.TileDTO;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.iToDTO;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.Enums.TileTypeEnum;
import App.RoborallyApplication.Model.GraphicalElements.TileGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

import java.awt.*;
import java.util.UUID;

public class Tile implements iToDTO {
    private UUID id;
    private Integer xCoordinate;
    private Integer yCoordinate;
    private TileGraphicalElement graphicalElement;
    protected TileTypeEnum tileTypeEnum;
    private Obstacle obstacle;

    public Tile(int xCoordinate, int yCoordinate, TileTypeEnum tileTypeEnum){
        this.id = UUID.randomUUID();
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new TileGraphicalElement();
        this.tileTypeEnum = tileTypeEnum;
        this.id = UUID.randomUUID();
    }

    public Point getCoordinates(){
        return new Point(this.xCoordinate, this.yCoordinate);
    }

    public TileTypeEnum getTileTypeEnum() {
        return tileTypeEnum;
    }

    public TileGraphicalElement getGraphicalElement() {
        return graphicalElement;
    }

    public void setGraphicalElement(GraphicalElementEnum graphicalElementEnum, DifficultyEnum difficulty){
        this.graphicalElement.setGraphicalElement(graphicalElementEnum, difficulty);
    }

    public boolean doesTileHaveObstacle(){
        return this.obstacle == null;
    }

    public boolean doesTileHaveCheckpoint(){
        return this.tileTypeEnum.equals(TileTypeEnum.CHECKPOINT);
    }

    public boolean isTileFinishPoint(){
        return this.tileTypeEnum.equals(TileTypeEnum.FINISH);
    }

    public boolean isTileStartPoint(){
        return this.tileTypeEnum.equals(TileTypeEnum.START);
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

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }


}
