package App.RoborallyApplication.Model;

import java.util.ArrayList;

public class LCardSequence {
    private final ArrayList<AbCardProgramming> cardSequence;

    public LCardSequence(LPlayer player){
        this.cardSequence = new ArrayList<>();
    }

    /**
     * Called when player moves a card from unordered to ordered deck
     * @param card Card being added to the cardSequence
     */
    public void addCard(AbCardProgramming card){
        cardSequence.add(card);
    }

    public void removeCard() {
        if (!this.cardSequence.isEmpty()) {
            cardSequence.remove(cardSequence.size() - 1);
        }
    }


    /**
     * Method to get the last movement card.
     * @return Last programming card that is not an again card,
     * will return null if there are no usable cards
     */
    public AbCardProgramming getLastMovementCard(){
        if(this.cardSequence.isEmpty()){
            return null;
        }
        for (int i = this.cardSequence.size(); i > 0; i--) {
            if(!(this.cardSequence.get(i - 1) instanceof LCardAgainProgramming)){
                return this.cardSequence.get(i - 1);
            }
        }
        return null;

    }

    public int getSize(){
        return this.cardSequence.size();
    }

    public ArrayList<AbCardProgramming> getCardSequence(){
        return this.cardSequence;
    }

    public AbCardProgramming getFirstCard() {
        if(this.cardSequence.isEmpty()){
            return null;
        }
        return this.cardSequence.get(0);
    }
    public void removeFirstCard(){
        if(!this.cardSequence.isEmpty()){
            this.cardSequence.remove(getFirstCard());
        }
    }
    public AbCardProgramming getLastCardInSequence() {
        if(this.cardSequence.isEmpty()){
            return null;
        }
        return this.cardSequence.get(this.cardSequence.size() - 1);
    }
    public void addCardToSecondPosition(AbCardProgramming card) {
        if(this.cardSequence.size() >= 1){
            this.cardSequence.add(1, card);
        }

    }
}
