package quantum.Oracles;

import quantum.Qbit;
import quantum.gates.CNOT;
import quantum.gates.PauliX2D;

public class NOTOracle extends Oracle{
    @Override
    public void apply(Qbit[] input) {
        PauliX2D pauli = new PauliX2D();
        pauli.apply(input[0]);
        CNOT cnot = new CNOT();
        //cnot.apply(input);
        PauliX2D pauli2 = new PauliX2D();
        pauli2.apply(input[0]);
    }
}
