package App.RoborallyApplication.Model.GraphicalElements;

import App.RoborallyApplication.Model.GameObjects.Tile;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.Enums.GraphicalElementEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

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

    public ImageIcon getImage(){
        return this.image;
    }

    private void setImage(Tile tile, GameBrain gameBrain){
        Point location = new Point(tile.getCoordinates().first(), tile.getCoordinates().second());
        //tile.graphicalElement
    }
}
