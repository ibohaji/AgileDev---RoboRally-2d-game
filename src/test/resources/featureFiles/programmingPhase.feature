Feature: Programming Phase

  Scenario: Player receives 5 cards in programming phase
    Given  a player in the game
    When the programming phase starts
    Then the player should receive 5 cards

  Scenario: Player reorder cards in programming phase
    Given a player in the programming phase and has received 5 cards
    When the player reorders the cards
    Then the order of the cards should change accordingly

  Scenario: Player see the function of each card
    Given a player in the programming phase and has reordered 5 cards
    When after the player reorders the cards
    Then player must be able to see the function of each programming card as it is revealed

  Scenario: Player see the game board
    Given a player in the game
    When during the whole programming phase
    Then player must be able to see what is going on on the game board all the time
