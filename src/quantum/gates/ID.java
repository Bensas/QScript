package quantum.gates;

import math.Imaginary;

public class ID extends Gate {
    public ID(){
        dimension = 2;
        matrix = new Imaginary[][]{
                {new Imaginary(1 ), new Imaginary(0)},
                {new Imaginary(0 ), new Imaginary(1)}};
    }
}
