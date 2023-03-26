package App.RoborallyApplication.Model.GraphicalElements;

import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import Utils.ImageUtils;

import javax.swing.*;

public class GraphicalElement {

    protected GraphicalElementEnum graphicalElementEnum;
    protected ImageIcon image = null;
    public GraphicalElement(){

    }
    public void changeGraphicalElement(GraphicalElementEnum graphicalElement, DifficultyEnum difficultyEnumForScaling){
        this.graphicalElementEnum = graphicalElement;
        switch (difficultyEnumForScaling){
            case EASY -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.graphicalElementEnum.getElementLocation())), 60, 60);
            case MEDIUM -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.graphicalElementEnum.getElementLocation())), 45, 45);
            case HARD -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.graphicalElementEnum.getElementLocation())), 35, 35);
        }
    }

    public int getGraphicalElementOrdinal(){
        return this.graphicalElementEnum.getElementOrdinal();
    }

    public String getElementName(){
        return this.graphicalElementEnum.name();
    }

    protected boolean doesGraphicalElementExist(){
        return image == null;
    }

    public ImageIcon getImage(){
        return this.image;
    }

    protected ImageIcon getGraphicalElementEnum(){
        if (doesGraphicalElementExist()){
            return this.image;
        }
        // TODO
        // Throw custom error "graphical element not added"
        return null;
    }
}
