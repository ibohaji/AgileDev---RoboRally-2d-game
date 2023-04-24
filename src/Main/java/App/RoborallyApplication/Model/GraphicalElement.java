package App.RoborallyApplication.Model;

import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GraphicalElement {

    protected EnumGraphicalElementMain enumGraphicalElementMain;
    protected ImageIcon image = null;
    public GraphicalElement(){

    }
    public void setTileGraphicalElement(String path, EnumDifficulty enumDifficultyForScaling){
        switch (enumDifficultyForScaling){
            case EASY -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(path)), 60, 60);
            case MEDIUM -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(path)), 45, 45);
            case HARD -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(path)), 35, 35);
        }
    }

    public void setCardGraphicalElement(EnumGraphicalElementMain graphicalElement){
        this.enumGraphicalElementMain = graphicalElement;
        System.out.println(this.enumGraphicalElementMain.getElementLocation());
        System.out.println((ImageUtils.getImage(this.enumGraphicalElementMain.getElementLocation())==null));
        this.image = new ImageIcon(ImageUtils.getImage(this.enumGraphicalElementMain.getElementLocation()));
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
