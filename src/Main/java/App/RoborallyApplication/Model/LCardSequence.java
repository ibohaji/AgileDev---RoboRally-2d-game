package App.RoborallyApplication.Model;

import java.util.ArrayList;

public class LCardSequence {
    private LPlayer player;
    private ArrayList<AbCardProgramming> cardSequence;
    public LCardSequence(LPlayer player){
        this.player = player;
        this.cardSequence = new ArrayList<>();
    }

    /**
     * Called when player moves a card from unordered to ordered deck
     * @param card Card being added to the cardSequence
     */
    public void addCard(AbCardProgramming card){
        cardSequence.add(card);
    }

    /**
     * @return called when player removes card from ordered deck to unordered deck
     */
    public boolean removeCard(){
        if(this.cardSequence.isEmpty()){
            return false;
        } else {
            cardSequence.remove(cardSequence.size() - 1);
            return true;
        }
    }

    /**
     * Method to get the last movement card.
     * @return Last programming card that is not an again card,
     * will return null if there are no usable cards
     */
    public AbCardProgramming getLastCard(){
        if(this.cardSequence.isEmpty()){
            return null;
        }
        for (int i = 1; i < this.cardSequence.size(); i++) {
            if(!(this.cardSequence.get(this.cardSequence.size() - i) instanceof LCardAgainProgramming)){
                return this.cardSequence.get(this.cardSequence.size() - i);
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
    public void  setCardSequence(ArrayList<AbCardProgramming> cardSequence) {
        this.cardSequence = cardSequence;
    }

    public AbCardProgramming getFirstCard() {
        return this.cardSequence.get(0);
    }
}