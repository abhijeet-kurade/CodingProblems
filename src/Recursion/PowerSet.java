package Recursion;

import java.util.ArrayList;

public class PowerSet {
    public static void main(String[] args) {
        int[] arr= {5, 6, 7, 8};
        powerSet(arr, new ArrayList<>());
    }

    /*
    * Generate power set from
    * 1. integer array : [2, 4, 3, 6], ['e','y','a','r']
    * 2. string : dfhjs
    * */

    public static void powerSet(int[] arr, ArrayList<ArrayList<Integer>> set){
        set.add(new ArrayList<>());
        for(int num : arr){
            ArrayList<ArrayList<Integer>> tempSet = new ArrayList<>();
            int len = set.size();
            for(int i=0; i<len; i++){
                ArrayList<Integer> temp = new ArrayList<>(set.get(i));
                temp.add(num);
                System.out.println(temp);
                set.add(temp);
            }
        }
    }
}

