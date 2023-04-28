package App.DTO;

import App.RoborallyApplication.Model.*;

import java.awt.*;
import java.util.UUID;

public class TileDTO implements iFromDTO{
    private UUID id;
    private Point coordinate;
    private int xCoordinate;
    private int yCoordinate;
    private GraphicalElementTile graphicalElement;
    private EnumTileType enumTileType;

    public TileDTO(LTile tile){
        this.id = UUID.randomUUID();
        this.coordinate = tile.getCoordinates();
        this.xCoordinate = coordinate.x;
        this.yCoordinate = coordinate.y;
        this.enumTileType = tile.getTileTypeEnum();
        this.graphicalElement = tile.getGraphicalElement();
    }

    @Override
    public LTile returnObjectFromDTO() {
        LTile return_tile = new LTile(this.xCoordinate, this.yCoordinate, this.enumTileType);
        //TODO
        // return_tile.setGraphicalElement(this.graphicalElement);
        return return_tile;
    }
}
