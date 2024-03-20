import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SmartQLearner {

    public static void main(String[] args) throws IOException {
        BlackJackEnv game = new BlackJackEnv(BlackJackEnv.RENDER);

        //Init QTable
        double[][][] QTable = new double[22][2][2]; // 2 actions: HIT and STAND and 10 states - having cards of total values 0-21
		
        //Variables to measure and report average performance
		double totalReward = 0.0;
        int numberOfGames = 0;

        double learningRate = 0.5;
        double discountFactor = 0.5;
        FileWriter writer = new FileWriter("numbers_smartQLearner.txt");
        while (notDone()) {
        	// Make sure the playOneGame method returns the end-reward of the game
            totalReward += playOneGame(game, QTable, learningRate, discountFactor);
            numberOfGames++;
            if ((numberOfGames % 5) == 0){
                System.out.println("Avg reward after " + numberOfGames + " games = " + 
                						(totalReward / ++numberOfGames));

                double num = totalReward / ++numberOfGames;
                try {
                    writer.write(Double.toString(num) + "\n");
                    System.out.println("Numbers written to file successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        writer.close();

        // Show the learned QTable
        outputQTable(QTable);
    }

    private static double playOneGame(BlackJackEnv game, double[][][] QTable, double learningRate, double discountFactor) {
        ArrayList<String> gamestate;
        
        double reward = 0;

        // Playing 1 random games
        for (int i=0; i<1; i++) {
            gamestate = game.reset(); //updates total value for > 12


            System.out.println("The initial gamestate is: " + gamestate);
            while (gamestate.get(0).equals("false")) { // Game is not over yet
                System.out.println("The dealer is holding an " + BlackJackEnv.getDealerCards(gamestate));
                System.out.println("I am holding " + BlackJackEnv.getPlayerCards(gamestate));

                // Changed from Random Agent

                // Get the value that the agent is holding right now
                int current_state = BlackJackEnv.totalValue(BlackJackEnv.getPlayerCards(gamestate));

                // Check if the player is holding an active ace
                int hasActiveAce = BlackJackEnv.holdActiveAce(BlackJackEnv.getPlayerCards(gamestate)) ? 1 : 0;
                
                // Old Q value - value in QTable of current state and action = hit
                double oldQValue = QTable[current_state][0][hasActiveAce]; // Update according to action and active ace
                
                // Find the maximum achievable value when moving from the current state and having the QTable
                double maxNextQValue = getMaxQValue(current_state, hasActiveAce, QTable); // Update according to active ace

                // Now we know max Q and now we need action that led to this max Q
                int maxNextQValueACTION = (int) getMaxQValueACTION(current_state, maxNextQValue, hasActiveAce, QTable); // Update according to action and active ace
                
                // Having the action that led to the next max Q, we now update the game state
                gamestate = game.step(maxNextQValueACTION);

                // We make the recieved reward double
                reward = Double.parseDouble(gamestate.get(1));

                // Compute new Q value for current state
                double newQValue = oldQValue + learningRate * (reward + discountFactor * maxNextQValue - oldQValue);

                // Update Q table
                QTable[current_state][maxNextQValueACTION][hasActiveAce] = newQValue;
                
                System.out.println("The gamestate passed back to me was: " + gamestate);
                System.out.println("I received a reward of " + gamestate.get(1));
            }
            System.out.println("The game ended with the dealer holding " + BlackJackEnv.getDealerCards(gamestate) +
                    " for a value of " + BlackJackEnv.totalValue(BlackJackEnv.getDealerCards(gamestate)));
            System.out.println("and me holding " + BlackJackEnv.getPlayerCards(gamestate) +
                    " for a value of " + BlackJackEnv.totalValue(BlackJackEnv.getPlayerCards(gamestate)));
            System.out.println();
        }
        return reward;
    }
    private static double getMaxQValue(int state, int hasActiveAce, double[][][] QTable) {
        double maxQValue = Double.NEGATIVE_INFINITY;
        for (int action = 0; action < 2; action++) {
            if (QTable[state][action][hasActiveAce] > maxQValue) {
                maxQValue = QTable[state][action][hasActiveAce];
            }
        }
        return maxQValue;
    }

    private static double getMaxQValueACTION(int state, double maxQValue, int hasActiveAce, double[][][] QTable) {
        int action = 0;
        for (action = 0; action < 2; action++) {
            if (QTable[state][action][hasActiveAce] == maxQValue) {
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
        return (episodeCounter <= 100);
    }
    private static void outputQTable(double [][][] QTable) {
        System.out.println("QTable: ");
        for (int k = 0; k < QTable.length; k++) {
            for (int j = 0; j < QTable[k].length; j++) {
                for(int i = 0; i < QTable[k][j].length; i++){
                    System.out.print(QTable[k][j][i] + " ");
                }
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }
}