package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.CardDeck.MovementCardDeckView;
import Utils.GridBagConstraintsBuilder;
import Utils.MusicPlayer;

import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.*;

public class MovementPhaseView extends GameView {
    private final GameController gameController;
    private final LGameBrain gameBrain;
    private GameBoardView gameBoardView;
    private LPlayer player;
    private MovementCardDeckView movementCardDeckView;
    private final CardDeckController cardDeckController;

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
        this.movementCardDeckView = new MovementCardDeckView(cardDeckController,gameBrain);
        add(options, new GridBagConstraintsBuilder(1, 2).inset(10,0,0,0).fill(GridBagConstraints.BOTH).build());
        add(gameBoardView, new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
        add(movementCardDeckView, new GridBagConstraintsBuilder(2, 0).fill(GridBagConstraints.BOTH).build());
    }

    public void addPlayerRemovedPopup(LPlayer player){
        MusicPlayer.getInstance().playDieSound();
        showMessageDialog(null, player.getDisplayName() + " removed.");
    }
}

