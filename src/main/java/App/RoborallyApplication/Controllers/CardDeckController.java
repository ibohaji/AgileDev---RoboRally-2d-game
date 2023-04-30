package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.AbCardProgramming;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;

public class CardDeckController {

    private final GameController gameController;
    private final UserOrderedCardDeckView userOrderedDeckView;
    private final UserCardDeckView userDeckView;

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

    public UserCardDeckView getUserDeckView() {
        return userDeckView;
    }

    public UserOrderedCardDeckView getUserOrderedDeckView(){
        return userOrderedDeckView;
    }



    public void removeCardFromPlayerDeck(AbCardProgramming card) {
        this.userDeckView.removeCard(card);
    }

    public void addCardToOrdered(AbCardProgramming card) {
        this.userOrderedDeckView.addCard(card);
        removeCardFromPlayerDeck(card);
    }

    public void addCardToUnordered(AbCardProgramming card){
        this.userDeckView.addCard(card);
    }

    public void updateCardDecks() {
        userOrderedDeckView.revalidate();
        userDeckView.revalidate();
        userOrderedDeckView.repaint();
        userDeckView.repaint();
    }
}
