package App.DTO;

import App.RoborallyApplication.Model.GameObjects.Player;

import java.util.ArrayList;

public class PlayerDTO {

    public String displayName;
    public RobotDTO robotDTO;

    public ArrayList<CardDTO> cards;
    public PlayerDTO(){}

    public PlayerDTO(Player player){
        this.robotDTO = new RobotDTO(player.getRobot());
    }
}
