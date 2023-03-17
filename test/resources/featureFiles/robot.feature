Feature: Changing robot's direction

  Scenario: Successfully change the direction of a robot
    Given the robot's initial direction as NORTH
    When the robot get the "left direction " card
    Then the expected direction get updated to WEST

  Scenario: Move robot forward
    Given a robot with initial position at (0,0)
    When the robot moves forward
    Then the expected position is (0,1)
