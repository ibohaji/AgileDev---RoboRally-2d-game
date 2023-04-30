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
    Then the robot should be at point and facing EAST after using Again card

  Scenario: Robot pushes another robot
    Given the game board is set up with robots at positions
    And robot1 is facing NORTH and robot2 is facing EAST
    When robot1 uses a movement card with 2 steps
    Then robot1 should be at X=0 Y=1 facing NORTH and robot2 should be at X=0 Y=2 facing EAST

  Scenario: Robot falls off the board due to its own movementCard
    Given a game board with difficulty Easy
    And Robot1 at position X=7 Y=0 facing EAST with 4 lives
    When Robot1 moves forward 1 step
    Then Robot1 has 3 lives left
    And Robot1 should be restored to a random start point position on the board

  Scenario: Robot is deleted from the game
    Given Robot has one life
    When Robot suffer a damage
    Then Robot is deleted

  Scenario: Robot execute programming cards in order in activation phase
    Given a game board with a robot and 5 ordered cards
    When the activation phase starts
    Then the robot execute the cards in the given order

  Scenario: Robot gets Check Point
    Given a game board with difficulty Medium
    When Robot1 encounter a checkpoint
    And Robot2 encounter the same checkpoint
    Then Robot1 have this check point
    And Robot2 will not get this check point

  Scenario: Robot1 push robot2 and robot2 suffer one damage
    Given robot1 is at x=1 y=7 with 4 lives facing North and robot2 is at x=1 y=6 with 3 lives facing West
    When robot1 use his programming card which is Movementcard for 1 steps
    Then robot1 goes to x=1 y=6 with same lives facing North
    And robot2 is at x=1 y=5 and suffered one damage

  Scenario: Robot can only collect checkpoints once
    Given a game board with difficulty hard
    When robot collects checkpoints
    Then the order is checked automatically

  Scenario: Robot1 push robot2 off the board
    Given robot1 is at x=6 y=0 with 4 lives facing EAST and robot2 is at x=7 y=0 with 1 lives facing NORTH
    When robot1 make one step movement
    Then robot2 was pushed off the board by robot1 and reborn at a random start point