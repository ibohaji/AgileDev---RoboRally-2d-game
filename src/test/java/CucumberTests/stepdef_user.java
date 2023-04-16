package CucumberTests;

import App.RoborallyApplication.Model.GameObjects.Player;
import App.RoborallyApplication.Model.GameRunning.DifficultyEnum;
import App.RoborallyApplication.Model.GameRunning.GameBrain;
import App.RoborallyApplication.Views.Menus.LobbyViewHost;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;

public class stepdef_user {

    private GameBrain gameBrain;

    /*
        Test steps for user
     */

    // User chooses how many players will be in a game
    @Given("a user who wants to choose players")
    public void a_user_who_wants_to_choose_players() {
        gameBrain = new GameBrain(1, DifficultyEnum.MEDIUM);
    }

    @When("before a new game starts with specified players")
    public void before_a_new_game_starts_with_specified_players() {

    }

    @Then("user choose the number of players")
    public void user_choose_the_number_of_players() {
        gameBrain = new GameBrain(1, DifficultyEnum.MEDIUM);
        ArrayList<Player> getPlayers = gameBrain.getPlayers();
        Assert.assertEquals(1, getPlayers.size());
    }

    // User chooses the difficulty level
    @Given("a user who wants to choose difficulty level")
    public void a_user_who_wants_to_choose_difficulty_level() {
        gameBrain = new GameBrain(1, DifficultyEnum.MEDIUM);
        DifficultyEnum difficulty = gameBrain.getGameConfig().getDifficulty();
        gameBrain = new GameBrain(1, gameBrain.getGameConfig().getDifficulty());
    }

    @When("before a new game starts with specified difficulty level")
    public void before_a_new_game_starts_with_specified_difficulty_level() {

    }

    @Then("user choose the difficulty level")
    public void user_choose_the_difficulty_level() {
        gameBrain = new GameBrain(1, DifficultyEnum.MEDIUM);
        Assert.assertEquals(DifficultyEnum.MEDIUM, gameBrain.getGameConfig().getDifficulty());
    }

    // User create a single lobby
    @Given("a user who wants to create lobby")
    public void a_user_who_wants_to_create_lobby() {
        gameBrain = new GameBrain(1, DifficultyEnum.MEDIUM);
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
//        Assert.assertEquals(LobbyType.SINGLE, gameBrain.getLobbyType());
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

//            Assert.assertEquals(2, gameBrain.getNumAIPlayers());
        }
    }


