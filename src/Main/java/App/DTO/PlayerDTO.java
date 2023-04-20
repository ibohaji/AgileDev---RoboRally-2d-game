package App.DTO;

import App.RoborallyApplication.Model.LPlayer;

import java.util.ArrayList;

public class PlayerDTO implements iFromDTO{

    public String displayName;
    public RobotDTO robotDTO;

    public ArrayList<ProgrammingCardDTO> cards;
    public PlayerDTO(){}

    public PlayerDTO(LPlayer player){
        this.robotDTO = new RobotDTO(player.getRobot());
    }

    @Override
    public LPlayer returnObjectFromDTO() {
        // TODO
        // FIX THIS
        LPlayer player = new LPlayer(this.displayName, true);
        player.assignRobot(robotDTO.returnObjectFromDTO());
        for (ProgrammingCardDTO cardDTO: cards) {
            player.assignCardToPlayer(cardDTO.returnObjectFromDTO());
        }
        return player;
    }
}
