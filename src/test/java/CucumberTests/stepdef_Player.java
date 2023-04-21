package CucumberTests;

import App.RoborallyApplication.Model.*;
import Utils.Tuple;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import App.RoborallyApplication.Controllers.ApplicationController;
import App.RoborallyApplication.Controllers.GameController;
import App.RoborallyApplication.Controllers.LobbyController;
import App.RoborallyApplication.Controllers.MainMenuController;
import org.junit.Before;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class
stepdef_Player {
    private LPlayer player;
    private LGameBrain t_gamebrain;
    private LGameConfiguration t_gameconfig;
    private LCardSequence cardSequence;
    private AbCardProgramming card;
    @Before
    private void setup(){
        t_gameconfig = new LGameConfiguration(1, EnumDifficulty.EASY, true);
        int t_norplayers = 1;
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;
        for (int i = 0; i < t_norplayers; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfig.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfig);

        player = t_gamebrain.getPlayers().get(0);
    }
    // Player receives 5 cards in programming phase
    @Given("a player waiting to receive programming cards")
    public void a_player_waiting_to_receive_programming_cards() {
        setup();
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
        setup();
    }

    @When("the player reorders the cards")
    public void the_player_reordered_the_cards() {
        cardSequence = new LCardSequence(player);
        cardSequence.addCard(player.getProgrammingCards().get(4));
        cardSequence.addCard(player.getProgrammingCards().get(3));
        cardSequence.addCard(player.getProgrammingCards().get(2));
        cardSequence.addCard(player.getProgrammingCards().get(1));
        cardSequence.addCard(player.getProgrammingCards().get(0));
        player.setCardSequence(cardSequence);
    }

    @Then("the order of the cards change accordingly")
    public void the_order_of_the_cards_change_accordingly() {
        assertEquals(cardSequence.getCardSequence().get(0),player.getCardSequence().getCardSequence().get(0));
        assertEquals(cardSequence.getCardSequence().get(1),player.getCardSequence().getCardSequence().get(1));
        assertEquals(cardSequence.getCardSequence().get(2),player.getCardSequence().getCardSequence().get(2));
        assertEquals(cardSequence.getCardSequence().get(3),player.getCardSequence().getCardSequence().get(3));
        assertEquals(cardSequence.getCardSequence().get(4),player.getCardSequence().getCardSequence().get(4));
    }

    // Player watch the activation progress on the game board
    @Given("a player and its robot")
    public void a_player_and_its_robot() {
        setup();
        player.getRobot().setNrOfLives(3);
    }

    @When("during the activation phase")
    public void during_the_activation_phase() {

    }

    @Then("the player see how many lives its robot currently has")
    public void the_player_see_how_many_lives_its_robot_currently_has() {
        assertEquals(3,player.getRobot().getNrOfLives());
    }

    @Given("A player has already reordered his cards")
    public void a_player_has_already_reordered_his_cards() {
        setup();
        player.getRobot().setCords(new Point(7,7));
        player.getRobot().setDirection(EnumDirection.SOUTH);
        cardSequence = new LCardSequence(player);
        cardSequence.addCard(new LCardMovementProgramming(1));
        player.setCardSequence(cardSequence);
    }
    @When("start activation phase")
    public void start_activation_phase() {
        player.useProgrammingCard(player.getCardSequence().getCardSequence().get(0), t_gamebrain);
    }
    @Then("the robot follow the card instruction")
    public void the_robot_follow_the_card_instruction() {
        assertEquals(new Point(7,6),player.getRobot().getCords());
    }
}
