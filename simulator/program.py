# I/O van de raspberry
I0=0
I1=0

class gate:
    def __init__(self, name, func, inputs, inputsValue, outputsValue): # create objects on start
        self.name = name
        self.func = func
        self.inputs = inputs
        self.inputsValue = inputsValue
        self.outputsValue = outputsValue

# make logicGates
AND_01 = gate("AND_01", "logicGates.AND", ["I0", "I1"], [0, 0], [0])
AND_02 = gate("AND_02", "logicGates.AND", ["AND_01.inputsValue[0]", "AND_01.inputsValue[1]"], [0, 0],  [0])

# todo: make this automatic if possible
gates = [AND_01, AND_02] # list of all gates

def updateGates():
  for i in gates:
    for index, value in enumerate(i.inputs):
        exec(f"i.inputsValue[index] = {i.inputs[index]}")