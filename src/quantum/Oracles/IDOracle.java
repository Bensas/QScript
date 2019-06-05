package quantum.Oracles;

import quantum.Qbit;
import quantum.gates.CNOT;
import quantum.gates.PauliX2D;

public class IDOracle extends Oracle{
    @Override
    public void apply(Qbit[] input) {
        CNOT cnot = new CNOT();
//        cnot.apply(input);
    }
}
