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

    @Given("the robot's initial direction as NORTH")
    public void the_robot_s_initial_direction_as_north() {
        setup();
    }
    @When("the robot get the LEFT direction card")
    public void the_robot_get_the_left_direction_card() {
        changeDirectionProgramming = new LCardChangeDirectionProgramming(EnumTurnType.LEFT);
        changeDirectionProgramming.useCard(robot,gamebrain);
    }

    @Then("the expected direction get updated to WEST")
    public void the_expected_direction_get_updated_to_west() {
        assertEquals(EnumDirection.WEST,robot.getCurrentDirection());
    }

    @Given("a robot placed at position and direction SOUTH")
    public void a_robot_placed_at_position_and_direction_south() {
        setup();
        robot.setDirection(EnumDirection.SOUTH);
        robot.setCords(new Point(2,2));
    }
    @When("the robot receives the MovementCard with {int} steps")
    public void the_robot_receives_the_movement_card_with_steps(Integer int1) {
        movementProgramming = new LCardMovementProgramming(int1);
        movementProgramming.useCard(robot,gamebrain);
    }
    @Then("the robot should move to the specific point")
    public void the_robot_should_move_to_the_specific_point() {
        assertEquals(new Point(2,1),robot.getCords());
    }

    @Given("the robot is at point and facing WEST and the card used previously is U-TURN card")
    public void the_robot_is_at_point_and_facing_west_and_the_card_used_previously_is_u_turn_card(){
        setup();
        robot.setCords(new Point(3,3));
        robot.setDirection(EnumDirection.WEST);
        cardSequence = new LCardSequence(player0);
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.U_TURN));
        cardSequence.addCard(new LCardChangeDirectionProgramming(EnumTurnType.RIGHT));
        cardSequence.addCard(new LCardAgainProgramming());
        player0.setCardSequenceInDeck(cardSequence);
    }
    @When("an AGAIN card is played")
    public void an_again_card_is_played(){
        for (int i=0;i<=2;i++){
            player0.useProgrammingCard(player0.getCardSequenceInDeck().getCardSequence().get(i),gamebrain);
        }
    }
    @Then("the robot should be at point and facing EAST after using Again card")
    public void the_robot_should_be_at_point_and_facing_east_after_using_again_card() {
        assertEquals(new Point(3,3),robot.getCords());
        assertEquals(EnumDirection.WEST, robot.getCurrentDirection());
    }

    @Given("the game board is set up with robots at positions")
    public void the_game_board_is_set_up_with_robots_at_positions() {
        setup2();
        robot1.setCords(new Point(2,0));
        robot2.setCords(new Point(2,1));
    }
    @Given("robot1 is facing NORTH and robot2 is facing EAST")
    public void robot1_is_facing_north_and_robot2_is_facing_east() {
        robot1.setDirection(EnumDirection.NORTH);
        robot2.setDirection(EnumDirection.EAST);
    }
    @When("robot1 uses a movement card with {int} steps")
    public void robot1_uses_a_movement_card_with_steps(Integer int1) {
        movementProgramming = new LCardMovementProgramming(int1);
        movementProgramming.useCard(robot1,gamebrain);
    }
    @Then("robot1 should be at \\({double}) facing NORTH and robot2 should be at \\({double}) facing EAST")
    public void robot1_should_be_at_facing_north_and_robot2_should_be_at_facing_east(Double double1, Double double2) {
        assertEquals(new Point(2,2),robot1.getCords());
        assertEquals(new Point(2,3),robot2.getCords());
        assertEquals(EnumDirection.NORTH,robot1.getCurrentDirection());
        assertEquals(EnumDirection.EAST,robot2.getCurrentDirection());
    }

    @Given("a game board with difficulty Easy")
    public void a_game_board_with_difficulty_easy_fall() {
        setup();
    }
    @Given("Robot1 at position \\({double}) facing EAST with {int} lives")
    public void robot1_at_position_facing_east_with_lives(Double double1, Integer int1) {
        robot.setCords(new Point(7,7));
        robot.setDirection(EnumDirection.EAST);
        robot.setNrOfLives(int1);
    }
    @When("Robot1 moves forward {int} step")
    public void robot1_moves_forward_step(Integer int1) {
        movementProgramming = new LCardMovementProgramming(int1);
        movementProgramming.useCard(robot,gamebrain);
    }
    @Then("Robot1 has {int} lives left")
    public void robot1_has_lives_left(Integer int1) {

    }
    @Then("Robot1 should be restored to a random start point position on the board")
    public void robot1_should_be_restored_to_a_random_start_point_position_on_the_board() {

    }

    @Given("a game board with difficulty EASY")
    public void a_game_board_with_difficulty_easy() {
        setup2();
    }
    @Given("Robot1 at position \\({double}) and Robot2 at position \\({double})")
    public void robot1_at_position_and_robot2_at_position(Double double1, Double double2) {
        robot1.setCords(new Point(2,0));
        robot2.setCords(new Point(2,1));
    }
    @Given("Robot1 is facing NORTH and Robot2 is facing SOUTH")
    public void robot1_is_facing_north_and_robot2_is_facing_south() {
        robot1.setDirection(EnumDirection.NORTH);
        robot2.setDirection(EnumDirection.SOUTH);
    }
    @Given("Robot1 has {int} lives and Robot2 has {int} lives")
    public void robot1_has_lives_and_robot2_has_lives(Integer int1, Integer int2) {

    }
    @When("Robot1 moves forward one step")
    public void robot1_moves_forward_one_step() {

    }
    @Then("Robot2 is at a random start point position and facing NORTH with {int} lives")
    public void robot2_is_at_a_random_start_point_position_and_facing_north_with_lives(Integer int1) {

    }
    @Then("Robot1 should be at the Robot2's previous position\\({double}) and facing NORTH")
    public void robot1_should_be_at_the_robot2_s_previous_position_and_facing_north(Double double1) {

    }

    @Given("Robot has one life")
    public void robot_has_one_life() {
        setup();
        robot.setNrOfLives(1);
    }
    @When("Robot suffer a damage")
    public void robot_suffer_a_damage() {
        robot.setNrOfLives(robot.getNrOfLives()-1);
        if (robot.getNrOfLives() < 1){
            gamebrain.removeRobot(robot);
            gamebrain.removePlayer(robot.getPlayer());
        }
    }
    @Then("Robot is deleted")
    public void robot_is_deleted() {
        assertEquals(false,gamebrain.getPlayers().contains(player0));
    }

    // Robot execute programming cards in order in activation phase
    @Given("a game board with a robot and 5 ordered cards")
    public void a_game_board_with_a_robot_and_5_ordered_cards() {

    }

    @When("the activation phase starts")
    public void the_activation_phase_starts() {

    }

    @Then("the robot execute the cards in the given order")
    public void the_robot_execute_the_cards_in_the_given_order() {

    }
}
