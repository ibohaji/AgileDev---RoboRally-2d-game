package App.Views.GraphicalElements;

import App.Domain.Enums.GraphicalElementEnum;
import Utils.ImageUtils;

import javax.swing.*;

public class GraphicalElement {

    protected GraphicalElementEnum graphicalElement = null;
    protected ImageIcon image = null;
    public GraphicalElement(){

    }
    public void changeGraphicalElement(GraphicalElementEnum graphicalElement){
        this.graphicalElement = graphicalElement;
        this.image = new ImageIcon(ImageUtils.getImage(graphicalElement.getPictureFile()));
    }

    public String getGraphicalElementPath(){
        return this.graphicalElement.getPictureFile();
    }

    public int getGraphicalElementOrdinal(){
        return this.graphicalElement.getElementOrdinal();
    }

    public String getElementName(){
        return this.graphicalElement.name();
    }

    protected boolean doesGraphicalElementExist(){
        return image == null;
    }

    protected ImageIcon getGraphicalElement(){
        if (doesGraphicalElementExist()){
            return this.image;
        }
        // Throw custom error "graphical element not added"
        return null;
    }
}