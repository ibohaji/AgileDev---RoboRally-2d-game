package App.RoborallyApplication.Model;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
            System.out.println(this.obstacleType.name());
            System.out.println("Lives before: " + robot.getNrOfLives());
            System.out.println(this.obstacleType.getDamage());
            robot.setNrOfLives(robot.getNrOfLives() + this.obstacleType.getDamage());
            System.out.println("Lives after: " + robot.getNrOfLives());
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
                EnumObstacleType type = gameBrain.getRandomObstacleTypeToExplode();
                this.setObstacleType(type);
            }
            affectedTiles = affectedTiles.stream().filter(x -> !x.doesTileHaveObstacle()).collect(Collectors.toCollection(ArrayList::new));
            gameBrain.explodeObstacleToTilesNEW(affectedTiles, obstacleType);
            setObstacleClassification(EnumObstacleClassification.KNOWN_OBSTACLE);
            EnumImageGraphics graphic;
            if(this.obstacleType.equals(EnumObstacleType.RADIATION)){
                graphic = EnumImageGraphics.OBSTACLE_RADIATION;
            } else {
                graphic = EnumImageGraphics.OBSTACLE_ACID;
            }
            gameBrain.getGameboard().getTileFromCoordinate(robot.getCords().x, robot.getCords().y)
                    .setGraphicalElement(graphic,gameBrain.getGameConfig().getDifficulty());
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
