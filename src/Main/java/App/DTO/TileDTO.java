package App.DTO;

import App.RoborallyApplication.Model.EnumTileType;
import App.RoborallyApplication.Model.LTile;
import App.RoborallyApplication.Model.GraphicalElementTile;

import java.awt.*;

public class TileDTO implements iFromDTO{
    public Integer xCoordinate;
    public Integer yCoordinate;
    public GraphicalElementTile graphicalElement;
    public EnumTileType enumTileType;
    public TileDTO(){}
    public TileDTO(LTile tile){
        Point coordinates = tile.getCoordinates();
        this.xCoordinate = coordinates.x;
        this.yCoordinate = coordinates.y;
        this.enumTileType = tile.getTileTypeEnum();
        this.graphicalElement = tile.getGraphicalElement();
    }

    @Override
    public LTile returnObjectFromDTO() {
        LTile tile = new LTile(this.xCoordinate, this.yCoordinate, this.enumTileType);
        return tile;
    }
}
