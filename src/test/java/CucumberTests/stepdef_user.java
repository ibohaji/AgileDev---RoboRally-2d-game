package CucumberTests;

import App.RoborallyApplication.Model.LPlayer;
import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameBrain;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class stepdef_user {

    private LGameBrain gameBrain;

    /*
        Test steps for user
     */

    // User chooses how many players will be in a game
    @Given("a user who wants to choose players")
    public void a_user_who_wants_to_choose_players() {
        gameBrain = new LGameBrain(1, EnumDifficulty.MEDIUM);
    }

    @When("before a new game starts with specified players")
    public void before_a_new_game_starts_with_specified_players() {

    }

    @Then("user choose the number of players")
    public void user_choose_the_number_of_players() {
        gameBrain = new LGameBrain(1, EnumDifficulty.MEDIUM);
        ArrayList<LPlayer> getPlayers = gameBrain.getPlayers();
        assertEquals(1, getPlayers.size());
    }

    // User chooses the difficulty level
    @Given("a user who wants to choose difficulty level")
    public void a_user_who_wants_to_choose_difficulty_level() {
        gameBrain = new LGameBrain(1, EnumDifficulty.MEDIUM);
        EnumDifficulty difficulty = gameBrain.getGameConfig().getDifficulty();
        gameBrain = new LGameBrain(1, gameBrain.getGameConfig().getDifficulty());
    }

    @When("before a new game starts with specified difficulty level")
    public void before_a_new_game_starts_with_specified_difficulty_level() {

    }

    @Then("user choose the difficulty level")
    public void user_choose_the_difficulty_level() {
        gameBrain = new LGameBrain(1, EnumDifficulty.MEDIUM);
        assertEquals(EnumDifficulty.MEDIUM, gameBrain.getGameConfig().getDifficulty());
    }

    // User create a single lobby
    @Given("a user who wants to create lobby")
    public void a_user_who_wants_to_create_lobby() {
        gameBrain = new LGameBrain(1, EnumDifficulty.MEDIUM);
    }

    @When("before a new game starts with specified lobby")
    public void before_a_new_game_starts_with_specified_lobby() {
//
    }

    @Then("user create a single lobby")
    public void user_create_a_single_lobby() {
//        enum LobbyType {
//            SINGLE
//        }
//
//        assertEquals(LobbyType.SINGLE, gameBrain.getLobbyType());
   }
//
//
//
//
//
//
        // User create a new game where the other players are controlled by AI
        @Given("a user who wants to create new game")
        public void a_user_who_wants_to_create_new_game() {
//            gameBrain = new GameBrain(5, DifficultyEnum.MEDIUM);
        }
//
        @When("before a new game starts with AI")
        public void before_a_new_game_starts_with_AI() {
//            gameBrain.setNumAIPlayers(gameBrain.getNumPlayers() - 1);
        }
//
        @Then("user play a single_player game")
        public void user_player_a_single_player_game() {

//            assertEquals(2, gameBrain.getNumAIPlayers());
        }
    }


