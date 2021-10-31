package Scaler;

import java.util.*;

public class ArrayOne {


    public int maxArr1(ArrayList<Integer> A) {
        int len = A.size();
        int max_diff = 0;
        for(int i=0; i<len-1; i++){
            for(int j=i+1; j<len; j++){
                int diff = Math.abs(A.get(i) - A.get(j)) + Math.abs(i - j);
                max_diff = Math.max(max_diff, diff);
            }
        }
        return max_diff;
    }
    public int maxArr(ArrayList<Integer> A) {
        int len = A.size();
        int max_diff = 0;
        int[] add = new int[len];
        int[] sub = new int[len];

        for(int i=0; i<len; i++){
            add[i] = A.get(i) + i;
            sub[i] = A.get(i) - i;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int num : add){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        max_diff = Math.max(max_diff, max-min);
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for(int num : sub){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        max_diff = Math.max(max_diff, max-min);

        return max_diff;
    }


}
