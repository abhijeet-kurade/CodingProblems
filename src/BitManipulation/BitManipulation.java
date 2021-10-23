package BitManipulation;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;

public class BitManipulation {
    public static final int END = Integer.MAX_VALUE;
    public static final int START = END - 100;

    public static boolean b(){
        return false;
    }

    public static void main(String[] args) {
        /* int count = 0;
        System.out.println(START + " "+ END);
        for(int i=START; i<=END; i++){
            System.out.println(i);
            count++;
            if(count > 100) return;
        }
        System.out.println(count); */

        final String pig = "length: 100";
        final String dog = "length: "+pig.length();
        boolean t = pig == dog;
        System.out.println("Hello : " + b());

        /*try {
            System.out.println("Hello World.");
        }
        catch (IOException e){
            System.out.println("Error");
        }
        catch (Exception e){
            System.out.println("E");
        }*/

    }
    public static ArrayList<Integer> intToBin(int val){
        ArrayList<Integer> binary = new ArrayList<>();
        while(val > 0){
            int digit = val%2 == 0 ? 0 : 1;
            binary.add(digit);
            val = val / 2;
        }
        for(int i=0; i<(binary.size() - 1)/2; i++) Collections.swap(binary, i, binary.size()-1-i);
        return binary;
    }
    public static String intToBinary(int n)
    {
        String s = "";
        while (n > 0){
            s =  ( (n % 2 ) == 0 ? 0 : 1) + s;
            n = n / 2;
        }
        return s;
    }
}
