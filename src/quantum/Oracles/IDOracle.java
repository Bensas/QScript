package quantum.Oracles;

import quantum.Qbit;
import quantum.State;
import quantum.gates.CNOT;
import quantum.gates.PauliX2D;

public class IDOracle extends Oracle{

    @Override
    public void apply(State input, int firstQbit) {
        CNOT cnot = new CNOT();
        input.applyGateToQbits(firstQbit, firstQbit+1, cnot);
    }
}
