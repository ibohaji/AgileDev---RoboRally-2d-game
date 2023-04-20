package App.DTO;

import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Model.LRobot;
import io.cucumber.java.bs.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerDTO implements iFromDTO{

    private String displayName;
    private RobotDTO robotDTO;
    private ArrayList<ProgrammingCardDTO> cards = new ArrayList<>();
    //private LCardSequence cardSequence;

    public PlayerDTO(LPlayer player){
        this.robotDTO = new RobotDTO(player.getRobot());
        this.displayName = player.getDisplayName();
        for (int i = 0; i < player.getProgrammingCards().size(); i++) {
            this.cards.add(new ProgrammingCardDTO(player.getProgrammingCards().get(i)));
        }
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
