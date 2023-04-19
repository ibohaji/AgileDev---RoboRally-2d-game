Feature: Player

  Scenario: Player receives 5 cards in programming phase
    Given  a player waiting to receive programming cards
    When the programming phase starts
    Then the player receive 5 cards

  Scenario: Player reorder cards in programming phase
    Given a player has received 5 cards
    When the player reorders the cards
    Then the order of the cards change accordingly

  Scenario: Player see the function of each card
    Given a player has reordered 5 cards
    When after the player reorders the cards
    Then player check the function of the programming cards

  Scenario: Player see the game board
    Given a player in the game
    When during the whole programming phase
    Then player check the game board anytime

  Scenario: Player watch the activation progress on the game board
    Given a player and its robot
    When during the activation phase
    Then the player see how many lives its robot currently has

  Scenario: Player watch the activation progress on the game board
    Given a player and its robot
    When during the activation phase
    Then the player see how many lives its robot currently has
