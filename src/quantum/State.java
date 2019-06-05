package quantum;

import math.Imaginary;

import java.util.Arrays;
import java.util.Random;

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
            for (int j = 0; j < Math.abs(Integer.toBinaryString(i).length() - Math.log(components.length));j++)
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
}
