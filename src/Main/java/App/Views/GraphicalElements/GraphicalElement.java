package App.Views.GraphicalElements;

import App.Domain.Enums.GraphicalElementEnum;
import Utils.ImageUtils;

import javax.swing.*;

public class GraphicalElement {

    private GraphicalElementEnum graphicalElement;
    private ImageIcon image = null;
    public GraphicalElement(){

    }
    protected void changeGraphicalElement(GraphicalElementEnum graphicalElement){
        this.graphicalElement = graphicalElement;
        this.image = new ImageIcon(ImageUtils.getImage(graphicalElement.getPictureFile()));
    }
}
