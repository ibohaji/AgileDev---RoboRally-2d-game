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

    private LCardSequence orderedCardSequence;
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

    // CARD SEQUENCE METHODS
    /**
     * At the end of the players turn, card sequence should be null.
     * At the end of the round, all players should have cardsequence as null.
     */
    protected void setCardSequenceToNull(){
        this.orderedCardSequence = null;
        this.usedCardSequence = null;
    }
    public void setOrderedCardSequence(LCardSequence orderedSequence){
        this.orderedCardSequence = orderedSequence;
        this.usedCardSequence = new LCardSequence(this);
    }

    protected boolean doesPlayerHaveMovesLeft(){
        return this.usedCardSequence.getSize() != 5;
    }

    public LCardSequence getCardSequence(){
        return this.orderedCardSequence;
    }

    protected AbCardProgramming getLastCard(){
        return this.usedCardSequence.getLastCard();
    }

    protected AbCardProgramming getNextCardFromOrderedDeck(){
        return this.orderedCardSequence.getFirstCard();
    }

    public boolean isHuman(){
        return this.isHumanPlayer;
    }

}

