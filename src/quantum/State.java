package quantum;

import math.Imaginary;

import quantum.gates.Gate;
import quantum.gates.ID;

import java.util.*;

public class State {
    protected Imaginary[] components;

    public State(Qbit[] qbits){
        components = tensorize(qbits);
    }

    protected State() {}

    public void measure(){
        StringBuilder builder = new StringBuilder();
        builder.append("MEASURE: ");
        for (int i = 0; i < components.length; i++){
            builder.append('|');
            for (int j = 0; j < Math.abs(Integer.toBinaryString(i).length() - getNumberOfQbits());j++)
                builder.append('0');
            builder.append(Integer.toBinaryString(i));
            builder.append(">: ");
            builder.append(components[i].pow(2).toString());
            if (i != components.length-1) builder.append(" - ");
        }
        System.out.println(builder.toString());
    }

    public Imaginary getComponent(int n){
        return components[n];
    }

    public void setComponent(int n, Imaginary newVal){
        components[n] = newVal;
    }

    public int getNumOfComponents(){
        return components.length;
    }

    public int getNumberOfQbits(){return (int)(Math.log(components.length)/Math.log(2));}

    public void applyGateToQbit(int n, Gate gate){
        if (gate.getDimension() > 2)
            throw new IllegalArgumentException("This method can only be used with single-qbit gates!");
        //In order to apply a gate to a single Qbit, we must apply to the whole state a new gate,
        //which will be the result of a tensor product between the original gate and a bunch of Identity Gates for the
        //Qbits what the gate should not apply to.
        //eg. To apply X gate to the second qbit in a state with 4 qbits, we do the following tensor product:
        // ID x X x ID x ID
        //Ad then apply the resulting matrix(as a Gate) to the whole state.
        Gate[] gates = new Gate[getNumberOfQbits()];
        for (int i = 0; i < gates.length; i++) gates[i] = new ID();
        gates[n] = gate;
        Gate resultingGate = kroeneckerProductForGates(gates);
        resultingGate.apply(this);
    }

    public void applyGateToQbits(int firstQbit, int lastQbit, Gate gate){
        if (gate.getDimension() != (int)Math.pow(2, lastQbit-firstQbit + 1))
            throw new IllegalArgumentException("The number of qbits does not match the gate size!");

        Gate[] gates = new Gate[getNumberOfQbits() - (lastQbit-firstQbit)];
        for (int i = 0; i < gates.length; i++) gates[i] = new ID();
        gates[firstQbit] = gate;
        Gate resultingGate = kroeneckerProductForGates(gates);
        resultingGate.apply(this);
    }

    public static Imaginary[] tensorize(Qbit[] input){
        Imaginary[][] imaginaryInput = new Imaginary[input.length][2];
        for (int i = 0; i < input.length; i++)
            imaginaryInput[i] = input[i].components;
        return tensorizeRec(imaginaryInput);
    }

    private static Imaginary[] tensorizeRec(Imaginary[][] input){
        if (input.length == 2){
            Imaginary[] result = new Imaginary[input[0].length * input[1].length];
            for (int i = 0; i < input[0].length; i++)
                for (int j = 0; j < input[1].length; j++)
                    result[i * input[1].length + j] = Imaginary.multiply(input[0][i], input[1][j]);
            return result;
        }
        Imaginary[][] toBeTensorized = new Imaginary[][]{
                input[0],
                tensorizeRec(Arrays.copyOfRange(input, 1, input.length))
        };
        return tensorizeRec(toBeTensorized);
    }

    static Gate kroeneckerProductForGates(Gate[] gates){
        Queue<Imaginary[][]> matrices = new LinkedList<>();
        for (Gate gate: gates){
            matrices.offer(gate.getMatrix());
        }

        Imaginary[][] result = matrices.poll();
        while (!matrices.isEmpty()){
            Imaginary[][] next = matrices.poll();
            result = kroneckerProduct(result, next);
        }
        Gate resultGate = new Gate(result);
        return resultGate;
    }

    static Imaginary[][] kroneckerProduct(Imaginary a[][], Imaginary b[][])
    {
        int rowa = a.length;
        int rowb = b.length;
        int cola = a[0].length;
        int colb = b[0].length;

        Imaginary[][] c = new Imaginary[rowa * rowb][cola * colb];

        //Each element of matrix a is multiplied by whole matrix b and stored in matrix c
        for (int i = 0; i < rowa; i++)
            for (int j = 0; j < cola; j++)
                for (int k = 0; k < rowb; k++)
                    for (int l = 0; l < colb; l++)
                        c[i*rowb + k][colb*j + l] = Imaginary.multiply(a[i][j], b[k][l]);

        return c;
    }
}
