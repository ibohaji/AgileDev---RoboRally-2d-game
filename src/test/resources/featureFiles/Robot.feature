Feature: Robot Movement on the board

  Scenario: Robot turns direction successfully according to the TURN card
    Given the robot's initial direction as NORTH
    When the robot get the LEFT direction card
    Then the expected direction get updated to WEST

  Scenario: Robot moves according to the MOVEMENT card
    Given a robot placed at position and direction SOUTH
    When the robot receives the MovementCard with 1 steps
    Then the robot should move to the specific point

  Scenario: Robot repeats its previous movement according to the AGAIN card
    Given the robot is at point and facing WEST and the card used previously is U-TURN card
    When an AGAIN card is played
    Then the robot should be at point(3,3) and facing EAST after using Again card

  Scenario: Robot pushes another robot
    Given the game board is set up with robots at positions
    And robot1 is facing NORTH and robot2 is facing EAST
    When robot1 uses a movement card with 2 steps
    Then robot1 should be at (2,1) facing NORTH and robot2 should be at (2,2) facing EAST

  Scenario: Robot falls off the board due to its own movementCard
    Given a game board with difficulty Easy
    And Robot1 at position (7,7) facing EAST with 4 lives
    When Robot1 moves forward 3 step
    Then Robot1 has 3 lives left
    And Robot1 should be restored to a random start point position on the board

  Scenario: Robot pushed off the board
    Given a game board with difficulty EASY
    And Robot1 at position (6,4) and Robot2 at position (6,5)
    And Robot1 is facing NORTH and Robot2 is facing SOUTH
    And Robot1 has 2 lives and Robot2 has 5 lives
    When Robot1 moves forward one step
    Then Robot2 is at a random start point position and facing NORTH with 4 lives
    And Robot1 should be at the Robot2's previous position(6,5) and facing NORTH

  Scenario: Robot is deleted from the game
    Given Robot has one life
    When Robot suffer a damage
    Then Robot is deleted

    Scenario: Robot execute programming cards in order in activation phase
      Given a game board with a robot and 5 ordered cards
      When the activation phase starts
      Then the robot execute the cards in the given order
