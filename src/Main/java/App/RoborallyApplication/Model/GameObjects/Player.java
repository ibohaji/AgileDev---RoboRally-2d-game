package App.RoborallyApplication.Model.GameObjects;
import App.DTO.PlayerDTO;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Model.iToDTO;
import Utils.JsonHelper;
import java.util.ArrayList;
import java.util.UUID;

public class Player implements iToDTO {
    private UUID id;
    private String displayName;
    private ArrayList<ProgrammingCard> programmingCards = new ArrayList<>();
    private Robot robot = null;

    public Player(){}
    public Player(String displayName){
        this.id = UUID.randomUUID();
        this.displayName = displayName;
    }

    public void assignCardToPlayer(ProgrammingCard card){
        programmingCards.add(card);
    }

    public void useProgrammingCard(ProgrammingCard card, GameBrain gameBrain){
        robot.useProgrammingCard(card, gameBrain);
    }

    protected ArrayList<ProgrammingCard> getProgrammingPhaseOrdering(Player playerToGetOrderFrom){
        return new ArrayList<>();
    }

    /**
     * Robot can only be assigned to player if he previously doesn't have a robot assigned to them
     * @param robotToAssign robot object to assign to the player
     * @return boolean value whether robot could be assigned
     */
    public boolean assignRobot(Robot robotToAssign){
        if(robot == null){
            robot = robotToAssign;
            robot.assignPlayer(this);
            return true;
        }
        return false;
    }
    @Override
    public String DTOasJson() {
        PlayerDTO playerDTO = new PlayerDTO(this);
        return JsonHelper.serializeObjectToJson(playerDTO);
    }
    @Override
    public UUID getID() {
        return this.id;
    }

    public Robot getRobot(){
        return this.robot;
    }

    public String getDisplayName(){
        return this.displayName;
    }


    public ArrayList<ProgrammingCard> getCards(){
        return programmingCards;
    }


    /**
     * A player's initial starting position can only be accepted if the selected tire is a spawning gear and not occupied
     * @param x,y the selected coordinates of the player
     * @Error if the selected cords are invalid, otherwise it is passed to the gamebrain/gameboard
     */    public void StartingPosition(int x,int y){
         // TODO

    }
}

