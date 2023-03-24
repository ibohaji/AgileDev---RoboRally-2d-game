package App.Domain;

import App.DTO.PlayerDTO;
import App.Domain.Cards.Card;
import Utils.JsonHelper;

import java.util.ArrayList;

public class Player implements InGameObject{
    private final String displayName;

    private ArrayList<Card> cards = new ArrayList<>();

    private Robot robot = null;
    public Player(String displayName){
        this.displayName = displayName;
    }

    protected void addCard(){

    }


    /**
     * Robot can only be assigned to player if he previously doesn't have a robot assigned to them
     * @param robotToAssign robot object to assign to the player
     * @return boolean value whether robot could be assigned
     */
    protected boolean assignRobot(Robot robotToAssign){
        if(robot == null){
            robot = robotToAssign;
            return true;
        }
        return false;
    }


    @Override
    public String toJson() {
        PlayerDTO playerDTO = new PlayerDTO(this);
        return JsonHelper.serializeObjectToJson(playerDTO);
    }
}
