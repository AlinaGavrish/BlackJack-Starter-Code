# Intelligent Systems Lab Assignment - Reinforcement Learning
## Overview
This repository serves as a learning resource for reinforcement learning techniques, specifically Q-learning, applied to a simplified version of the Blackjack game. It encourages exploration of different agent strategies and parameter settings to understand their impact on learning and decision-making.

## Blackjack Game Rules
* Objective: The player aims to achieve a total score closer to 21 than the dealer without exceeding it.
* Rewards:
      +1 for scoring higher than the dealer without exceeding 21 or for staying below 21 while the dealer goes over.
      0 for scoring equal to the dealer without exceeding 21.
      -1 for scoring less than the dealer or exceeding 21.
* Actions: The player can either "HIT" (take an additional card) or "STAND" (end the game) after receiving each card.
* Card Values: Numbered cards (2-10) have face value, picture cards (Jack, Queen, King) have value 10, and Ace can be 11 or 1.
* Dealer Strategy: The dealer stops if the player goes over 21. Otherwise, the dealer draws cards until the hand value is 17 or higher.
## Implementation Details
* Q-learning Agents: Implementations of table-based Q-learning agents with varying levels of complexity in analyzing the game state. Agents may consider factors like current total hand value, presence of an "active Ace," and dealer's first card.
* Agent Evaluation: The project includes code to evaluate the behavior and performance of Q-learning agents. Visual aids and analysis help understand the agents' decision-making processes.
* Experimentation: Parameters used in Q-learning, such as learning rate and exploration-exploitation trade-off, are tunable to assess their impact on learning and playing performance.
