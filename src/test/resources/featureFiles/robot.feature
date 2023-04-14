Feature: Robot Movement on the board

  Scenario: Robot turns direction successfully according to the TURN card
    Given the robot's initial direction as NORTH
    When the robot get the LEFT direction card
    Then the expected direction get updated to WEST

  Scenario: Robot moves according to the MOVEMENT card
    Given a robot placed at position "1,1" and direction NORTH
    When the robot receives the MovementCard with 3 steps
    Then the robot should move the specific number of steps in its current direction

  Scenario: Robot repeats its previous movement according to the AGAIN card
    Given the robot has previously moved a certain number of steps in a certain direction
    And an AGAIN card is played
    Then the robot should move the same number of steps in the same direction as its previous movement

  Scenario: Robot pushes another robot
    Given the game board is set up with robots at positions (2, 0) and (2, 3)
    And the first robot is facing NORTH
    And the first robot uses a movement card with 3 steps
    Then the second robot should be pushed one tile in the direction of the first robot
    And the first robot should end up in the tile previously occupied by the second robot


  Scenario: Robot falls off the board due to its own movemnentCard
    Given a game board with difficulty Easy
    And Robot1 at position x=8,y=8
    And Robot1 is facing EAST
    When Robot1 moves forward one step
    Then Robot1 should fall off the board
    And Robot1 should lose a life
    And Robot1 should be restored to a random position on the board
    And if Robot1 has no lives left, Robot1 should be removed from the game



  Scenario: Robot pushed off the board
    Given a game board with difficulty EASY
    And Robot1 at position (6,5) and Robot2 at position (6,5)
    And Robot1 is facing NORTH and Robot2 is facing SOUTH
    When Robot1 moves forward one step
    Then Robot1 should push Robot2 off the board
    And Robot2 should lose a life
    And Robot2 should be restored to a random position on the board
    And if Robot2 has no lives left, Robot2 should be removed from the game
