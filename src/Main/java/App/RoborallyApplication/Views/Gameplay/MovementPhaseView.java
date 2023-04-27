package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserMovementCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;

import javax.swing.*;
import java.awt.*;

/**
 * @author: Zoe Liu
 */


public class MovementPhaseView extends GameView{
    private GameController gameController;
    private LGameBrain gameBrain;
    private UserMovementCardDeckView userMovementCardDeckView;
    private CardDeckController cardDeckController;
    private GameBoardView gameBoardView;
    private LPlayer player;

//    private UserCardDeckView userDeckView;
//    private UserOrderedCardDeckView userOrderedDeckView;


    public MovementPhaseView(GameController gameController, LGameBrain gameBrain) {
        super(gameController, gameBrain);
        this.gameBrain = gameBrain;
        this.gameController = gameController;
        this.cardDeckController = new CardDeckController(gameController);
        this.player = gameBrain.getPlayerWhoIsCurrentlyMoving();
        createView();
    }

    private void createView() {
        setLayout(new GridBagLayout());
        Options options = new Options(gameController,gameBrain);
        this.userMovementCardDeckView = new UserMovementCardDeckView(cardDeckController, gameBrain);
        this.gameBoardView = new GameBoardView(gameController, gameBrain);
        add(options, new GridBagConstraintsBuilder(1, 2).inset(75,0,0,0).fill(GridBagConstraints.BOTH).build());
        add(this.cardDeckController.getUserOrderedDeckView(), new GridBagConstraintsBuilder(0, 0).inset(0,0,0,50).fill(GridBagConstraints.BOTH).build());
        add(gameBoardView, new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
        UserOrderedCardDeckView orderedDeckView = this.cardDeckController.getUserOrderedDeckView();
        add(orderedDeckView, new GridBagConstraintsBuilder(0, 0).inset(0,0,0,50).fill(GridBagConstraints.BOTH).build());
    }


}

