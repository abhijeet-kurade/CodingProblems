package BitManipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BitManipulation {
    public static void main(String[] args) {
        int num = 167;
        System.out.println(intToBin(num));
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
