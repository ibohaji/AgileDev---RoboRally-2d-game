Feature: AI gameplay


Scenario: AI accesses the map
  Given a new game has started
  When the AI opponent accesses the map
  Then the AI opponent can view the game board and its surroundings

Scenario: AI communicates with the game brain
  Given a new game has started
  When the AI opponent requests information from the game brain regarding the tile
  Then the AI opponent receives the current state of the game

Scenario: AI calculates the shortest path
  Given a new game has started
  And the game board is set up
  When the AI opponent calculates the shortest path
  Then the AI opponent identifies the optimal route


Scenario: AI adjusts card sequence dynamically
  Given a new game has started
  And the AI opponent has selected its programming cards
  When the game state changes
  Then the AI opponent dynamically adjusts its card sequence to maintain its competitive edge


