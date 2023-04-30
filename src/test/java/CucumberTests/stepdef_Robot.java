package CucumberTests;

import App.RoborallyApplication.Model.*;
import Utils.Tuple;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;

import java.awt.Point;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class stepdef_Robot {

    private LCardMovementProgramming movementProgramming;
    private LCardChangeDirectionProgramming changeDirectionProgramming;
    private LCardAgainProgramming againProgramming;
    private LGameBrain gamebrain;
    private LCardSequence cardSequence;
    private LRobot robot;
    private LRobot robot1;
    private LRobot robot2;
    private LPlayer player0,player1;
    private LGameConfiguration gameConfiguration;
    private int t_no_of_players;


    /*
        Tests for Robot
    */

    @Before
    public void setup(){
        t_no_of_players = 1;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        gamebrain = new LGameBrain(t_gameconfiguration);
        robot = gamebrain.getPlayers().get(0).getRobot();

        player0 = gamebrain.getPlayers().get(0);
    }
    public void setup2(){
        t_no_of_players = 2;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.EASY, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        gamebrain = new LGameBrain(t_gameconfiguration);

        robot1 = gamebrain.getPlayers().get(0).getRobot();
        robot2 = gamebrain.getPlayers().get(1).getRobot();

        player0 = gamebrain.getPlayers().get(0);
        player1 = gamebrain.getPlayers().get(1);

    }
    public void setup3(){
        t_no_of_players = 2;
        LGameConfiguration t_gameconfiguration = new LGameConfiguration(t_no_of_players, EnumDifficulty.MEDIUM, true);
        ArrayList<Tuple<String, Boolean>> t_playerInfo = new ArrayList<>();
        Tuple<String, Boolean> t_info;;
        for (int i = 0; i < t_no_of_players; i++) {
            t_info = new Tuple<>("player" + i, false);
            t_playerInfo.add(t_info);
        }
        t_gameconfiguration.createPlayersFromLobby(t_playerInfo);
        gamebrain = new LGameBrain(t_gameconfiguration);

        robot1 = gamebrain.getPlayers().get(0).getRobot();
        robot2 = gamebrain.getPlayers().get(1).getRobot();

        player0 = gamebrain.getPlayers().get(0);
        player1 = gamebrain.getPlayers().get(1);

    }

    // Robot turns direction successfully according to the TURN card
    @Given("the robot's initial direction as NORTH")
    public void the_robot_s_initial_direction_as_north() {
        setup();
        robot.setNrOfLives(4);
    }
    @When("the robot get the LEFT direction card")
    public void the_robot_get_the_left_direction_card() {
        changeDirectionProgramming = new LCardChangeDirectionProgramming(EnumTurnType.LEFT);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(changeDirectionProgramming);
        player0.setOrderedCardSequence(cardSequence);
        gamebrain.makeMovement();
    }

    @Then("the expected direction get updated to WEST")
    public void the_expected_direction_get_updated_to_west() {
        assertEquals(EnumDirection.WEST,robot.getCurrentDirection());
    }

    // Robot moves according to the MOVEMENT card
    @Given("a robot placed at position and direction SOUTH")
    public void a_robot_placed_at_position_and_direction_south() {
        setup();
        robot.setDirection(EnumDirection.WEST);
        robot.setCords(new Point(7,0));
    }
    @When("the robot receives the MovementCard with {int} steps")
    public void the_robot_receives_the_movement_card_with_steps(Integer int1) {
        movementProgramming = new LCardMovementProgramming(int1);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(movementProgramming);
        player0.setOrderedCardSequence(cardSequence);
        gamebrain.makeMovement();
    }
    @Then("the robot should move to the specific point")
    public void the_robot_should_move_to_the_specific_point() {
        assertEquals(new Point(6,0),robot.getCords());
    }

    // Robot repeats its previous movement according to the AGAIN card
    @Given("the robot is at point and facing WEST and the card used previously is U-TURN card")
    public void the_robot_is_at_point_and_facing_west_and_the_card_used_previously_is_u_turn_card(){
        setup();
        robot.setCords(new Point(7,2));
        robot.setDirection(EnumDirection.NORTH);
        robot.setNrOfLives(4);
    }
    @When("an AGAIN card is played")
    public void an_again_card_is_played(){
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.U_TURN));
        cardSequence.addCard(new LCardAgainProgramming());
        cardSequence.addCard(new LCardMovementProgramming(1));
        cardSequence.addCard(new LCardAgainProgramming());
        player0.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }
    @Then("the robot should be at point and facing EAST after using Again card")
    public void the_robot_should_be_at_point_and_facing_east_after_using_again_card() {
        assertEquals(new Point(7,0),robot.getCords());
        assertEquals(EnumDirection.NORTH, robot.getCurrentDirection());
    }

    // Robot pushes another robot
    @Given("the game board is set up with robots at positions")
    public void the_game_board_is_set_up_with_robots_at_positions() {
        setup2();
        robot1.setCords(new Point(0,0));
        robot2.setCords(new Point(0,1));
        robot1.setNrOfLives(4);
        robot2.setNrOfLives(4);
    }
    @Given("robot1 is facing NORTH and robot2 is facing EAST")
    public void robot1_is_facing_north_and_robot2_is_facing_east() {
        robot1.setDirection(EnumDirection.SOUTH);
        robot2.setDirection(EnumDirection.SOUTH);
    }
    @When("robot1 uses a movement card with {int} steps")
    public void robot1_uses_a_movement_card_with_steps(Integer int1) {
        movementProgramming = new LCardMovementProgramming(int1);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(movementProgramming);
        player0.setOrderedCardSequence(cardSequence);
        gamebrain.makeMovement();
    }
    @Then("robot1 should be at X={int} Y={int} facing NORTH and robot2 should be at X={int} Y={int} facing EAST")
    public void robot1_should_be_at_facing_north_and_robot2_should_be_at_facing_east(int r1_x, int r1_y,int r2_x, int r2_y) {
        assertEquals(new Point(r1_x, r1_y),robot1.getCords());
        assertEquals(new Point(r2_x, r2_y),robot2.getCords());
        assertEquals(EnumDirection.SOUTH,robot1.getCurrentDirection());
        assertEquals(EnumDirection.SOUTH,robot2.getCurrentDirection());
    }

    //  Robot falls off the board due to its own movementCard
    @Given("a game board with difficulty Easy")
    public void a_game_board_with_difficulty_easy_fall() {
        setup();
    }
    @Given("Robot1 at position X={int} Y={int} facing EAST with {int} lives")
    public void robot1_at_position_facing_east_with_lives(int x, int y, int l) {
        robot.setCords(new Point(x, y));
        robot.setDirection(EnumDirection.EAST);
        robot.setNrOfLives(l);
    }
    @When("Robot1 moves forward {int} step")
    public void robot1_moves_forward_step(Integer int1) {
        movementProgramming = new LCardMovementProgramming(int1);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(movementProgramming);
        player0.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }
    @Then("Robot1 has {int} lives left")
    public void robot1_has_lives_left(Integer int1) {
        assertEquals(3,robot.getNrOfLives());
    }
    @Then("Robot1 should be restored to a random start point position on the board")
    public void robot1_should_be_restored_to_a_random_start_point_position_on_the_board() {

    }

    // Robot pushed off the board
    @Given("a game board with difficulty EASY")
    public void a_game_board_with_difficulty_easy() {
        setup2();
    }
    @Given("Robot1 at position X={int} Y={int} and Robot2 at position X={int} Y={int}")
    public void robot1_at_position_and_robot2_at_position(int r1_x, int r1_y, int r2_x, int r2_y) {
        robot1.setCords(new Point(r1_x, r1_y));
        robot2.setCords(new Point(r2_x, r2_y));
    }
    @Given("Robot1 is facing NORTH and Robot2 is facing SOUTH")
    public void robot1_is_facing_north_and_robot2_is_facing_south() {
        robot1.setDirection(EnumDirection.NORTH);
        robot2.setDirection(EnumDirection.SOUTH);
    }

    // Robot is deleted from the game
    @Given("Robot has one life")
    public void robot_has_one_life() {
        setup2();
        robot1.setCords(new Point(5,4));
        robot1.setDirection(EnumDirection.WEST);
        robot1.setNrOfLives(1);
        robot2.setDirection(EnumDirection.NORTH);
        robot2.setCords(new Point(4,4));
        robot2.setNrOfLives(5);
    }
    @When("Robot suffer a damage")
    public void robot_suffer_a_damage() {
        movementProgramming = new LCardMovementProgramming(2);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(movementProgramming);
        player0.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }
    @Then("Robot is deleted")
    public void robot_is_deleted() {
        assertEquals(false,gamebrain.getPlayers().contains(player0));
    }

    // Robot execute programming cards in order in activation phase
    @Given("a game board with a robot and 5 ordered cards")
    public void a_game_board_with_a_robot_and_5_ordered_cards() {
        setup();
        robot.setDirection(EnumDirection.SOUTH);
        robot.setCords(new Point(7,0));
    }

    @When("the activation phase starts")
    public void the_activation_phase_starts() {
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.LEFT));
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.RIGHT));
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.U_TURN));
        cardSequence.addCard(new LCardAgainProgramming());
        cardSequence.addCard(new LCardMovementProgramming(1));
        player0.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }

    @Then("the robot execute the cards in the given order")
    public void the_robot_execute_the_cards_in_the_given_order() {
        assertEquals(new Point(7,1),robot.getCords());
        assertEquals(EnumDirection.SOUTH,robot.getCurrentDirection());
    }

    @Given("a game board with difficulty Medium")
    public void a_game_board_with_difficulty_medium() {
        setup3();
        robot1.setCords(new Point(0,4));
        robot1.setDirection(EnumDirection.EAST);
        robot2.setCords(new Point(0,5));
        robot2.setDirection(EnumDirection.NORTH);
    }
    @When("Robot1 encounter a checkpoint")
    public void robot1_encounter_a_checkpoint() {
        movementProgramming = new LCardMovementProgramming(1);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(movementProgramming);
        player0.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }
    @When("Robot2 encounter the same checkpoint")
    public void robot2_encounter_the_same_checkpoint() {
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(new LCardMovementProgramming(1));
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.RIGHT));
        cardSequence.addCard(new LCardMovementProgramming(1));
        player1.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }
    @Then("Robot1 have this check point")
    public void robot1_have_this_check_point() {
        assertEquals(new Point(1,4),robot1.getCheckpointsDone().get(0));

    }
    @Then("Robot2 will not get this check point")
    public void robot2_will_not_get_this_check_point() {
        assertEquals(true,robot2.getCheckpointsDone().isEmpty());
    }

    @Given("robot1 is at x={int} y={int} with {int} lives facing North and robot2 is at x={int} y={int} with {int} lives facing West")
    public void robot1_is_at_x_y_with_lives_facing_north_and_robot2_is_at_x_y_with_lives_facing_west(Integer int1, Integer int2, Integer int3, Integer int4, Integer int5, Integer int6) {
        setup2();
        robot1.setCords(new Point(int1,int2));
        robot1.setDirection(EnumDirection.NORTH);
        robot1.setNrOfLives(int3);
        robot2.setCords(new Point(int4,int5));
        robot2.setDirection(EnumDirection.WEST);
        robot2.setNrOfLives(int6);
    }
    @When("robot1 use his programming card which is Movementcard for {int} steps")
    public void robot1_use_his_programming_card_which_is_movementcard_for_steps(Integer int1) {
        movementProgramming = new LCardMovementProgramming(1);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(movementProgramming);
        player0.setOrderedCardSequence(cardSequence);
        while(player0.getCardSequence().getSize() != 0){
            gamebrain.makeMovement();
        }
    }
    @Then("robot1 goes to x={int} y={int} with same lives facing North")
    public void robot1_goes_to_x_y_with_same_lives_facing_north(Integer int1, Integer int2) {
        assertEquals(new Point(int1,int2),robot1.getCords());
        assertEquals(4,robot1.getNrOfLives());
        assertEquals(EnumDirection.NORTH,robot1.getCurrentDirection());
    }
    @Then("robot2 is at x={int} y={int} and suffered one damage")
    public void robot2_is_at_x_y_and_suffered_one_damage(Integer int1, Integer int2) {
        assertEquals(new Point(int1,int2),robot2.getCords());
        assertEquals(2,robot2.getNrOfLives());
        assertEquals(EnumDirection.WEST,robot2.getCurrentDirection());
    }

}
