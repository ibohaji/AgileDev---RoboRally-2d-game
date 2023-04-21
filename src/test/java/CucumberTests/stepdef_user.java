package CucumberTests;

import App.RoborallyApplication.Controllers.ApplicationController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Controllers.MainMenuController;
import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
import Utils.Tuple;
import java.util.ArrayList;
import java.util.Random;

public class stepdef_user {

    private int t_norplayers;
    private EnumDifficulty t_difficulty;
    private boolean t_isplaywithAI;
    private LGameConfiguration t_gameconfiguration;

    private LGameBrain t_gamebrain;

    private static int t_rndInt(int min, int max) {
        Random t_rnd = new Random();
        return t_rnd.nextInt((max - min) + 1) + min;
    }

    /*
        Test steps for user
     */

    // User chooses how many players will be in a game
    @Given("a user who wants to choose players")
    public void a_user_who_wants_to_choose_players() {
        t_norplayers = 1;
        t_difficulty = EnumDifficulty.EASY;
        t_isplaywithAI = false;

    }

    @When("before a new game starts with specified players")
    public void before_a_new_game_starts_with_specified_players() {
        ApplicationController t_app = new ApplicationController();
        MainMenuController t_menuController = new MainMenuController(t_app);
        t_menuController.userClickPlay(t_difficulty, t_norplayers, t_isplaywithAI);
        LobbyController t_lobbyController = t_menuController.getLobbyController();

        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_norplayers, t_difficulty, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_norplayers; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);

        t_lobbyController.userClickStartGame(t_gameconfiguration);
        GameController t_gameController = t_lobbyController.getGameController();
        t_gamebrain = t_gameController.getGameBrain();
    }

    @Then("user choose the number of players")
    public void user_choose_the_number_of_players() {
        assertEquals(t_norplayers, t_gamebrain.getPlayers().size());

        t_gamebrain = null;
    }

    // User chooses the difficulty level
    @Given("a user who wants to choose difficulty level")
    public void a_user_who_wants_to_choose_difficulty_level() {
        t_norplayers = 1;
        switch (t_rndInt(1, 3)) {
            case(1): t_difficulty = EnumDifficulty.EASY;
            case(2): t_difficulty = EnumDifficulty.MEDIUM;
            case(3): t_difficulty = EnumDifficulty.HARD;
        }
        t_isplaywithAI = false;
    }

    @When("before a new game starts with specified difficulty level")
    public void before_a_new_game_starts_with_specified_difficulty_level() {
        ApplicationController t_app = new ApplicationController();
        MainMenuController t_menuController = new MainMenuController(t_app);
        t_menuController.userClickPlay(t_difficulty, t_norplayers, t_isplaywithAI);
        LobbyController t_lobbyController = t_menuController.getLobbyController();

        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_norplayers, t_difficulty, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_norplayers; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);

        t_lobbyController.userClickStartGame(t_gameconfiguration);
        GameController t_gameController = t_lobbyController.getGameController();
        t_gamebrain = t_gameController.getGameBrain();
    }

    @Then("user choose the difficulty level")
    public void user_choose_the_difficulty_level() {
        assertEquals(t_difficulty, t_gamebrain.getGameConfig().getDifficulty());
    }

    // User create a single lobby
    @Given("a user who wants to create lobby")
    public void a_user_who_wants_to_create_lobby() {

    }

    @When("before a new game starts with specified lobby")
    public void before_a_new_game_starts_with_specified_lobby() {

    }

    @Then("user create a multiple lobby")
    public void user_create_a_single_lobby() {

   }

   // User create a new game where the other players are controlled by AI
   @Given("a user who wants to create new game")
   public void a_user_who_wants_to_create_new_game() {

    }

    @When("before a new game starts with AI")
    public void before_a_new_game_starts_with_AI() {

    }

    @Then("user play a single_player game")
    public void user_player_a_single_player_game() {


    }

}
