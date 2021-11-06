package Scaler;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayOne {

    public static void main(String[] args) {
        int i = 31;

        System.out.print((i>>5) & 1);
        System.out.print((i>>4) & 1);
        System.out.print((i>>3) & 1);
        System.out.print((i>>2) & 1);
        System.out.print((i>>1) & 1);
        System.out.print((i>>0) & 1);
    }
    public int maxArr1(ArrayList<Integer> A) { // Time : O(n^2) Space : O(1)
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
    public int maxArr(ArrayList<Integer> A) { // Time : O(n) Space : O(n)
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
    public int maxArr2(ArrayList<Integer> A) { // Time : O(n) Space : O(1)
        int max_diff = 0;
        int len = A.size();
        int minOne = Integer.MAX_VALUE;
        int minTwo = Integer.MAX_VALUE;
        int maxOne = Integer.MIN_VALUE;
        int maxTwo = Integer.MIN_VALUE;
        for(int i=0; i<len; i++){
            minOne = Math.min(minOne, A.get(i) + i);
            minTwo = Math.min(minTwo, A.get(i) - i);
            maxOne = Math.max(maxOne, A.get(i) + i);
            maxTwo = Math.max(maxTwo, A.get(i) - i);
        }
        max_diff = Math.max(max_diff, maxOne - minOne);
        max_diff = Math.max(max_diff, maxTwo - minTwo);
        return max_diff;
    }
    public static int minMaxSubArray(ArrayList<Integer> A) { // Time : O(n) Space : O(1)
        int len = A.size();
        int min = Collections.min(A);
        int lastMin = -1;
        int max = Collections.max(A);
        int lastMax = -1;
        int length = Integer.MAX_VALUE;
        for(int i=0; i<len; i++){
            int num = A.get(i);
            if(num == min){
                length = lastMax == -1 ? Integer.MAX_VALUE : Math.min(length, i - lastMax + 1);
                lastMin = i;
            }
            else if(num == max){
                length = lastMin == -1 ? Integer.MAX_VALUE : Math.min(length, i - lastMin + 1);
                lastMax = i;
            }
        }
        return length == Integer.MAX_VALUE ? 1 : length ;
    }
    public static int minMaxSubArray1(ArrayList<Integer> A) { // Time : O(n) Space : O(1)
        int len = A.size();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : A){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int minIdx = -1, maxIdx = -1;
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<len; i++){
            int num = A.get(i);
            if(num == min) minIdx = Math.max(minIdx, i);
            if(num == max) maxIdx = Math.max(maxIdx, i);
            if(minIdx != -1 && maxIdx != -1) ans = Math.min(ans, Math.abs(minIdx - maxIdx) + 1);
        }
        return ans;
    }

    public int absoluteMaxFour(int[] A, int[] B, int[] C, int[] D) {
        int n= A.length;
        int[] min = new int[32];
        int[] max = new int[32];
        Arrays.fill(min, Integer.MAX_VALUE);
        Arrays.fill(max, Integer.MIN_VALUE);
        int max_diff = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            ArrayList<Integer> lst = new ArrayList<>();
            for(int num=0; i<32; i++){
                int a = ((num>>4) & 1) == 0 ? -A[i] : A[i];
                int b = ((num>>3) & 1) == 0 ? -B[i] : B[i];
                int c = ((num>>2) & 1) == 0 ? -C[i] : C[i];
                int d = ((num>>1) & 1) == 0 ? -D[i] : D[i];
                int e = ((num>>0) & 1) == 0 ? -i : i;
                int sum = a + b + c + d + e;
                min[i] = Math.min(min[i], sum);
                max[i] = Math.max(max[i], sum);
            }
        }
        for(int i=0; i<32; i++){
            max_diff = Math.max(max_diff, max[i] - min[i]);
        }
        return max_diff;
    }

    public int minimumJumps(int[] A){
        int n = A.length;
        int nextJump = A[0];
        int i = 1;
        int steps = 0;
        while(i < n){
            int longest = Integer.MIN_VALUE;
            for(;i<n && i<=nextJump; i++)
                longest = Math.max(longest, A[i] + i);
            if(longest < i) return -1;
            nextJump = longest;
            steps += 1;
        }
        return steps;
    }

    public ArrayList<Interval> mergeOverlappingIntervals(ArrayList<Interval> intervals) {
        int n = intervals.size();
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        ArrayList<Interval> merged = new ArrayList<>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for(int i=1; i<n; i++){
            Interval current = intervals.get(i);
            if(end < current.start){
                merged.add(new Interval(start, end));
                start = current.start;
                end = current.end;
            }
            else {
                end = Math.max(end, current.end);
            }
        }
        merged.add(new Interval(start, end));
        return merged;
    }


    class Interval{
        int start;
        int end;
        public Interval(){
            this.start = 0;
            this.end = 0;
        }
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals, Interval newInterval){
        int len = intervals.size();
        boolean inserted = false;
        for(int i=0; i<len; i++)
            if(newInterval.start <= intervals.get(i).start){
                intervals.add(i, newInterval);
                inserted = true;
            }
        if(!inserted) intervals.add(newInterval);

        ArrayList<Interval> newIntervals = new ArrayList<>();

        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for(int i =1; i<len; i++){
            int currStart = intervals.get(i).start;
            int currEnd = intervals.get(i).end;
            if(end < currStart){
                newIntervals.add(new Interval(start, end));
                start = currStart;
                end = currEnd;
            }
            else{
                end = Math.max(end, currEnd);
            }
        }
        newIntervals.add(new Interval(start, end));
        return newIntervals;
    }


}
