package App.Domain;

import App.Domain.Enums.GraphicalElementEnum;
import App.Views.GraphicalElements.GraphicalElement;
import App.Views.GraphicalElements.TileGraphicalElement;
import Utils.ImageUtils;

import javax.swing.*;

public class Tile {

    private Integer xCoordinate;
    private Integer yCoordinate;
    private TileGraphicalElement graphicalElement;
    public Tile(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.graphicalElement = new TileGraphicalElement();
    }


}
