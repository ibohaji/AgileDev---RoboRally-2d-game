Feature: Obstacle actions

  Scenario: Obstacle does damage to robot
    Given a tile with a known regular obstacle
    When robot steps on the obstacle
    Then robot gets the damage from the obstacle
  Scenario: Known Explosive obstacle explodes to surrounding tiles
    Given a tile with explosive known obstacle and default floor tiles surrounding it
    When robot steps on the explosive obstacle
    Then the surrounding tiles will get infected with that obstacle

  Scenario: Unknown explosive obstacle explodes to surrounding tiles
    Given a tile with explosive unknown obstacle with default floor tiles and some other obstacles surrounding it
    When robot steps on the unknown explosive obstacle
    Then the surrounding tiles will get infected with a randomly chosen obstacle type and the tiles with existing obstacles will remain the same

  Scenario: Treadmill moves robot
    Given a tile with a forward treadmill pointing NORTH obstacle on it
    When robot that has an initial direction of SOUTH steps on the treadmill
    Then the robot will get moved by the treadmill and the the robots direction will remain SOUTH

  Scenario: Multiple treadmills move the robot to the end of the line of treadmills
    Given a sequence of treadmills
    When robot that has an initial direction of SOUTH moves onto a sequence of treadmills
    Then the robot will get moved by the treadmill to the end of the treadmill sequence and the the robots direction will remain SOUTH

