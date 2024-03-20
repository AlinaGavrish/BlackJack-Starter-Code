import matplotlib.pyplot as plt

# Read numbers from file: simpleQLearner
numbers_simple = []
with open('numbers_simpleQLearner.txt', 'r') as file:
    for line in file:
        numbers_simple.append(float(line.strip()))

# Read numbers from file: smartQLearner
numbers_smart = []
with open('numbers_smartQLearner.txt', 'r') as file:
    for line in file:
        numbers_smart.append(float(line.strip()))

# Generate x-axis values with a step of 5 (assuming both files have the same length)
x_values = range(0, len(numbers_simple) * 5, 5)

# Plot numbers from simpleQLearner with blue color
plt.plot(x_values, numbers_simple, label='simpleQLearner', color='blue')

# Plot numbers from smartQLearner with red color
plt.plot(x_values, numbers_smart, label='smartQLearner', color='red')

plt.xlabel('Index')
plt.ylabel('Number')
plt.title('Plot of Numbers')
plt.legend()  # Show legend
plt.show()
