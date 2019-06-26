package quantum.Oracles;

import quantum.State;
import quantum.Gates.CNOT;
import quantum.Gates.PauliX2D;

public class NOTOracle extends Oracle{
    public void apply(State input, int firstQbit) {
        PauliX2D pauli = new PauliX2D();
        CNOT cnot = new CNOT();
        input.applyGateToQbit(firstQbit, pauli);
        input.applyGateToQbits(firstQbit, firstQbit+1, cnot);
        input.applyGateToQbit(firstQbit, pauli);
    }

    public void apply(State input){
        apply(input, 0);
    }
}
