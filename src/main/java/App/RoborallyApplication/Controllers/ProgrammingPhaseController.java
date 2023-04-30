package App.RoborallyApplication.Controllers;

import App.RoborallyApplication.Model.EnumGamePhase;
import App.RoborallyApplication.Model.LCardSequence;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Views.Gameplay.ProgrammingPhaseView;

public class ProgrammingPhaseController extends AbPhaseController{

    private final GameController gameController;
    private final LGameBrain gameBrain;

    public ProgrammingPhaseController(GameController gameController, LGameBrain gameBrain){
        this.gameController = gameController;
        this.gameBrain = gameBrain;
        ProgrammingPhaseView view = new ProgrammingPhaseView(this, gameBrain);
        this.gameController.updateView(view);
    }
    public void setPlayerCardSequence(LPlayer player, LCardSequence cardSequence){
        gameBrain.setCardSequenceForPlayer(player, cardSequence);
        if(gameBrain.haveAllPlayersSubmittedSequence()){
            gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
            gameController.updateControllerState();
        } else {
            if(gameBrain.getPlayerWithoutCardSequence().isHuman()){
                gameController.updateControllerState();
            } else {
                gameBrain.setCardSequencesForAi();
                gameBrain.setCurrentGamePhase(EnumGamePhase.MOVEMENT_PHASE);
                gameController.updateControllerState();
            }
        }
    }
    public GameController getGameController(){return this.gameController;}

}
