package App.Domain;

import App.DTO.TileDTO;
import App.Views.GraphicalElements.TileGraphicalElement;
import Utils.JsonHelper;
import Utils.Tuple;

public class Tile implements serializable {

    private Integer xCoordinate;
    private Integer yCoordinate;
    public TileGraphicalElement graphicalElement;
    public Tile(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new TileGraphicalElement();
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
