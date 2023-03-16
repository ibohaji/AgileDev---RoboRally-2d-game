package App.Domain;

import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class SpaceOnBoard {
    
    private GraphicalElementEnum graphicalElement;
    private ImageIcon image = null;

    private Integer xCoordinate;
    private Integer yCoordinate;
    public SpaceOnBoard(int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    protected void putGraphicElementOnSpace(GraphicalElementEnum graphicalElement){
        this.graphicalElement = graphicalElement;
        this.image = new ImageIcon(ImageUtils.getImage(graphicalElement.getPictureFile()));
    }
}
