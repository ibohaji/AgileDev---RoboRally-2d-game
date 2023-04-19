package App.RoborallyApplication.Model;

import java.util.ArrayList;

public class LCardSequence {
    private LPlayer player;
    private ArrayList<AbCardProgramming> cardSequence;
    public LCardSequence(LPlayer player){
        this.player = player;
    }

    public void addCard(AbCardProgramming card){
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

    public AbCardProgramming getCard(int index){
        return cardSequence.get(index - 1);
    }

}
