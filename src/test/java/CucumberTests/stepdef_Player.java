package CucumberTests;

import Utils.Tuple;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import App.RoborallyApplication.Controllers.ApplicationController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Controllers.MainMenuController;
import App.RoborallyApplication.Model.EnumDifficulty;
import App.RoborallyApplication.Model.LGameBrain;
import App.RoborallyApplication.Model.LGameConfiguration;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class
stepdef_Player {

    /*
        Test steps for programmingPhase
     */
    private LGameBrain t_gamebrain;
    private LGameConfiguration t_gameconfig;
    // Player receives 5 cards in programming phase
    @Given("a player waiting to receive programming cards")
    public void a_player_waiting_to_receive_programming_cards() {
        t_gameconfig = new LGameConfiguration(1, EnumDifficulty.EASY, true);
        int t_norplayers = 1;
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_norplayers; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfig.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfig);
    }

    @When("the programming phase starts")
    public void the_programming_phase_starts() {

    }

    @Then("the player receive 5 cards")
    public void the_player_receive_5_cards() {
        assertEquals(5, t_gamebrain.getPlayers().get(0).getProgrammingCards().size());
    }

    // Player reorder cards in programming phase
    @Given("a player has received 5 cards")
    public void a_player_has_received_5_cards() {

    }

    @When("the player reorders the cards")
    public void the_player_reordered_the_cards() {

    }

    @Then("the order of the cards change accordingly")
    public void the_order_of_the_cards_change_accordingly() {

    }

    // Player see the function of each card
    @Given("a player has reordered 5 cards")
    public void a_player_has_reordered_5_cards() {

    }

    @When("after the player reorders the cards")
    public void after_the_player_reorders_the_cards() {

    }

    @Then("player check the function of the programming cards")
    public void player_check_the_function_of_the_programming_cards() {

    }

    // Player see the game board
    @Given("a player in the game")
    public void a_player_in_the_game() {

    }

    @When("during the whole programming phase")
    public void during_the_whole_programming_phase() {

    }

    @Then("player check the game board anytime")
    public void player_check_the_game_board_anytime() {

    }

    // Player watch the activation progress on the game board
    @Given("a player and its robot")
    public void a_player_and_its_robot() {

    }

    @When("during the activation phase")
    public void during_the_activation_phase() {

    }

    @Then("the player see how many lives its robot currently has")
    public void the_player_see_how_many_lives_its_robot_currently_has() {

    }

}
