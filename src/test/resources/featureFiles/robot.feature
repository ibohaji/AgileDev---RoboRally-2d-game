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

  Scenario: Go back to a random starting point when robot fall off the board
    Given the random starting point and its position is (x,y)
    When the robot fall off the board
    Then the expected robot's position is (x,y)

  Scenario: Robot1 push robot2
    Given robot1's position (x1,y1) and robot2's position (x2,y2)
    And robot1's direction is NORTH
    When robot1 push robot2 are going to be in the same tile
    Then the expected robot1's position is (x2,y2), robot2's position is (x2,y2+1)

