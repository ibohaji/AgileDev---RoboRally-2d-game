package App.RoborallyApplication.Model;

import Utils.ImageUtils;

import javax.swing.*;
import java.awt.*;

public class GraphicalElement {

    protected EnumImageGraphics enumImageGraphics;
    protected ImageIcon image = null;
    public GraphicalElement(){

    }
    public void setTileGraphicalElement(EnumImageGraphics graphicalElement, EnumDifficulty enumDifficultyForScaling){
        this.enumImageGraphics = graphicalElement;
        switch (enumDifficultyForScaling){
            case EASY -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.enumImageGraphics.getElementLocation())), 60, 60);
            case MEDIUM -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.enumImageGraphics.getElementLocation())), 45, 45);
            case HARD -> this.image = ImageUtils.scaledImageIcon(
                    new ImageIcon(ImageUtils.getImage(this.enumImageGraphics.getElementLocation())), 35, 35);
        }
    }
    public void setCardGraphicalElement(EnumImageGraphics graphicalElement){
        this.enumImageGraphics = graphicalElement;
        this.image = new ImageIcon(ImageUtils.getImage(this.enumImageGraphics.getElementLocation()));
    }
    public String getElementName(){
        return this.enumImageGraphics.name();
    }

    public ImageIcon getImage(){
        return this.image;
    }
}
