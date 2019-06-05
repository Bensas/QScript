package quantum.gates;

import math.Imaginary;
import quantum.Qbit;
import quantum.gates.Gate;

public class Hadamard2d extends Gate {

    public Hadamard2d(){
        dimension = 2;
        matrix = new Imaginary[][]{
                {new Imaginary(1 / Math.sqrt(2)), new Imaginary(1 / Math.sqrt(2))},  // 1/sqrt(2)(1  1)
                {new Imaginary(1 / Math.sqrt(2)), new Imaginary(-1 / Math.sqrt(2))}};//          (1 -1)
    }
}