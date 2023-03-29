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

import javax.swing.*;
import java.util.UUID;

public class Tile implements iToDTO {

    private Integer xCoordinate;
    private Integer yCoordinate;
    private TileGraphicalElement graphicalElement;
    protected TileTypeEnum tileTypeEnum;
    private Obstacle obstacle;

    public Tile(int xCoordinate, int yCoordinate, TileTypeEnum tileTypeEnum){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new TileGraphicalElement();
        this.tileTypeEnum = tileTypeEnum;
    }

    protected void changeTile(GraphicalElementEnum graphicalElementEnum, TileTypeEnum tileTypeEnum, DifficultyEnum difficultyEnum){
        this.tileTypeEnum = tileTypeEnum;
        this.getGraphicalElement().setGraphicalElement(graphicalElementEnum, difficultyEnum);
    }

    public Tuple<Integer, Integer> getCoordinates(){
        return new Tuple<>(this.xCoordinate, this.yCoordinate);
    }

    public TileTypeEnum getTileTypeEnum() {
        return tileTypeEnum;
    }

    public TileGraphicalElement getGraphicalElement() {
        return graphicalElement;
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

    protected Obstacle getObstacle() {
        return obstacle;
    }

    protected void addObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }


}
