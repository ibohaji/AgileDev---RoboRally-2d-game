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

