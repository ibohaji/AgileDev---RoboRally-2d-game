package App.Domain;

import App.DTO.TileDTO;
import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.GraphicalElementEnum;
import App.Domain.Enums.TileTypeEnum;
import App.Domain.GraphicalElements.TileGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

public class Tile implements serializable {

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
}
