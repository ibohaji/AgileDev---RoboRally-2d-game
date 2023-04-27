package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.MovementPhaseView;

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
        Timer timer = new Timer(2000, null); // Create a timer with a 2000 ms (2 second) delay
        timer.addActionListener(e -> {
            if(!this.gameBrain.areThereMovementsLeftInThisRound()){
                timer.stop();
                gameBrain.endRound();
                gameController.updateControllerState();
            } else {
                this.gameBrain.makeMovement();
                this.view = new MovementPhaseView(this.gameController, gameBrain);
                gameController.updateView(this.view);
                LPlayer player = this.gameBrain.getPlayerWhoIsCurrentlyMoving(); // will be null when all players moved
                if(player != null){
                    this.gameBrain.removeFirstCardForPlayer(player);
                    if(!gameBrain.areThereMovementsLeftInThisRound()){
                        timer.stop();
                        gameBrain.endRound();
                        gameController.updateControllerState();
                    }
                }
            }
        });
        timer.start(); // Start the timer
    }
}
