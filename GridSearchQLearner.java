// comments - done 

import java.io.IOException;
import java.util.ArrayList;

public class GridSearchQLearner {

    public static void main(String[] args) throws IOException {
        BlackJackEnv game = new BlackJackEnv(BlackJackEnv.RENDER);
		
        // Variables to store average performance
		double totalReward = 0.0;
        int numberOfGames = 0;

        // Define parameters for grid search
        double[] learningRates = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        double[] discountFactors = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
        
        // Variables to track maximum reward and corresponding parameters
        double maxReward = Double.NEGATIVE_INFINITY;
        double maxLearningRate = 0.0;
        double maxDiscountFactor = 0.0;

        int count = 0;

        for (double learningRate : learningRates) {
            for (double discountFactor : discountFactors) {

                // Init QTable
                double[][] QTable = new double[22][2]; // 2 actions: HIT and STAND and 10 states - having cards of total values 0-21

                System.out.println("Iteration number " + count);

                // Reset total reward for each parameter combination
                totalReward = 0.0;
        
                while (notDone()) {
                    // Make sure the playOneGame method returns the end-reward of the game
                    totalReward += playOneGame(game, QTable, learningRate, discountFactor);
                    numberOfGames++;
                }

                double avgReward = totalReward / numberOfGames;

                // Check if this combination achieved max reward
                if (avgReward > maxReward) {
                    maxReward = avgReward;
                    maxLearningRate = learningRate;
                    maxDiscountFactor = discountFactor;
                }

                // Reset episode counter and total reward for next parameter combination
                episodeCounter = 0;
                numberOfGames = 0;
                count++;

                // Show the learned QTable
                outputQTable(QTable);
            }
        }

        System.out.println("Maximum reward " + maxReward);
        System.out.println("Achieved with learning rate = " + maxLearningRate + " and discount factor = " + maxDiscountFactor);
    }

    private static double playOneGame(BlackJackEnv game, double[][] QTable, double learningRate, double discountFactor) {
        ArrayList<String> gamestate;
        
        double reward = 0.0;

        // Playing 1 random game
        for (int i=0; i<1; i++) {
            gamestate = game.reset(); //updates total value for > 12

            while (gamestate.get(0).equals("false")) { // Game is not over yet

                // Get the value that the agent is holding right now
                int current_state = BlackJackEnv.totalValue(BlackJackEnv.getPlayerCards(gamestate));
                
                // Old Q value - value in QTable of current state and action = hit
                double oldQValue = QTable[current_state][0]; // Update according to action and active ace
                
                // Find the maximum achievable value when moving from the current state and having the QTable
                double maxNextQValue = getMaxQValue(current_state, QTable); // Update according to active ace

                // Now we know max Q and now we need action that led to this max Q
                int maxNextQValueACTION = (int) getMaxQValueACTION(current_state, maxNextQValue, QTable); // Update according to action and active ace
                
                // Having the action that led to the next max Q, we now update the game state
                gamestate = game.step(maxNextQValueACTION);

                // We make the recieved reward double
                reward = Double.parseDouble(gamestate.get(1));

                // Compute new Q value for current state
                double newQValue = oldQValue + learningRate * (reward + discountFactor * maxNextQValue - oldQValue);

                // Update Q table
                QTable[current_state][maxNextQValueACTION] = newQValue;
            }
        }
        return reward;
    }

    // Returns the largest value of Q
    private static double getMaxQValue(int state, double[][] QTable) {
        double maxQValue = Double.NEGATIVE_INFINITY;
        for (int action = 0; action < 2; action++) {
            if (QTable[state][action] > maxQValue) {
                maxQValue = QTable[state][action];
            }
        }
        return maxQValue;
    }

    // Returns the action that corresponds to the biggest achievable Q
    private static double getMaxQValueACTION(int state, double maxQValue, double[][] QTable) {
        int action = 0;
        for (action = 0; action < 2; action++) {
            if (QTable[state][action] == maxQValue) {
                return action;
            }
        }
        return action;
    }

    // Example stopping condition: fixed number of games
    private static int episodeCounter = 0;
    private static boolean notDone() {
        episodeCounter++;
        System.out.println(episodeCounter);
        return (episodeCounter < 10);
    }

    // Prints the QTable
    private static void outputQTable(double [][] QTable) {
        System.out.println("QTable: ");
        for (int k = 0; k < QTable.length; k++) {
            for (int j = 0; j < QTable[k].length; j++) {
                    System.out.print(QTable[k][j] + " ");
            }
        }
            System.out.println(); // Move to the next line after printing each row
    }
}



