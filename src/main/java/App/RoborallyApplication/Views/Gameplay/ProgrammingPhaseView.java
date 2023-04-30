package App.RoborallyApplication.Views.Gameplay;

import App.RoborallyApplication.Controllers.CardDeckController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Controllers.ProgrammingPhaseController;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserCardDeckView;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import Utils.Fonts;
import Utils.GridBagConstraintsBuilder;
import javax.swing.*;
import java.awt.*;

public class ProgrammingPhaseView extends GameView{

    private final GameController gameController;
    private final LGameBrain gameBrain;

    private UserCardDeckView userDeckView;

    private UserOrderedCardDeckView userOrderedDeckView;

    private final CardDeckController cardDeckController;

    private GameBoardView gameBoardView;
    private final LPlayer player;

    private final ProgrammingPhaseController controller;
    public ProgrammingPhaseView(ProgrammingPhaseController controller, LGameBrain gameBrain) {
        super(controller.getGameController(), gameBrain);
        this.gameBrain = gameBrain;
        this.gameController = controller.getGameController();
        this.controller = controller;
        this.cardDeckController = new CardDeckController(gameController);
        this.player = gameController.getPlayerWithoutCardSequence();
        createView();
    }

    private void createView() {
        setLayout(new GridBagLayout());
        Options options = new Options(gameController,gameBrain);
        this.userDeckView = new UserCardDeckView(cardDeckController, gameBrain, userOrderedDeckView);
        this.userOrderedDeckView = new UserOrderedCardDeckView(cardDeckController, gameBrain);
        this.gameBoardView = new GameBoardView(gameController, gameBrain);
        add(this.cardDeckController.getUserOrderedDeckView(), new GridBagConstraintsBuilder(0, 0).inset(100,0,0,50).fill(GridBagConstraints.BOTH).build());
        add(gameBoardView, new GridBagConstraintsBuilder(1, 0).fill(GridBagConstraints.BOTH).build());
        add(this.cardDeckController.getUserDeckView(), new GridBagConstraintsBuilder(2, 0).inset(100,50,0,0).weightY(1).anchor(GridBagConstraints.NORTH).fill(GridBagConstraints.BOTH).build());

        // Submit button
        JButton submitButton = new JButton("SUBMIT SEQUENCE");
        submitButton.setFont(Fonts.LARGE);
        submitButton.addActionListener(e -> {
            this.controller.setPlayerCardSequence(this.player, this.cardDeckController.getOrderedCardSequence());
            submitButton.setEnabled(false);
        });
        add(submitButton, new GridBagConstraintsBuilder(1, 1).inset(50,0,0,0).fill(GridBagConstraints.BOTH).build());
        add(options, new GridBagConstraintsBuilder(1, 2).inset(0,0,0,0).fill(GridBagConstraints.BOTH).build());


    }


}
