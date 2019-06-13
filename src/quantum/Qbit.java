package quantum;

import math.Imaginary;

import java.util.Arrays;
import java.util.Random;

public class Qbit extends State {

    public Qbit(Imaginary probabilityOf0, Imaginary probabilityOf1){
        components = new Imaginary[2];
        components[0] = probabilityOf0;
        components[1] = probabilityOf1;
    }

    public Qbit(double probabilityOf0, double probabilityOf1){
//        if (probabilityOf0 != 1 && probabilityOf1 != 1)
//            throw new IllegalArgumentException("A Qbit must be initialized to |0> or |1>");
        components = new Imaginary[2];
        components[0] = new Imaginary(probabilityOf0, 0);
        components[1] = new Imaginary(probabilityOf1, 0);
    }

    public Imaginary probabilityOf0(){
        return components[0];
    }

    public Imaginary probabilityOf1(){
        return components[1];
    }

    @Override
    public String toString() {
        return "[0: " + components[0].toString() + ", 1:" + components[1].toString() + "]";
    }
}