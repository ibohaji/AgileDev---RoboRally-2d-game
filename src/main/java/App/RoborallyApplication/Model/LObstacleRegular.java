package App.RoborallyApplication.Model;

import Utils.MusicPlayer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LObstacleRegular extends AbObstacle{
    private EnumObstacleClassification obstacleClassification;
    private EnumObstacleType obstacleType;

    public LObstacleRegular(EnumObstacleType obstacleType, EnumObstacleClassification obstacleClassification){
        super();
        this.obstacleType = obstacleType;
        this.obstacleClassification = obstacleClassification;
    }

    public LObstacleRegular(EnumObstacleClassification obstacleClassification){
        super();
        this.obstacleType = null;
        this.obstacleClassification = obstacleClassification;
    }


    @Override
    public void applyEffect(LRobot robot, LGameBrain gameBrain) {
        if(this.obstacleClassification.equals(EnumObstacleClassification.KNOWN_OBSTACLE)){
            robot.setNrOfLives(robot.getNrOfLives() + this.obstacleType.getDamage());
            if(this.obstacleType.getDamage() < 0) {
                if(this.obstacleType.equals(EnumObstacleType.PIT)) {
                    MusicPlayer.getInstance().play("pit.wav");
                } else {
                    MusicPlayer.getInstance().playDamageSound();
                }
            }
            if(this.obstacleType.equals(EnumObstacleType.PIT)){
                gameBrain.putRobotToRandomStartPoint(robot);
            }
            if(robot.getNrOfLives() <= 0){
                gameBrain.removePlayer(robot.getPlayer());
                gameBrain.removeRobot(robot);
            }
        } else {
            ArrayList<LTile> affectedTiles = gameBrain.getGameboard().getTilesSurroundingCoordinate(robot.getCords().x, robot.getCords().y);
            if (this.obstacleClassification.equals(EnumObstacleClassification.EXPLOSIVE_UNKNOWN)){
                EnumObstacleType type = gameBrain.getRandomObstacleTypeToExplode();
                this.setObstacleType(type);
            }
            affectedTiles = affectedTiles.stream().filter(x -> !x.doesTileHaveObstacle()).collect(Collectors.toCollection(ArrayList::new));
            affectedTiles = affectedTiles.stream().filter(x -> !x.doesTileHaveCheckpoint()).collect(Collectors.toCollection(ArrayList::new));
            affectedTiles = affectedTiles.stream().filter(x -> !x.isTileFinishPoint()).collect(Collectors.toCollection(ArrayList::new));
            gameBrain.explodeObstacleToTilesNEW(affectedTiles, obstacleType);
            setObstacleClassification(EnumObstacleClassification.KNOWN_OBSTACLE);
            EnumImageGraphics graphic;
            if(this.obstacleType.equals(EnumObstacleType.RADIATION)){
                graphic = EnumImageGraphics.OBSTACLE_RADIATION;
            } else {
                graphic = EnumImageGraphics.OBSTACLE_ACID;
            }
            int x = robot.getCords().x;
            int y = robot.getCords().y;
            gameBrain.getGameboard().getTileFromCoordinate(x, y)
                    .setGraphicalElement(graphic,gameBrain.getGameConfig().getDifficulty());
            applyEffect(robot, gameBrain);
        }
    }

    public EnumObstacleType getObstacleType() {return this.obstacleType;}

    protected void setObstacleType(EnumObstacleType type){this.obstacleType = type;}
    public EnumObstacleClassification getObstacleClassification() {
        return obstacleClassification;
    }
    public void setObstacleClassification(EnumObstacleClassification classification){
        this.obstacleClassification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LObstacleRegular that = (LObstacleRegular) o;
        return obstacleType == that.obstacleType &&
                obstacleClassification == that.obstacleClassification;
    }
}
