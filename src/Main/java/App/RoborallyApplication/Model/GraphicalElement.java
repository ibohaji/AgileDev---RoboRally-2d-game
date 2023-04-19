package App.RoborallyApplication.Model;

import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GraphicalElement {

    protected EnumGraphicalElementMain enumGraphicalElementMain;
    protected ImageIcon image = null;
    public GraphicalElement(){

    }
    public void setGraphicalElement(EnumGraphicalElementMain graphicalElement, EnumDifficulty enumDifficultyForScaling){
        this.enumGraphicalElementMain = graphicalElement;
        switch (enumDifficultyForScaling){
            case EASY -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.enumGraphicalElementMain.getElementLocation())), 60, 60);
            case MEDIUM -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.enumGraphicalElementMain.getElementLocation())), 45, 45);
            case HARD -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.enumGraphicalElementMain.getElementLocation())), 35, 35);
        }
    }

    public int getGraphicalElementOrdinal(){
        return this.enumGraphicalElementMain.getElementOrdinal();
    }
    public String getElementName(){
        return this.enumGraphicalElementMain.name();
    }

    public ImageIcon getImage(){
        return this.image;
    }

    private void setImage(LTile tile, LGameBrain gameBrain){
        Point location = new Point(tile.getCoordinates().x, tile.getCoordinates().y);
        //tile.graphicalElement
    }
}
