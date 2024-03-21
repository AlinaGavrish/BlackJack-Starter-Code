# comments - done 

## Run this file if you want to plot the result of all three agents for  visualization of comparison

import matplotlib.pyplot as plt

# Read numbers from file: simpleQLearner
numbers_simple = []
with open('numbers_simpleQLearner.txt', 'r') as file:
    for line in file:
        numbers_simple.append(float(line.strip()))

#Read numbers from file: smartQLearner
numbers_smart = []
with open('numbers_smartQLearner.txt', 'r') as file:
    for line in file:
        numbers_smart.append(float(line.strip()))

# Read numbers from file: smartQLearner2
numbers_smart2 = []
with open('numbers_smartQLearner2.txt', 'r') as file:
    for line in file:
        numbers_smart2.append(float(line.strip()))

# Generate x-axis values with a step of 5 (assuming both files have the same length)
x_values = range(0, len(numbers_simple) * 5, 5)

# Plot numbers from simpleQLearner with blue color
plt.plot(x_values, numbers_simple, label='simpleQLearner', color='blue')

# Plot numbers from smartQLearner with red color
plt.plot(x_values, numbers_smart, label='smartQLearner', color='red')

# Plot numbers from smartQLearner2 with green color
plt.plot(x_values, numbers_smart2, label='smartQLearner2', color='green')

# Find the index and value of the highest number in each dataset
max_simple = max(numbers_simple)
max_smart = max(numbers_smart)
max_smart2 = max(numbers_smart2)

# Find the index and value of the last number in each dataset
last_simple = numbers_simple[-1]
last_smart = numbers_smart[-1]
last_smart2 = numbers_smart2[-1]

max_index_simple = numbers_simple.index(max_simple)
max_index_smart = numbers_smart.index(max_smart)
max_index_smart2 = numbers_smart2.index(max_smart2)


# Annotate the maximum value and its index on the plot
# plt.annotate(f'Highest: {max_smart:.2f} (Iteration: {max_index_simple * 5})', xy=(x_values[max_index_simple], max_smart),
#              xytext=(-20, 10), textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))
# plt.annotate(f'Highest: {max_smart:.2f} (Iteration: {max_index_smart * 5})', xy=(x_values[max_index_smart], max_smart),
#              xytext=(-20, 10), textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))
# plt.annotate(f'Highest: {max_smart:.2f} (Iteration: {max_index_smart2 * 5})', xy=(x_values[max_index_smart2], max_smart),
#              xytext=(-20, 10), textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))

# Annotate the highest values
plt.annotate(f'Highest: {max_simple:.2f}', xy=(x_values[numbers_simple.index(max_simple)], max_simple), xytext=(-20, 10),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))
plt.annotate(f'Highest: {max_smart:.2f}', xy=(x_values[numbers_smart.index(max_smart)], max_smart), xytext=(-20, 10),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))
plt.annotate(f'Highest: {max_smart2:.2f}', xy=(x_values[numbers_smart2.index(max_smart2)], max_smart2), xytext=(-20, 10),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))

# Annotate the last values
plt.annotate(f'Last: {last_simple:.2f}', xy=(x_values[-1], last_simple), xytext=(20, -20),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))
plt.annotate(f'Last: {last_smart:.2f}', xy=(x_values[-1], last_smart), xytext=(20, -20),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))
plt.annotate(f'Last: {last_smart2:.2f}', xy=(x_values[-1], last_smart2), xytext=(20, -20),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))

plt.xlabel('Iteration')
plt.ylabel('Awerage Reward')
plt.title('Plot of Awerage Rewards')
plt.legend()  # Show legend
plt.show()