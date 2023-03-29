package App.DTO;

import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.GameRunning.GameConfiguration;
import App.RoborallyApplication.Model.GameRunning.Gameboard;
import App.RoborallyApplication.Model.GameObjects.Robot;
import App.RoborallyApplication.Model.GameObjects.Tile;
import Utils.Tuple;

import java.util.ArrayList;
import java.util.UUID;

public class GameboardDTO implements iFromDTO{

    public UUID id;
    public UUID gamebrainID;
    public ArrayList<TileDTO> tiles = new ArrayList<>();
    public ArrayList<RobotDTO> robots = new ArrayList<>();
    public GameboardDTO() {

    }

    public GameboardDTO(Gameboard gameboard){
        for (Tile tile : gameboard.getTiles()){
            this.tiles.add(new TileDTO(tile));
        }
        for (Robot robot: gameboard.getRobots()) {
            this.robots.add(new RobotDTO(robot));
        }
        this.id = gameboard.getID();
        this.gamebrainID = gameboard.getGameBrainId();
    }


    @Override
    public Gameboard returnObjectFromDTO() {
        //TODO
        ArrayList<Robot> robotsForBoard = new ArrayList<>();
        ArrayList<Tile> tilesForBoard = new ArrayList<>();
        Gameboard gameboard = new Gameboard();
        for (RobotDTO robotDTO: robots) {
            robotsForBoard.add(robotDTO.returnObjectFromDTO());
        }
        for (TileDTO tileDTO: tiles) {
            Tile tile = tileDTO.returnObjectFromDTO();
            tilesForBoard.add(tile);
        }
        return gameboard;
    }
}
