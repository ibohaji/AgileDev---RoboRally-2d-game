package App.RoborallyApplication.Model.GameRunning;

import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.GameObjects.Player;

import java.util.ArrayList;

public class CardSequence {
    private Player player;
    private ArrayList<ProgrammingCard> cardSequence;
    public CardSequence(Player player){
        this.player = player;
    }

    public void addCard(ProgrammingCard card){
        cardSequence.add(card);
    }

    public boolean removeCard(){
        if(this.cardSequence.isEmpty()){
            return false;
        } else {
            cardSequence.remove(cardSequence.size() - 1);
            return true;
        }
    }

    public ProgrammingCard getCard(int index){
        return cardSequence.get(index - 1);
    }

}
