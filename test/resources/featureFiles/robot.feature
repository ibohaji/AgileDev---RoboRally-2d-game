Feature: Changing robot's direction

  Scenario: Successfully change the direction of a robot
    Given the robot's initial direction as NORTH
    When the robot get the "left direction " card
    Then the expected robot's direction get updated to WEST

  Scenario: Move robot forward
    Given a robot with initial position at (0,0) and face NORTH
    When the robot moves forward
    Then the expected robot's position is (0,1)

  Scenario: Use again card
    Given the card which is used previously is move forward 2 step
    And robot with initial position at (0,0) and face NORTH
    When the user get the "again card" card
    Then the expected robot's position is (0,2)

  Scenario: Go back to a random starting point when robot fall off the board
    Given the random starting point and its position is (x,y)
    When the robot fall off the board
    Then the expected robot's position is (x,y)

  Scenario: Robot1 push robot2
    Given robot1's position (x1,y1) and robot2's position (x2,y2)
    And robot1's direction is NORTH
    When robot1 push robot2 are going to be in the same tile
    Then the expected robot1's position is (x2,y2), robot2's position is (x2,y2+1)

  Scenario: Get programming cards from the gamelogic