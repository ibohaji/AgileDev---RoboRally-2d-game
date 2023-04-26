package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Views.Gameplay.GameBoardView;
import App.RoborallyApplication.Views.Gameplay.GameView;
import App.RoborallyApplication.Views.Gameplay.Options;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

import java.awt.*;
import javax.swing.Timer;

public class GameController {
    private final ApplicationController applicationController;
    protected final LGameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, LGameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;

        if(gameBrain.getCurrentGamePhase().equals(EnumGamePhase.PROGRAMMING_PHASE)){
            this.view = new ProgrammingPhaseView(this, gameBrain);
            applicationController.changePanel(this.view);
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)) {
            this.view = new GameBoardView(this, gameBrain);
            applicationController.changePanel(this.view);
            if(gameBrain.areThereMovementsLeftInThisRound()){
                makeMovements();
            } else {
                gameBrain.endRound();
                gameBrain.startRound();
                this.view = new ProgrammingPhaseView(this, gameBrain);
                applicationController.changePanel(this.view);
            }

        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.ROUND_END)){
            if(gameBrain.isThereAWinner()){
                gameBrain.setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                //TODO
                // redirect
            } else {
                gameBrain.startRound();
                this.view = new ProgrammingPhaseView(this, gameBrain);
                applicationController.changePanel(this.view);
            }
        }
        applicationController.changePanel(this.view);

    }

    private void makeMovements() {
        Timer timer = new Timer(1000, null); // Create a timer with a 1000 ms (1 second) delay
        timer.addActionListener(e -> {
            this.gameBrain.makeMovement();
            if(this.gameBrain.areThereMovementsLeftInThisRound()){
                this.view = new GameBoardView(this, gameBrain);
                applicationController.changePanel(this.view);
            } else {
                gameBrain.endRound();
                this.view = new ProgrammingPhaseView(this, gameBrain);
                applicationController.changePanel(this.view);
            }

        });
        timer.start(); // Start the timer
    }

    protected void display() {
        applicationController.changePanel(view);
    }

    private void changeView(GameView viewToChangeTo){
        this.view = viewToChangeTo;
    }

    public LPlayer getPlayerWithoutCardSequence(){
        return gameBrain.getPlayerWithoutCardSequence();
    }

    public void setPlayerCardSequence(LPlayer player, LCardSequence cardSequence){
        System.out.println("SETTING SEQUENCE FOR PLAYER: " + player.getDisplayName());
        gameBrain.setCardSequenceForPlayer(player, cardSequence);
        if(gameBrain.haveAllPlayersSubmittedSequence()){
            gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
            applicationController.changePanel(new GameBoardView(new GameController(applicationController, gameBrain), gameBrain));
        } else {
            if(gameBrain.getPlayerWithoutCardSequence().isHuman()){
                applicationController.changePanel(new ProgrammingPhaseView(this, gameBrain));
            } else {
                gameBrain.setCardSequencesForAi();
                gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
                applicationController.changePanel(new GameBoardView(new GameController(applicationController, gameBrain), gameBrain));
            }

        }
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
