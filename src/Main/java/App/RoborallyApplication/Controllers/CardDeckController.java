package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import App.RoborallyApplication.Views.Gameplay.GameView;

import java.util.ArrayList;

public class CardDeckController {

    private GameController gameController;
    private UserOrderedCardDeckView userOrderedDeckView;
    private UserCardDeckView userDeckView;

    public CardDeckController(GameController gameController){
        this.gameController = gameController;
        this.userOrderedDeckView = new UserOrderedCardDeckView(this, gameController.gameBrain);
        this.userDeckView = new UserCardDeckView(this, gameController.gameBrain, userOrderedDeckView);
    }

    public GameController getGameController() {
        return gameController;
    }

    public LCardSequence getOrderedCardSequence() {
        return userOrderedDeckView.getCardSequence();
    }

    public void addCardToOrdered(AbCardProgramming card) {
        this.userOrderedDeckView.addCard(card);
    }

    public void updateCardDecks() {
        userOrderedDeckView.revalidate();
        userDeckView.revalidate();
        userOrderedDeckView.repaint();
        userDeckView.repaint();
    }

    public UserCardDeckView getUserDeckView() {
        return userDeckView;
    }

    public UserOrderedCardDeckView getUserOrderedDeckView(){
        return userOrderedDeckView;
    }

    public void removeCardFromPlayerDeck(AbCardProgramming card) {
        this.userDeckView.removeCard(card);
    }
}
