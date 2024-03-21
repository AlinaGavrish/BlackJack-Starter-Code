# comments - done 

## Run this file if you want to plot the result of one of the agents
## If needed, change the txt file we read from and all the labels

import matplotlib.pyplot as plt

# Read numbers from file: simpleQLearner
numbers_simple = []
with open('numbers_simpleQLearner.txt', 'r') as file:
    for line in file:
        numbers_simple.append(float(line.strip()))

# #Read numbers from file: smartQLearner
# numbers_smart = []
# with open('numbers_smartQLearner.txt', 'r') as file:
#     for line in file:
#         numbers_smart.append(float(line.strip()))

# # Read numbers from file: smartQLearner2
# numbers_smart2 = []
# with open('numbers_smartQLearner2.txt', 'r') as file:
#     for line in file:
#         numbers_smart2.append(float(line.strip()))

print(len(numbers_simple))

# Generate x-axis values with a step of 5 (assuming both files have the same length)
x_values = range(0, len(numbers_simple) * 5, 5)

# Plot numbers from smartQLearner with red color
plt.plot(x_values, numbers_simple, label='simpleQLearner', color='blue')

# Find the index and value of the highest number in each dataset
max_smart = max(numbers_simple)
max_index = numbers_simple.index(max_smart)

# Annotate the maximum value and its index on the plot
plt.annotate(f'Highest: {max_smart:.2f} (Iteration: {max_index * 5})', xy=(x_values[max_index], max_smart),
             xytext=(-20, 10), textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))

# Find the index and value of the last number in each dataset
last_smart = numbers_simple[-1]

plt.annotate(f'Highest: {max_smart:.2f}', xy=(x_values[numbers_simple.index(max_smart)], max_smart), xytext=(-20, 10),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))

# Annotate the last values
plt.annotate(f'Last: {last_smart:.2f}', xy=(x_values[-1], last_smart), xytext=(20, -20),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))


plt.xlabel('Iteration')
plt.ylabel('Average Reward')
plt.title('Plot of Average Rewards')
plt.legend()  # Show legend
plt.show()