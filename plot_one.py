import matplotlib.pyplot as plt

#Read numbers from file: smartQLearner
numbers_smart = []
with open('numbers_smartQLearner.txt', 'r') as file:
    for line in file:
        numbers_smart.append(float(line.strip()))

print(len(numbers_smart))
# Generate x-axis values with a step of 5 (assuming both files have the same length)
x_values = range(0, len(numbers_smart) * 5, 5)

# Plot numbers from smartQLearner with red color
plt.plot(x_values, numbers_smart, label='smartQLearner', color='red')

# Find the index and value of the highest number in each dataset
max_smart = max(numbers_smart)

# Find the index and value of the last number in each dataset
last_smart = numbers_smart[-1]

plt.annotate(f'Highest: {max_smart:.2f}', xy=(x_values[numbers_smart.index(max_smart)], max_smart), xytext=(-20, 10),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))

# Annotate the last values
plt.annotate(f'Last: {last_smart:.2f}', xy=(x_values[-1], last_smart), xytext=(20, -20),
             textcoords='offset points', arrowprops=dict(arrowstyle="->", connectionstyle="arc3"))


plt.xlabel('Index')
plt.ylabel('Number')
plt.title('Plot of Numbers')
plt.legend()  # Show legend
plt.show()