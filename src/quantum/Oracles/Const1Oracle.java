package quantum.Oracles;

import quantum.Qbit;
import quantum.gates.PauliX2D;

public class Const1Oracle extends Oracle{
    @Override
    public void apply(Qbit[] input) {
        PauliX2D pauli = new PauliX2D();
        pauli.apply(input[1]);
    }
}
