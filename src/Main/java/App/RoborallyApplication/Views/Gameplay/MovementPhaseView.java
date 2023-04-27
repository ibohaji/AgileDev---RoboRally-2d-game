package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
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
    private CardDeckController cardDeckController;
    private GameBoardView gameBoardView;
    private LPlayer player;


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
        this.gameBoardView = new GameBoardView(gameController, gameBrain);
        add(options, new GridBagConstraintsBuilder(1, 1).inset(75,0,0,0).fill(GridBagConstraints.BOTH).build());
        add(gameBoardView, new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
    }
}

