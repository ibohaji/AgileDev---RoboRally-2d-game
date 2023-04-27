package App.RoborallyApplication.Model;

import java.util.ArrayList;

public class LObstacleRegular extends AbObstacle{
    private EnumObstacleClassification obstacleClassification;
    private EnumObstacleType obstacleType;
    private GraphicalElement graphicalElement;
    public LObstacleRegular(EnumObstacleType obstacleType, EnumObstacleClassification obstacleClassification){
        super();
        this.obstacleType = obstacleType;
        this.obstacleClassification = obstacleClassification;
        this.graphicalElement = new GraphicalElement();
    }

    public LObstacleRegular(EnumObstacleClassification obstacleClassification){
        super();
        this.obstacleType = null;
        this.obstacleClassification = obstacleClassification;
        this.graphicalElement = new GraphicalElement();
    }
    @Override
    public void applyEffect(LRobot robot, LGameBrain gameBrain) {
        if(this.obstacleClassification.equals(EnumObstacleClassification.KNOWN_OBSTACLE)){
            robot.setNrOfLives(robot.getNrOfLives() + this.obstacleType.getDamage());
            if(this.obstacleType.equals(EnumObstacleType.PIT)){
                gameBrain.putRobotToRandomStartPoint(robot);
            }
            if(robot.getNrOfLives() <= 0){
                gameBrain.removePlayer(robot.getPlayer());
                gameBrain.removeRobot(robot);
            }
        } else {
            ArrayList<LTile> affectedTiles = gameBrain.getGameboard().getTilesSurroundingCoordinate(robot.getCords().x, robot.getCords().y);
            if (this.obstacleClassification.equals(EnumObstacleClassification.EXPLOSIVE_KNOWN)){
                // Explosive known
            } else if (this.obstacleClassification.equals(EnumObstacleClassification.EXPLOSIVE_UNKNOWN)){
                EnumObstacleType type = gameBrain.getRandomObstacleType();
                this.setObstacleType(type);
            }
            gameBrain.explodeObstacleToTilesNEW(affectedTiles, obstacleType);
            setObstacleClassification(EnumObstacleClassification.KNOWN_OBSTACLE);
            applyEffect(robot, gameBrain);
        }
    }

    public EnumObstacleType getObstacleType() {return this.obstacleType;}

    protected void setObstacleType(EnumObstacleType type){this.obstacleType = type;}
    public EnumObstacleClassification getObstacleClassification() {
        return obstacleClassification;
    }
    protected void setObstacleClassification(EnumObstacleClassification classification){
        this.obstacleClassification = classification;
    }
}
