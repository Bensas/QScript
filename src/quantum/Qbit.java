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
        if (probabilityOf0 != 1 && probabilityOf1 != 1)
            throw new IllegalArgumentException("A Qbit must be initialized to |0> or |1>");
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

//    public int measure(Random rng, int numberOfMeasures){
//        int zeroCount = 0;
//        int oneCount = 0;
//        double probabilityOfOne = probabilityOf1.pow(2).getRealPart();
//        for (int i = 0; i < numberOfMeasures; i++){
//            double rand = rng.nextDouble();
//            if (rand > probabilityOfOne)
//                zeroCount++;
//            else if (rand < probabilityOfOne)
//                oneCount++;
//        }
//        System.out.println("One count: " + oneCount + " - " + "Zero count: " + zeroCount);
//        return (zeroCount > oneCount) ? zeroCount : oneCount;
//    }

//    public static Qbit[] detensorize(Imaginary[] input){
//        if ((Math.log(input.length)/Math.log(2)) % 1 != 0)
//            throw new IllegalArgumentException("The input tensor must have a number of elements equal to a power of 2!");
//
//        int numberOfQbits = (int)(Math.log(input.length)/Math.log(2));
//        Qbit[] result = new Qbit[numberOfQbits];
//        for (int i = 0; i < result.length; i++)
//            result[i] = new Qbit(0, 0);
//
//        System.out.println("deTensorizing, Input:");
//        for (int j = 0; j < input.length; j++) {
//            System.out.println(input[j]);
//        }
//        for (int j = 0; j < numberOfQbits; j++){
//            boolean isAddingZero = true;
//            for (int i = 0; i < input.length; i++){
//                if (isAddingZero)
//                    result[numberOfQbits -1 - j].probabilityOf0.add(input[i]);//TODO: Should we be adding modulus?
//                else
//                    result[numberOfQbits -1 - j].probabilityOf1.add(input[i]);
//                if ((i+1) % Math.pow(2, j) == 0){
//                    isAddingZero = !isAddingZero;
//                }
//            }
//            Imaginary zeroSum = result[numberOfQbits -1 - j].probabilityOf0;
//            Imaginary oneSum = result[numberOfQbits -1 - j].probabilityOf1;
//            result[numberOfQbits -1 - j].probabilityOf0 = Imaginary.divide(zeroSum, Imaginary.sum(zeroSum, oneSum)).pow(1.0/2);
//            result[numberOfQbits -1 - j].probabilityOf1 = Imaginary.divide(oneSum, Imaginary.sum(zeroSum, oneSum)).pow(1.0/2);
//        }
//        System.out.println("deTensorized, Result:");
//        for (int j = 0; j < result.length; j++) {
//            System.out.println(result[j].toString());
//        }
//        return result;
//    }


}