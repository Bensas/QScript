package quantum.gates;

import math.Imaginary;

public class PauliZ2D extends Gate{
    public PauliZ2D(){
        dimension = 2;
        matrix = new Imaginary[][]{
                {new Imaginary(1), new Imaginary(0)},
                {new Imaginary(0), new Imaginary(-1)}};
    }
}
