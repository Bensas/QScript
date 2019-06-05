package quantum.Oracles;

import quantum.Qbit;

public abstract class Oracle {
    public abstract void apply(Qbit[] input);
}
