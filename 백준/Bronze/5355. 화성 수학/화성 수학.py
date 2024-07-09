import math

T = int(input())

for i in range(T):
    line = input().split()
    length = len(line)
    
    line[0] = float(line[0])
    
    for j in range(1, length):
        if line[j] == "@":
            line[0] = line[0] * 3
        elif line[j] == "%":
            line[0] = line[0] + 5
        elif line[j] == "#":
            line[0] = line[0] - 7
    print(f"{line[0]:.2f}")