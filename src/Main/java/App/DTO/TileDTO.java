package App.DTO;

import App.RoborallyApplication.Model.Enums.TileTypeEnum;
import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.GraphicalElements.GraphicalElement;
import App.RoborallyApplication.Model.GraphicalElements.TileGraphicalElement;
import Utils.Tuple;

public class TileDTO implements iFromDTO{
    public Integer xCoordinate;
    public Integer yCoordinate;
    public TileGraphicalElement graphicalElement;
    public TileTypeEnum tileTypeEnum;

    public TileDTO(){

    }

    public TileDTO(Tile tile){
        Tuple<Integer, Integer> coordinates = tile.getCoordinates();
        this.xCoordinate = coordinates.first();
        this.yCoordinate = coordinates.second();
        this.tileTypeEnum = tile.getTileTypeEnum();
        this.graphicalElement = tile.getGraphicalElement();
    }

    @Override
    public Tile returnObjectFromDTO() {
        Tile tile = new Tile(this.xCoordinate, this.yCoordinate, this.tileTypeEnum);
        return tile;
    }
}
