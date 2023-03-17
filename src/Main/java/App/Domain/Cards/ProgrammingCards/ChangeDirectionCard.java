package App.Domain.Cards.ProgrammingCards;

import App.Domain.Enums.TurnEnum;

public class ChangeDirectionCard extends ProgrammingCard{

    private final TurnEnum turn;
    public ChangeDirectionCard(TurnEnum turnEnum){
        turn = turnEnum;
    }


}
