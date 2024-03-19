Current Simple version: agent looks at its total hand value

Smarter:

----1)Is agent holding an "Active Ace" - can play more daringly (Тузы) 

----------detect_active_ace

----2)What is the first card held by dealer?- (знаем но пока не учитываем)

      What are specific cards held by the player?
      
Why? To increase agent's probability to win

Use Table - based representation of Q values

Tune Q-learning parameters (learning rate & discount factor) for each versions of Q-table

Total of 4 experiments (2 for 2 versions)
