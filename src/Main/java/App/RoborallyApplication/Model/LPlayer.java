package App.RoborallyApplication.Model;
import App.DTO.PlayerDTO;
import Utils.JsonHelper;
import java.util.ArrayList;
import java.util.UUID;

public class LPlayer implements IToDTO {
    private UUID id;
    private String displayName;
    private ArrayList<AbCardProgramming> programmingCards = new ArrayList<>();

    private boolean isHumanPlayer;

    private LCardSequence cardSequence;

    private LCardSequence usedCardSequence;
    private LRobot robot = null;
    public LPlayer(){}
    public LPlayer(String displayName, boolean isHumanPlayer){
        this.isHumanPlayer = isHumanPlayer;
        this.id = UUID.randomUUID();
        this.displayName = displayName;
    }
    public void assignCardToPlayer(AbCardProgramming card){
        programmingCards.add(card);
    }
    public void useProgrammingCard(AbCardProgramming card, LGameBrain gameBrain) {
        robot.useProgrammingCard(card, gameBrain);
        this.usedCardSequence.addCard(card);
    }

    /**
     * Robot can only be assigned to player if he previously doesn't have a robot assigned to them
     * @param robotToAssign robot object to assign to the player
     * @return boolean value whether robot could be assigned
     */
    public boolean assignRobot(LRobot robotToAssign){
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

    public LRobot getRobot(){
        return this.robot;
    }

    public String getDisplayName(){
        return this.displayName;
    }


    public ArrayList<AbCardProgramming> getProgrammingCards(){
        return programmingCards;
    }


    /**
     * A player's initial starting position can only be accepted if the selected tire is a spawning gear and not occupied
     * @param x,y the selected coordinates of the player
     * @Error if the selected cords are invalid, otherwise it is passed to the gamebrain/gameboard
     */    public void StartingPosition(int x,int y){
         // TODO
    }

    // CARD SEQUENCE METHODS
    /**
     * At the end of the round, cardsequence should be null
     */
    protected void setCardSequenceToNull(){
        this.cardSequence = null;
        this.usedCardSequence = null;
    }
    public void setCardSequence(LCardSequence cardSequence){
        this.cardSequence = cardSequence;
        this.usedCardSequence = new LCardSequence(this);
    }

    public LCardSequence getCardSequence(){
        return this.cardSequence;
    }

    protected AbCardProgramming getLastCard(){
        return this.usedCardSequence.getLastCard();
    }

    public boolean isHuman(){
        return this.isHumanPlayer;
    }

}

