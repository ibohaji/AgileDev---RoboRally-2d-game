package App.DTO;

import App.Domain.Gameboard;
import App.Domain.Tile;
import Utils.Tuple;
import io.cucumber.java.bs.I;

public class TileDTO{
    public Integer xCoordinate;
    public Integer yCoordinate;
    public int graphicalElementOrdinal;

    public TileDTO(){

    }

    public TileDTO(Tile tile){
        Tuple<Integer, Integer> coordinates = tile.getCoordinates();
        this.xCoordinate = coordinates.first();
        this.yCoordinate = coordinates.second();
        this.graphicalElementOrdinal = tile.graphicalElement.getGraphicalElementOrdinal();
    }
}