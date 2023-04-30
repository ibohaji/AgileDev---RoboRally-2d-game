Feature: Player

  Scenario: Player receives 5 cards in programming phase
    Given  a player waiting to receive programming cards
    When the programming phase starts
    Then the player receive 5 cards


  Scenario: Player reorder cards in programming phase
    Given a player has received 5 cards
    When the player reorders the cards
    Then the order of the cards change accordingly



  Scenario: Add and remove cards from the sequence
    Given a player with an empty card sequence
    When the player add a card to the sequence
    Then the size of the sequence should be 1
    When the player remove a card from the sequence
    Then the size of the sequence should be 0



  Scenario: Get the first and last card in the sequence
    Given a player with a card sequence with two cards
    When the player get the first card in the sequence
    Then the card should be the first card added
    When the player get the last card in the sequence
    Then the card should be the last card added







  Scenario: Player watch the activation progress on the game board
    Given a player and its robot
    When during the activation phase
    Then the player see how many lives its robot currently has

  Scenario: Player use his Programming card for his robot
    Given A player has already reordered his cards
    When start activation phase
    Then the robot follow the card instruction

    Scenario: Check if the player is human
      Given Two AI players
      When Check if the player is human
      Then The result should be false

  Scenario: Player with empty sequence
    Given A player who decided to submit an empty sequence in programming phase
    When The player tries to get cards from his sequence
    Then The player should not get a card because there aren't any

  Scenario: Player can see card images
    Given A player with all existing different card types
    Then The cards should all have imageicons


