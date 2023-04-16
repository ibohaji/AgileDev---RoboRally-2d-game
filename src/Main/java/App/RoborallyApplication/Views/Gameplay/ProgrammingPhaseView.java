package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import Utils.GridBagConstraintsBuilder;

import java.awt.*;
import java.util.ArrayList;

public class ProgrammingPhaseView extends GameView{

    private GameController gameController;
    private GameBrain gameBrain;
    public ProgrammingPhaseView(GameController gameController, GameBrain gameBrain) {
        super(gameController, gameBrain);
        this.gameBrain = gameBrain;
        this.gameController = gameController;
        createView();
    }

    private void createView() {
        setLayout(new GridBagLayout());
        add(new UserCardDeckView(gameController, gameBrain, new ArrayList<>()), new GridBagConstraintsBuilder(2, 0).inset(0,50,0,0).fill(GridBagConstraints.BOTH).build());
        add(new GameBoardView(gameController, gameBrain), new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
        add(new UserOrderedCardDeckView(gameController, gameBrain), new GridBagConstraintsBuilder(0, 0).inset(0,0,0,50).fill(GridBagConstraints.BOTH).build());
    }


}
