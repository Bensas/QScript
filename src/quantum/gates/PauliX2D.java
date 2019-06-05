package quantum.gates;

import math.Imaginary;

public class PauliX2D extends Gate{
    public PauliX2D(){
        dimension = 2;
        matrix = new Imaginary[][]{
                {new Imaginary(0 ), new Imaginary(1)},
                {new Imaginary(1 ), new Imaginary(0)}};
    }
}
