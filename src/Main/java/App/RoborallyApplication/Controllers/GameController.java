package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Views.Gameplay.*;
import App.RoborallyApplication.Views.Gameplay.CardDeck.UserOrderedCardDeckView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

import java.awt.*;
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

    public void updateControllerState(){
        System.out.println(gameBrain.getCurrentGamePhase());
        if(gameBrain.getCurrentGamePhase().equals(EnumGamePhase.PROGRAMMING_PHASE)){
            this.controller = new ProgrammingPhaseController(this, gameBrain);
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)) {
            this.controller = new MovementPhaseController(this, gameBrain);
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.ROUND_END)){
            if(gameBrain.isThereAWinner()){
                gameBrain.setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                this.applicationController.changePanel(new GameOverPanel(this, gameBrain));
            } else {
                gameBrain.startRound();
                updateControllerState();
            }
        }
    }

    public void updateView(GameView viewToChangeTo){
        applicationController.changePanel(viewToChangeTo);
    }

    public void updateOrderedCardDeckView(LPlayer player) {
        /*UserOrderedCardDeckView orderedDeckView = cardDeckController.getUserOrderedDeckView();
        orderedDeckView.clearCardSlots();
        for (AbCardProgramming card : player.getCardSequence().getCardSequence()) {
            orderedDeckView.addCard(card);
        }*/
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
