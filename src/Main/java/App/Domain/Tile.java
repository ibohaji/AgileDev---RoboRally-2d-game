package App.Domain;

import App.Domain.Enums.GraphicalElementEnum;
import Utils.ImageUtils;

import javax.swing.*;

public class Tile {
    
    private GraphicalElementEnum graphicalElement;
    private ImageIcon image = null;

    private Integer xCoordinate;
    private Integer yCoordinate;
    public Tile(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    protected void putGraphicElementOnSpace(GraphicalElementEnum graphicalElement){
        this.graphicalElement = graphicalElement;
        this.image = new ImageIcon(ImageUtils.getImage(graphicalElement.getPictureFile()));
    }
}
