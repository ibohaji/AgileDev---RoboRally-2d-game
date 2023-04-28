package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Views.Gameplay.*;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameController {
    private final ApplicationController applicationController;
    protected final LGameBrain gameBrain;
    protected AbPhaseController controller;

    public GameController(ApplicationController applicationController, LGameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;
        updateControllerState();
    }

    public void updateControllerState() {
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameBrain.canGameContinue()) {
                    applicationController.changePanel(new GameOverPanel(GameController.this, gameBrain));
                    ((Timer) e.getSource()).stop(); // Stop the timer
                } else {
                    if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.PROGRAMMING_PHASE)) {
                        controller = new ProgrammingPhaseController(GameController.this, gameBrain);
                    } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)) {
                        if(gameBrain.getPlayers().isEmpty()){
                            applicationController.changePanel(new GameOverPanel(GameController.this, gameBrain));
                            ((Timer) e.getSource()).stop(); // Stop the timer
                        } else {
                            controller = new MovementPhaseController(GameController.this, gameBrain);
                        }
                    } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.ROUND_END)) {
                        if (gameBrain.isThereAWinner()) {
                            gameBrain.setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                            ((Timer) e.getSource()).stop(); // Stop the timer
                            applicationController.changePanel(new GameOverPanel(GameController.this, gameBrain));
                        } else if (gameBrain.getPlayers().isEmpty()) {
                            ((Timer) e.getSource()).stop(); // Stop the timer
                            applicationController.changePanel(new GameOverPanel(GameController.this, gameBrain));
                        } else {
                            gameBrain.startRound();
                            updateControllerState();
                        }
                    }
                }
                ((Timer) e.getSource()).stop(); // Stop the timer after the update
            }
        });

        timer.start(); // Start the timer
    }


    public void updateView(GameView viewToChangeTo){
        applicationController.changePanel(viewToChangeTo);
    }

    public LPlayer getPlayerWithoutCardSequence(){
        return gameBrain.getPlayerWithoutCardSequence();
    }

    // Test purpose only
    public LGameBrain getGameBrain() {
        return this.gameBrain;
    }

    public void saveGame() {
        //TODO
        // make into DTOs and save
    }

    public void quitGame() {
        applicationController.changePanel(new MainMenuView(new MainMenuController(applicationController)));
    }
}
