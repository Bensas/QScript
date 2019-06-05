package quantum.gates;

import math.Imaginary;
import quantum.Qbit;
import quantum.State;

public abstract class Gate {
    protected static int dimension;
    protected static Imaginary[][] matrix;

    public static void apply(Qbit input) {
        if (dimension > 2)
            throw new IllegalArgumentException("This gate can be applied to " + (Math.log(dimension)/Math.log(2)) + " Qbits!");
        Imaginary newProb0 = Imaginary.sum(Imaginary.multiply(input.getComponent(0), matrix[0][0]), Imaginary.multiply(input.getComponent(1), matrix[0][1]));
        Imaginary newProb1 = Imaginary.sum(Imaginary.multiply(input.getComponent(0), matrix[1][0]), Imaginary.multiply(input.getComponent(1), matrix[1][1]));
        input.setComponent(0, newProb0);
        input.setComponent(1, newProb1);
    }

    public static void apply(State input){
        if (input.getNumOfComponents() != dimension)
            throw new IllegalArgumentException("This gate can be applied to " + (Math.log(dimension)/Math.log(2)) + " Qbits!");

        //To work with multiple Qbits, we first need to get the tensor product between them.
//        System.out.println("Tensorizing...");
//        Imaginary[] tensor = Qbit.tensorize(input);
//        System.out.println("Result:");
//        for (Imaginary im: tensor){
//            System.out.println(im);
//        }

        Imaginary[] result = new Imaginary[dimension];
        for (int i = 0; i < result.length; i++)
            result[i] = new Imaginary(0);

        //This is just matrix multiplication
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                result[i].add(Imaginary.multiply(input.getComponent(j), matrix[i][j]));

        System.out.println("Multiplication result:");
        for (int i = 0; i < result.length; i++)
            System.out.println(result[i]);

        //We get back our Qbit values from the tensor result and store them in the input
//        Qbit[] resultTensor = Qbit.detensorize(result);
        for (int i = 0; i < dimension; i++)
            input.setComponent(i, result[i]);
    }
}
