package App.RoborallyApplication.Model;
import java.util.ArrayList;
import java.util.UUID;

public class LPlayer{
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
    }

    protected void addCardToUsedSequence(AbCardProgramming card){
        this.usedCardSequence.addCard(card);
    }

    protected void removeFirstCardFromOrderedSequence(){
        this.orderedCardSequence.removeFirstCard();
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
    public void setCardSequenceToNull(){
        this.orderedCardSequence = null;
        this.usedCardSequence = null;
        this.programmingCards = new ArrayList<>();
    }
    public void setOrderedCardSequence(LCardSequence orderedSequence){
        this.orderedCardSequence = orderedSequence;
        this.usedCardSequence = new LCardSequence(this);
    }
    protected boolean doesPlayerHaveMovesLeft(){
        if(this.orderedCardSequence == null){
            return false;
        }
        return true;
    }
    public LCardSequence getCardSequence(){
        return this.orderedCardSequence;
    }
    protected AbCardProgramming getLastCard(){
        return this.usedCardSequence.getLastMovementCard();
    }
    protected AbCardProgramming getNextCardFromOrderedDeck(){
        if(this.orderedCardSequence.getSize() != 0){
            return this.orderedCardSequence.getFirstCard();
        } else {
            return null;
        }
    }

    public boolean isHuman(){
        return this.isHumanPlayer;
    }

    public void addCardFromAgain(AbCardProgramming card) {
        this.orderedCardSequence.addCardToSecondPosition(card);
    }
}

