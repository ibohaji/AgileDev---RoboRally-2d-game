package App.DTO;

import App.RoborallyApplication.Model.LGameboard;
import App.RoborallyApplication.Model.LRobot;
import App.RoborallyApplication.Model.LTile;

import java.util.ArrayList;
import java.util.UUID;

public class GameboardDTO implements iFromDTO{

    public UUID id;
    public UUID gamebrainID;
    public ArrayList<TileDTO> tiles = new ArrayList<>();
    public ArrayList<RobotDTO> robots = new ArrayList<>();
    public GameboardDTO() {

    }

    public GameboardDTO(LGameboard gameboard){
        for (LTile tile : gameboard.getTiles()){
            this.tiles.add(new TileDTO(tile));
        }
        for (LRobot robot: gameboard.getRobots()) {
            this.robots.add(new RobotDTO(robot));
        }
        this.id = gameboard.getID();
        this.gamebrainID = gameboard.getGameBrainId();
    }


    @Override
    public LGameboard returnObjectFromDTO() {
        //TODO
        ArrayList<LRobot> robotsForBoard = new ArrayList<>();
        ArrayList<LTile> tilesForBoard = new ArrayList<>();
        LGameboard gameboard = new LGameboard();
        for (RobotDTO robotDTO: robots) {
            robotsForBoard.add(robotDTO.returnObjectFromDTO());
        }
        for (TileDTO tileDTO: tiles) {
            LTile tile = tileDTO.returnObjectFromDTO();
            tilesForBoard.add(tile);
        }
        return gameboard;
    }
}
