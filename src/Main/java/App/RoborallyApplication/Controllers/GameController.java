package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.*;
import App.RoborallyApplication.Views.Gameplay.GameBoardView;
import App.RoborallyApplication.Views.Gameplay.GameView;
import App.RoborallyApplication.Views.Gameplay.Options;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;
import App.RoborallyApplication.Views.Menus.MainMenuView;

import java.awt.*;
import java.util.Timer;

public class GameController {
    private final ApplicationController applicationController;
    protected final LGameBrain gameBrain;
    private GameView view;

    public GameController(ApplicationController applicationController, LGameBrain gameBrain){
        this.applicationController = applicationController;
        this.gameBrain = gameBrain;

        if(gameBrain.getCurrentGamePhase().equals(EnumGamePhase.PROGRAMMING_PHASE)){
            this.view = new ProgrammingPhaseView(this, gameBrain);
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.MOVEMENT_PHASE)) {
            this.view = new GameBoardView(this, gameBrain);
            applicationController.changePanel(this.view);
            makeMovements();
            gameBrain.endRound();
        } else if (gameBrain.getCurrentGamePhase().equals(EnumGamePhase.ROUND_END)){
            // Give a screen where you can save game or continue with next round
            this.view = new Options(this,gameBrain);
        }
        applicationController.changePanel(this.view);

    }

    private void makeMovements() {
        Timer timer = new Timer(1000, null); // Create a timer with a 1000 ms (1 second) delay
        timer.addActionListener(e -> {
            if(gameBrain.isThereAWinner()){
                this.gameBrain.setCurrentGamePhase(EnumGamePhase.GAME_OVER);
                //TODO
                // move to final screen showing winner
            } else {
                if (this.gameBrain.areThereMovementsLeftInThisRound()) {
                    this.gameBrain.makeMovement();
                    this.view = new GameBoardView(this, gameBrain);
                    applicationController.changePanel(this.view);

                } else {
                    // Stop the timer when there are no more movements left
                    timer.stop();
                    this.gameBrain.setCurrentGamePhase(EnumGamePhase.ROUND_END);
                }
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
