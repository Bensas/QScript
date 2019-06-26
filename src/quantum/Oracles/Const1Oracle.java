package quantum.Oracles;

import quantum.State;
import quantum.Gates.PauliX2D;

public class Const1Oracle extends Oracle{
    @Override
    public void apply(State input, int firstQbit) {
        PauliX2D pauli = new PauliX2D();
        input.applyGateToQbit(firstQbit+1, pauli);
    }
}
