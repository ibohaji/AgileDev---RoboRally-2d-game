package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.EnumGamePhase;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.GameBoardView;
import App.RoborallyApplication.Views.Gameplay.MovementPhaseView;
import Utils.Waiter;

import javax.swing.*;

public class MovementPhaseController extends AbPhaseController{

    private LGameBrain gameBrain;
    private GameController gameController;
    private MovementPhaseView view;

    public MovementPhaseController(GameController controller, LGameBrain gameBrain){
        this.gameController = controller;
        this.gameBrain = gameBrain;
        this.view = new MovementPhaseView(controller, gameBrain);
        makeMovements();
    }
    private void makeMovements() {
        Timer timer = new Timer(1000, null); // Create a timer with a 2000 ms (2 second) delay
        timer.addActionListener(e -> {
            if(!this.gameBrain.areThereMovementsLeftInThisRound()){
                timer.stop();
                this.view = new MovementPhaseView(this.gameController, gameBrain);
                gameController.updateView(this.view);
                Waiter.getInstance().waitForXMilliseconds(1000);
                gameBrain.endRound();
                gameController.updateControllerState();
            } else {
                if(this.gameBrain.getGameboard().getRobots().size() == 0 || this.gameBrain.isThereAWinner()){
                    gameBrain.setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                    gameController.updateControllerState();
                } else {
                    LPlayer player = this.gameBrain.getPlayerWhoIsCurrentlyMoving();
                    this.gameBrain.makeMovement();
                    if(player.getRobot().getNrOfLives() < 1){
                        this.view.addPlayerRemovedPopup(player);
                        timer.stop();
                        Waiter.getInstance().waitForXMilliseconds(1000);
                        gameController.updateControllerState();
                    } else {
                        this.view = new MovementPhaseView(this.gameController, gameBrain);
                        gameController.updateView(this.view);
                        player = this.gameBrain.getPlayerWhoIsCurrentlyMoving(); // will be null when all players moved
                        if(player.getCardSequence().getSize() == 0){
                            player.setCardSequenceToNull();
                        }
                        if(player != null){
                            if(!gameBrain.areThereMovementsLeftInThisRound()){
                                timer.stop();
                                //Waiter.getInstance().waitForXMilliseconds(1000);
                                gameBrain.endRound();
                                gameController.updateControllerState();
                            }
                        }
                    }
                }
            }
        });
        timer.start(); // Start the timer
    }
}
