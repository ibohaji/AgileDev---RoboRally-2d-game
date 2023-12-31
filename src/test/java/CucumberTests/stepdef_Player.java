package CucumberTests;

import App.RoborallyApplication.Model.*;
import Utils.Tuple;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class
stepdef_Player {
    private LPlayer player;
    private LGameBrain t_gamebrain;
    private LGameConfiguration t_gameconfig;
    private LCardSequence cardSequence;
    private AbCardProgramming card;
    private AbCardProgramming card2;

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
        player.setOrderedCardSequence(cardSequence);
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
    @Given("a player with an empty card sequence")
    public void a_player_with_an_empty_card_sequence (){
        setup();
    }
    @When("the player add a card to the sequence")
    public void the_player_add_a_card_to_the_sequence() {
        cardSequence = new LCardSequence(player);
        cardSequence.addCard(player.getProgrammingCards().get(4));
        player.setOrderedCardSequence(cardSequence);
    }
    @Then("the size of the sequence should be 1")
    public void the_size_of_the_sequence_should_be_1() {
        assertEquals(1, player.getCardSequence().getSize());
    }
    @When("the player remove a card from the sequence")
    public void the_player_remove_a_card_from_the_sequence() {
        cardSequence.removeCard();
    }
    @Then("the size of the sequence should be 0")
    public void the_size_of_the_sequence_should_be_0() {
        assertEquals(0, player.getCardSequence().getSize());
    }

    @Given("a player with a card sequence with two cards")
    public void a_player_with_a_card_sequence_with_two_cards() {
        setup();
    }
    @When("the player get the first card in the sequence")
    public void the_player_get_the_first_card_in_the_sequence() {
        cardSequence = new LCardSequence(player);
        card = new LCardMovementProgramming(1);
        cardSequence.addCard(card);
        player.setOrderedCardSequence(cardSequence);
    }
    @Then("the card should be the first card added")
    public void the_card_should_be_the_first_card_added() {
        assertEquals(card, player.getCardSequence().getFirstCard());
    }
    @When("the player get the last card in the sequence")
    public void the_player_get_the_last_card_in_the_sequence() {
        cardSequence = new LCardSequence(player);
        card = new LCardMovementProgramming(2);
        cardSequence.addCard(card);
        player.setOrderedCardSequence(cardSequence);
    }
    @Then("the card should be the last card added")
    public void the_card_should_be_the_last_card_added() {
        assertEquals(card, player.getCardSequence().getFirstCard());
    }

    @Given("a player with a card sequence")
    public void a_player_with_a_card_sequence() {
        setup();
        cardSequence = new LCardSequence(player);
        cardSequence.addCard(new LCardMovementProgramming(1));
        cardSequence.addCard(new LCardMovementProgramming(2));
        player.setOrderedCardSequence(cardSequence);
    }

    @Given("a player have no card in the sequence")
    public void the_player_have_no_cards_in_the_sequence() {
        setup();
        cardSequence = new LCardSequence(player);
        player.setOrderedCardSequence(cardSequence);
    }
    @When("the player wand to remove the last card")
    public void the_player_want_to_remove_the_last_card() {
        AbCardProgramming lastCard = cardSequence.getLastCardInSequence();
    }
    @Then("the result return null")
    public void the_result_return_null() {
        assertNull(cardSequence.getLastCardInSequence());
    }

    @Given("a player want to remove the last card")
    public void a_player_want_to_remove_the_last_card() {
        setup();
    }
    @When("the player have 5 cards in the sequence")
    public void the_player_have_5_cards_in_the_sequence() {
        cardSequence = new LCardSequence(player);
        cardSequence.addCard(player.getProgrammingCards().get(4));
        cardSequence.addCard(player.getProgrammingCards().get(3));
        cardSequence.addCard(player.getProgrammingCards().get(2));
        cardSequence.addCard(player.getProgrammingCards().get(1));
        cardSequence.addCard(player.getProgrammingCards().get(0));
        player.setOrderedCardSequence(cardSequence);
    }
    @Then("the 5th card is removed")
    public void the_5th_card_is_removed() {
        AbCardProgramming lastCard = cardSequence.getLastCardInSequence();
        player.getCardSequence().removeCard();
        AbCardProgramming newLastCard = cardSequence.getLastCardInSequence();
        assertNotEquals(lastCard, newLastCard);
    }


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
        player.getRobot().setCords(new Point(7,0));
        player.getRobot().setDirection(EnumDirection.SOUTH);
    }
    @When("start activation phase")
    public void start_activation_phase() {
        cardSequence = new LCardSequence(player);
        cardSequence.addCard(new LCardMovementProgramming(1));
        player.setOrderedCardSequence(cardSequence);
        t_gamebrain.makeMovement();
    }
    @Then("the robot follow the card instruction")
    public void the_robot_follow_the_card_instruction() {
        assertEquals(new Point(7,1),player.getRobot().getCords());
    }

    // Check if the player is human
    @Given("Two AI players")
    public void Two_AI_players() {
        setup();
    }

    @When("Check if the player is human")
    public void Check_if_the_player_is_human() {

    }
    @Then("The result should be false")
    public void The_result_should_be_false() {
        assertFalse(player.isHuman());
    }

    @Given("A player who decided to submit an empty sequence in programming phase")
    public void aPlayerWhoDecidedToSubmitAnEmptySequenceInProgrammingPhase() {
        setup();
        LCardSequence cardSequence = new LCardSequence(player);
        player.setOrderedCardSequence(cardSequence);
    }
    @When("The player tries to get cards from his sequence")
    public void thePlayerTriesToGetCardsFromHisSequence() {
        card = player.getCardSequence().getFirstCard();
        card2 = player.getCardSequence().getLastCardInSequence();
    }

    @Then("The player should not get a card because there aren't any")
    public void thePlayerShouldNotGetACardBecauseThereArenTAny() {
        assertEquals(0, player.getCardSequence().getSize());
        assertNull(card);
        assertNull(card2);
    }

    @Given("A player with all existing different card types")
    public void aPlayerWithAllExistingDifferentCardTypes() {
        setup();
        LCardSequence seq = new LCardSequence(player);
        seq.addCard(new LCardMovementProgramming(1));
        seq.addCard(new LCardChangeDirectionProgramming(EnumTurnType.LEFT));
        seq.addCard(new LCardAgainProgramming());
        player.setOrderedCardSequence(seq);
    }

    @Then("The cards should all have imageicons")
    public void theCardsShouldAllHaveImageicons() {
        for (AbCardProgramming card: player.getCardSequence().getCardSequence()) {
            assertNotNull(card.getCardImageIcon());
        }
    }

    @Given("A player choose 4 players in main view")
    public void A_player_choose_4_players_in_main_view() {
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(4, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;

        t_info = new Tuple<>("player1", true);
        t_playerInfo.add(t_info);
        t_info = new Tuple<>("player2", true);
        t_playerInfo.add(t_info);
        t_info = new Tuple<>("player3", false);
        t_playerInfo.add(t_info);
        t_info = new Tuple<>("player4", false);
        t_playerInfo.add(t_info);

        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        t_gamebrain = new LGameBrain(t_gameconfiguration);
    }
    @When("The player want to choose 2 AI players")
    public void The_player_want_too_choose_2_AI_players() {

    }
    @Then("Two human players and two AI players are confirmed")
    public void Two_human_players_and_two_AI_players_are_confirmed() {
        ArrayList<LPlayer> players = t_gamebrain.getPlayers();
        ArrayList<LPlayer> AIPlayers = new ArrayList<>();
        ArrayList<LPlayer> HumanPlayers = new ArrayList<>();

        for (LPlayer value : players) {
            if (value.isHuman()) {
                HumanPlayers.add(value);
            }
        }

        for (LPlayer lPlayer : players) {
            if (!lPlayer.isHuman()) {
                AIPlayers.add(lPlayer);
            }
        }

        assertEquals(2, HumanPlayers.size());
        assertEquals(2, AIPlayers.size());
    }

    //
}
