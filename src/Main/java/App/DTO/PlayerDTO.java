package App.DTO;

import App.RoborallyApplication.Model.GameObjects.Player;

import java.util.ArrayList;

public class PlayerDTO implements iFromDTO{

    public String displayName;
    public RobotDTO robotDTO;

    public ArrayList<ProgrammingCardDTO> cards;
    public PlayerDTO(){}

    public PlayerDTO(Player player){
        this.robotDTO = new RobotDTO(player.getRobot());
    }

    @Override
    public Player returnObjectFromDTO() {
        Player player = new Player(this.displayName);
        player.assignRobot(robotDTO.returnObjectFromDTO());
        for (ProgrammingCardDTO cardDTO: cards) {
            player.assignCardToPlayer(cardDTO.returnObjectFromDTO());
        }
        return player;
    }
}
