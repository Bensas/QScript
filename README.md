# QScript
A basic quantum computing circuit simulation library for Java, developed primarily to be used by the Flamingo Language.
The library contains a series of useful classes:

-Complex: Implementation of a complex number based on Euler's Rho-Theta notation. Includes methods for addition, multiplication, division, power.

-State: Represents a quantum state, with complex probability amplitudes for the different states it can collapse to. It includes a measureQbit method which will generate a random float and generate a measurement based on the result, modifying the state's amplitudes accordingly. It also contains a method to apply a gate to one or more qbits, modifying the state accordingly.

-Qbit: Basically a 2-amplitudes State that can either collapse to a 1 or a 0.

-Gate: A glorified wrapper for a square matrix for the sake of clarity and prettiness.

-Oracle: Contains a series of operations to be applied to a state.
