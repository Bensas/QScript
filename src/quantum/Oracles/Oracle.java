package quantum.Oracles;

import quantum.Qbit;
import quantum.State;

public abstract class Oracle {
    public abstract void apply(State input, int firstQbit);
}
