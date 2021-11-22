import program
import logicGates

def main():
    gates = program.gates

    usrinput=str()

    while usrinput!="exit":
        for gate in gates:
            program.updateGates()
            gate.outputsValue = eval(f"{gate.func}({gate.inputsValue})")
            print(gate.outputsValue)
        usrinput=input()
        if usrinput.startswith("/"):
            usrinput=usrinput[1:]
            if usrinput.startswith("exec "):
                exec(usrinput[5:])
            elif usrinput.startswith("eval "):
                eval(usrinput[5:])
            elif usrinput == "update":
                print("starting update")
                program.updateGates()
                print("done")
            
if __name__ == "__main__":
    main()