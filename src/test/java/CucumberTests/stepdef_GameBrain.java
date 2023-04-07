package CucumberTests;

import App.RoborallyApplication.Model.GameRunning.GameBrain;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepdef_GameBrain {

    /*
        Test steps for GameBrain
     */

    GameBrain t_gamebrain = new GameBrain();

    // GameBrain shuffles and assigns cards
    @Given("a GameBrain and different types of cards")
    public void a_GameBrain_and_different_types_of_cards() {

    }

    @When("either the game first starts or after each round ends")
    public void either_the_game_first_starts_or_after_each_round_ends() {

    }

    @Then("GameBrain shuffle and assign cards to players")
    public void GameBrain_shuffle_and_assign_cards_to_players() {
        //t_gamebrain.givePlayersCardsForRound();
    }

    // GameBrain gets the current game phase
    @Given("a GameBrain and a game instance")
    public void a_GameBrain_and_a_game_instance() {

    }

    @When("a game is running")
    public void a_game_is_running() {

    }

    @Then("GameBrain get the current game phase")
    public void GameBrain_get_the_current_game_phase() {

    }

    // GameBrain puts robots at their starting positions
    @Given("a GameBrain a game board and robots")
    public void a_GameBrain_a_game_board_and_robots() {

    }

    @When("a new game starts")
    public void a_new_game_starts() {

    }

    @Then("GameBrain give robots their starting positions")
    public void GameBrain_give_robots_their_starting_positions() {

    }

    // GameBrain gets obstacles on the game board and their properties
    @Given("a GameBrain and game board filled with tiles")
    public void a_GameBrain_and_game_board_filled_with_tiles() {

    }

    @When("a new game board is generated")
    public void a_new_game_board_is_generated() {

    }

    @Then("GameBrain get obstacles and their properties")
    public void GameBrain_get_obstacles_and_their_properties() {

    }

    // GameBrain gets the relative position of a robot and an obstacle
    @Given("a GameBrain a robot and a tile on the game board")
    public void a_GameBrain_a_robot_and_a_tile_on_the_game_board() {

    }

    @When("a robot makes movement on the game board")
    public void a_robot_makes_movement_on_the_game_board() {

    }

    @Then("GameBrain detect if a robot encounters an obstacle")
    public void GameBrain_detect_if_a_robot_encounters_an_obstacle() {

    }

    // GameBrain gets and sets the icon image of a tile
    @Given("a GameBrain a tile and an icon image")
    public void a_GameBrain_a_tile_and_an_icon_image() {

    }

    @When("an explosive tile affects nearby tiles")
    public void an_explosive_tile_affects_nearby_tiles() {

    }

    @Then("GameBrain change graphics of a tile")
    public void GameBrain_change_graphics_of_a_tile() {

    }

    // GameBrain traces the status of a robot
    @Given("a GameBrain and a robot")
    public void a_GameBrain_and_a_robot() {

    }

    @When("a robot touches a checkpoint")
    public void a_robot_touches_a_checkpoint() {

    }

    @Then("GameBrain check how many checkpoints a robot has reached")
    public void GameBrain_check_how_many_checkpoints_a_robot_has_reached() {

    }

    // GameBrain detects if a robot has fallen out of the game board
    @Given("a GameBrain a game board and a robot")
    public void a_GameBrain_a_game_board_and_a_robot() {

    }

    @When("a robot falls into a pit")
    public void a_robot_falls_into_a_pit() {

    }

    @Then("GameBrain remove a robot from the game")
    public void GameBrain_remove_a_robot_from_the_game() {

    }

    // GameBrain detects if a player has been defeated
    @Given("a GameBrain and a player")
    public void a_GameBrain_and_a_player() {

    }

    @When("a player's robot has no life left")
    public void a_player_s_robot_has_no_life_left() {

    }

    @Then("GameBrain remove a player from the game")
    public void GameBrain_remove_a_player_from_the_game() {

    }

    // GameBrain activate an explosive tile
    @Given("a GameBrain and a tile on the game board")
    public void a_GameBrain_and_a_tile_on_the_game_board() {

    }

    @When("a robot stands on an explosive tile")
    public void a_robot_stands_on_an_explosive_tile() {

    }

    @Then("GameBrain make a bomb obstacle explode")
    public void GameBrain_make_a_bomb_obstacle_explode() {

    }

}
