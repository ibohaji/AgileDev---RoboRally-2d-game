package App.Model.GameObjects;

import App.DTO.TileDTO;
import App.Model.IReloadable;
import App.Model.GameRunning.DifficultyEnum;
import App.Model.Enums.GraphicalElementEnum;
import App.Model.Enums.TileTypeEnum;
import App.Model.GraphicalElements.TileGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

import java.util.UUID;

public class Tile implements IReloadable {

    private Integer xCoordinate;
    private Integer yCoordinate;
    public TileGraphicalElement graphicalElement;
    protected TileTypeEnum tileTypeEnum;

    public Tile(int xCoordinate, int yCoordinate, TileTypeEnum tileTypeEnum){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new TileGraphicalElement();
        this.tileTypeEnum = tileTypeEnum;
    }

    public Tile(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new TileGraphicalElement();
        this.tileTypeEnum = tileTypeEnum;
    }



    protected void changeTile(GraphicalElementEnum graphicalElementEnum, TileTypeEnum tileTypeEnum, DifficultyEnum difficultyEnum){
        this.tileTypeEnum = tileTypeEnum;
        this.graphicalElement.changeGraphicalElement(graphicalElementEnum, difficultyEnum);
    }

    public Tuple<Integer, Integer> getCoordinates(){
        return new Tuple<>(this.xCoordinate, this.yCoordinate);
    }

    @Override
    public String toString() {
        return "Tile object. X coordinate: " + this.xCoordinate + " Y coordinate: " + this.yCoordinate +
                ". Graphical element on tile: " + this.graphicalElement.getElementName();
    }

    @Override
    public String toJson() {
        TileDTO tileDTO = new TileDTO(this);
        return JsonHelper.serializeObjectToJson(tileDTO);
    }

    @Override
    public UUID getID() {
        return null;
    }
}