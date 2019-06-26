package quantum.Oracles;

import quantum.State;
import quantum.Gates.CNOT;

public class IDOracle extends Oracle{

    @Override
    public void apply(State input, int firstQbit) {
        CNOT cnot = new CNOT();
        input.applyGateToQbits(firstQbit, firstQbit+1, cnot);
    }
}
