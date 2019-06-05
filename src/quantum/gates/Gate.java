package quantum.gates;

import math.Imaginary;
import quantum.Qbit;
import quantum.State;

public class Gate {
    protected int dimension;
    protected Imaginary[][] matrix;

    public Gate(Imaginary[][] matrix){
        if (matrix.length != matrix[0].length)
            throw new IllegalArgumentException("The gate must be a square matrix!");
        this.matrix = matrix;
        this.dimension = matrix.length;
    }

    protected Gate(){}

    public int getDimension(){
        return dimension;
    }
    public Imaginary[][] getMatrix(){return matrix;}

    public void apply(Qbit input) {
        if (dimension > 2)
            throw new IllegalArgumentException("This gate can be applied to " + (Math.log(dimension)/Math.log(2)) + " Qbits!");
        Imaginary newProb0 = Imaginary.sum(Imaginary.multiply(input.getComponent(0), matrix[0][0]), Imaginary.multiply(input.getComponent(1), matrix[0][1]));
        Imaginary newProb1 = Imaginary.sum(Imaginary.multiply(input.getComponent(0), matrix[1][0]), Imaginary.multiply(input.getComponent(1), matrix[1][1]));
        input.setComponent(0, newProb0);
        input.setComponent(1, newProb1);
    }

    public void apply(State input){
        if (input.getNumOfComponents() != dimension)
            throw new IllegalArgumentException("This gate can be applied to " + (Math.log(dimension)/Math.log(2)) + " Qbits!");

        Imaginary[] result = new Imaginary[dimension];
        for (int i = 0; i < result.length; i++)
            result[i] = new Imaginary(0);

        //This is just matrix multiplication
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                result[i].add(Imaginary.multiply(input.getComponent(j), matrix[i][j]));

        for (int i = 0; i < dimension; i++)
            input.setComponent(i, result[i]);
    }
}
