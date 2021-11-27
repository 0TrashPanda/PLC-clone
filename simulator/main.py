import program
import logicGates # it says not used but it is, python just doesn't know


def main():
    gates = program.gates # list of gates

    usrinput = str()

    while usrinput != "exit":
        for gate in gates:  # loop through every gate
            program.updateGates()  # update the gate values
            gate.outputsValue = eval(f"{gate.func}({gate.inputsValue})")  # simulate the gate
            print(gate.outputsValue)

        # command input with / or continue by just pressing enter
        usrinput = input()
        if usrinput.startswith("/"):
            usrinput = usrinput[1:]
            
            if usrinput.startswith("exec "): # e.g. /exec program.I0=1
                exec(usrinput[5:])
                
            elif usrinput.startswith("eval "): # e.g. /eval print(program.I0)
                eval(usrinput[5:])


if __name__ == "__main__":  # test is program is imported or the main
    main()
