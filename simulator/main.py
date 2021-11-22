import program
import logicGates


def main():
    gates = program.gates # list of gates

    usrinput = str()

    while usrinput != "exit":
        for gate in gates:  # loop through every gate
            program.updateGates()  # update the gate values
            gate.outputsValue = eval(
                f"{gate.func}({gate.inputsValue})")  # simulate the gate
            print(gate.outputsValue)

        # command input with / or continue by just pressing enter
        usrinput = input()
        if usrinput.startswith("/"):
            usrinput = usrinput[1:]
            
            if usrinput.startswith("exec "):
                exec(usrinput[5:])
                
            elif usrinput.startswith("eval "):
                eval(usrinput[5:])
                
            elif usrinput == "update":
                print("starting update")
                program.updateGates()
                print("done")


if __name__ == "__main__":  # test is program is imported or the main
    main()
