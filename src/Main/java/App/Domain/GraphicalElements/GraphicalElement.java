package App.Domain.GraphicalElements;

import App.Domain.Enums.DifficultyEnum;
import App.Domain.Enums.GraphicalElementEnum;
import Utils.ImageUtils;
import Utils.NoScalingIcon;
import Utils.Tuple;

import javax.swing.*;

public class GraphicalElement {

    protected GraphicalElementEnum graphicalElement = null;
    protected ImageIcon image = null;
    public GraphicalElement(){

    }
    public void changeGraphicalElement(GraphicalElementEnum graphicalElement, DifficultyEnum difficultyEnumForScaling){
        this.graphicalElement = graphicalElement;
        switch (difficultyEnumForScaling){
            case EASY -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.graphicalElement.getElementLocation())), 60, 60);
            case MEDIUM -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.graphicalElement.getElementLocation())), 45, 45);
            case HARD -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.graphicalElement.getElementLocation())), 35, 35);
        }
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

    public ImageIcon getImage(){
        return this.image;
    }

    protected ImageIcon getGraphicalElement(){
        if (doesGraphicalElementExist()){
            return this.image;
        }
        // TODO
        // Throw custom error "graphical element not added"
        return null;
    }
}
