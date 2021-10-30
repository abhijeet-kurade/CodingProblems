package Prctice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Basics {

    public static void main(String[] args) {
        System.out.println(gcd(6, 31));
        System.out.println(gcdIterative(62, 31));
    }

    public static int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b, a%b);
    }

    public static int gcdIterative(int a, int b){
        while(b!=0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}


