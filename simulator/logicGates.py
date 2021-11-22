# AND
def AND(gates):
    if gates[0] and gates[1]:
        return True
    else:
        return False
    
# NOT
def NOT(a):
    return not a

# OR
def OR(gates):
    return NOT(AND([NOT(gates[0]), NOT(gates[1])]))