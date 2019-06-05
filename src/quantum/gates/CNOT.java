package quantum.gates;

import math.Imaginary;

public class CNOT extends Gate{
    public CNOT(){
        dimension = 4;
        matrix = new Imaginary[][]{
                {new Imaginary(1 ), new Imaginary(0), new Imaginary(0), new Imaginary(0)},
                {new Imaginary(0 ), new Imaginary(1), new Imaginary(0), new Imaginary(0)},
                {new Imaginary(0 ), new Imaginary(0), new Imaginary(0), new Imaginary(1)},
                {new Imaginary(0 ), new Imaginary(0), new Imaginary(1), new Imaginary(0)}};
    }
}
