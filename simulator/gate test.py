import logicGates

print("test AND GATE")
print("A B Q")
for a in range(2):
    for b in range(2):
        print(f"{a} {b} {int(logicGates.AND([a,b]))}")
print("")

print("test NOT GATE")
print("A Q")
for a in range(2):
    print(f"{a} {int(logicGates.NOT(a))}")
print("")

print("test OR GATE")
print("A B Q")
for a in range(2):
    for b in range(2):
        print(f"{a} {b} {int(logicGates.OR([a,b]))}")
print("")