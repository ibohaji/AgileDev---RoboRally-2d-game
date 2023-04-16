package App.RoborallyApplication.Views.Gameplay.CardDeck;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.Cards.ProgrammingCards.ProgrammingCard;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Views.Gameplay.CardView;
import App.RoborallyApplication.Views.Gameplay.GameView;

import java.util.ArrayList;

public class UserCardDeckView extends GameView {
    private final ArrayList<ProgrammingCard> cards;
    private final ArrayList<CardView> cardViews = new ArrayList<CardView>();

    public UserCardDeckView(GameController controller, GameBrain gameBrain, ArrayList<ProgrammingCard> cards) {
        super(controller, gameBrain);
        this.cards = cards;

        for (ProgrammingCard card : cards) {
            CardView cardView = new CardView(card);
            cardViews.add(cardView);
            add(cardView);
        }
    }

}

