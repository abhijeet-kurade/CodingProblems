package Scaler;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayOne {

    public static void main(String[] args) {
        int[][] A = {
                {1, 2, 3, 31},
                {4, 5, 6, 61},
                {7, 8, 9, 91},
                {7, 8, 9, 91}
        };
        HashSet<String> set = new HashSet<>();
        generateAllBString(set, 5, "");
        System.out.println(set);
    }

    public static void generateAllBString(HashSet<String> set, int size, String str){
        if(str.length() == size) {
            set.add(str);
            return;
        }
        generateAllBString(set, size, str+"0");
        generateAllBString(set, size, str+"1");

    }
    public String findDifferentBinaryString(String[] A) {
        int n = (int)Math.pow(2, A[0].length());
        HashSet<String> set = new HashSet<>();
        for(String str : A) set.add(str);

        for(int i=0; i<n; i++){
            String b = "";
            for(int j=0; j<A[0].length(); j++){
                int c = ((i>>j) & 1);
                b = c + b;
            }
            if(!set.contains(b)) return b;
        }
        return "";
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

    public void mulOfPrevNext(int[] A){
        int n = A.length;
        if(n <= 1) return;
        int prev = A[0];
        for(int i=0; i<n; i++){
            int next = i == n-1 ? A[n-1] : A[i+1];
            int mul = prev * next;
            prev = A[i];
            A[i] = mul;
        }
        return;
    }

    public int[][] multipleLeftRotations(int[] A, int[] B){
        int rotations = B.length;
        int n = A.length;
        int[][] rotated = new int[rotations][n];
        for(int i=0; i<rotations; i++){
            for(int j=0; j<n; j++){
                rotated[i][j] = A[(j+B[i])%n];
            }
        }
        return rotated;
    }


    public static int[][] antiDiagonal(int[][] A) {
        int n = A.length;
        int[][] m = new int[2*n-1][n];
        int i = 0, j =0;
        int d = 0, idx = 0;
        char dir = 'H';
        int chg1 = 0, chg2 =0;
        while(0<=i && i<n && 0<=j && j<n){
            m[d][idx] = A[i][j];
            System.out.print(A[i][j] + " ");
            if(dir=='H'){
                if(i==n-1 && j==0){
                    dir = 'V';
                    i = ++chg2;
                    j = n-1;
                    d += 1;
                    idx = 0;
                }
                else if(j == 0){
                    i += 0;
                    j = ++chg1;
                    d += 1;
                    idx = 0;
                }
                else {
                    i += 1;
                    j -= 1;
                    idx += 1;
                }
            }
            else{
                if(i == n-1 && j == n-1) break;
                else if(i == n-1){
                    j = n-1;
                    i = ++chg2;
                    d += 1;
                    idx = 0;
                }
                else{
                     i += 1;
                     j -= 1;
                    idx += 1;
                }

            }

        }
        return m;
    }

    public static int[] missingNumbers(int[] A){
        int n = A.length;
        if(n == 0) return A;
        int i=0;
        while(i < n){
            if(A[i] == i+1 || (A[i] <= 0 || n < A[i])  || A[A[i] - 1] == A[i]) {
                i += 1;
                continue;
            }
            int temp = A[A[i] -1];
            A[A[i] - 1] = A[i];
            A[i] = temp;
        }
        int count = 0;
        for(i=0; i<n; i++) if(A[i] != i+1) count += 1;
        int[] missing = new int[count];
        int idx = 0;
        for(i=0; i<n; i++) if(A[i] != i+1) missing[idx++] = i+1;
        for(int num : missing) System.out.print(num + " ");
        return missing;
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
