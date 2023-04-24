package App.RoborallyApplication.Model.ObstaclesFolder;

import App.DTO.ObstacleDTO;
import App.RoborallyApplication.Model.IToDTO;
import App.RoborallyApplication.Model.LRobot;
import Utils.JsonHelper;

import java.util.UUID;

public abstract class Obstacles implements IToDTO {
    private int damage;
    private String graphicalElement;
    UUID id;


    Obstacles(int damage){
        this.damage = damage;
    }

    abstract int getDamage();

    public abstract void applyEffect(LRobot robot);
    public abstract String getGraphicalElement();

    public abstract boolean isKnown();
    public abstract boolean isExplosive();
    @Override
    public String toString() {
        return "Obstacle object. Graphical element on obstacle: " ;
        //+ this.graphicalElement.getElementName(); // TODO -> I'am not sure how to implement DTO
    }

    @Override
    public String DTOasJson() {
        ObstacleDTO obstacledto = new ObstacleDTO(this);
        return JsonHelper.serializeObjectToJson(obstacledto);
    }

    @Override
    public UUID getID() {
        return this.id;
    }

}
