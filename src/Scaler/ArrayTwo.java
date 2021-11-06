package Scaler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class ArrayTwo {
    public static void main(String[] args) {
        int[] arr = {5, 17, 100, 11};
        minimumSwaps(arr, 20);
    }

    public static void printArr(int[] arr){
        for(int num : arr) System.out.print(num +  " ");
        System.out.println();
    }

    public static int[][] generateSpiralMatrix(int A) {
        int n = A;
        int[][] m = new int[n][n];
        int start = 0;
        int end = n-1;
        int number = 1;
        while(start <= end){
            for(int i=start; i<=end; i++) m[start][i] = number++;
            for(int i=start+1; i<=end; i++) m[i][end] = number++;
            for(int i=end-1; i>=start; i--) if(start != end) m[end][i] = number++;
            for(int i=end-1; i>start; i--) if(start != end) m[i][start] = number++;
            start += 1;
            end -= 1;
        }
        return m;
    }

    static class Pair{
        int num1;
        int num2;
        public Pair(int num1, int num2){
            this.num1 = num1;
            this.num2 = num2;
        }
    }

    public static int maximumGap(final int[] A) {
        int n = A.length;
        ArrayList<Pair> arr = new ArrayList<>();
        for(int i=0; i<n; i++)arr.add(new Pair(A[i], i));
        Collections.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.num1 - o2.num1;
            }
        });

        int min = Integer.MAX_VALUE;
        int ans = Integer.MIN_VALUE;
        for(Pair pair : arr){
            ans = Math.max(ans, pair.num2 - min);
            min = Math.min(min, pair.num2);
        }
        return ans;
    }

    public static int minimumSwaps(int[] A, int B){

        int n = A.length;
        int count = 0;
        for(int i=0; i<n; i++){
            if(A[i] <= B) count += 1;
        }
        if(count <= 1) return 0;
        int min_swaps = Integer.MAX_VALUE;
        int cnt1=0, cnt2=0;
        for(int i=0; i<n; i++){
            if(i<count-1){
                if(A[i] <= B) cnt2 += 1;
                continue;
            }
            if(A[i] <= B) cnt2 += 1;
            System.out.println(cnt2 +" "+cnt1);
            int ones = cnt2 - cnt1;
            min_swaps = Math.min(min_swaps, count - ones);
            if(A[i-count+1] <= B) cnt1 += 1;
        }
        System.out.println(min_swaps);
        return min_swaps;
    }

    public static int minimumSwapsToSort(int[] A){
        int n = A.length;
        int i = 0;
        int swaps = 0;
        while(i < n){
            if(A[i] == i){
                i += 1;
                continue;
            }
            int temp = A[A[i]];
            A[A[i]] = A[i];
            A[i] = temp;
            swaps += 1;
        }
        return swaps;
    }
    public String[] fizzBuzz(int A) {
        String[] fb = new String[A];

        for(int i=1; i<=A; i++){
            String output = "";
            if(i%3 == 0) output += "Fizz";
            if(i%5 == 0) output += "Buzz";
            if(output.equals("")) output = String.valueOf(i);
            fb[i] = output;
        }
        return fb;
    }
    public int goodPair(int[] A, int B) {
        HashSet<Integer> set = new HashSet<>();
        for(int num : A){
            if(set.contains(B-num)) return 1;
            set.add(num);
        }
        return 0;
    }

}

class SubMatrixSums{
    private final int[][] arr;
    private final long[][] prefix;
    private final long M = 1000000007;
    public SubMatrixSums(int[][] arr){
        this.arr = arr;
        int height = arr.length;
        int width = arr[0].length;
        this.prefix = new long[height][width];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                long top = i>0 ? this.prefix[i-1][j] : 0;
                long left = j>0 ? this.prefix[i][j-1] : 0;
                long top_left = i>0 && j>0 ? this.prefix[i-1][j-1] : 0;
                this.prefix[i][j] = this.arr[i][j] + top + left - top_left;
                this.prefix[i][j] %= M;
                //System.out.print(this.prefix[i][j] + " ");
            }
            //System.out.println();
        }
    }

    public int[] sumsSubMatrix(int[] B, int[] C, int[] D, int[] E){
        int len = B.length;
        int[] sums = new int[len];
        for(int i=0; i<len; i++){
            int x1 = B[i] - 1;
            int y1 = C[i] - 1;
            int x2 = D[i] - 1;
            int y2 = E[i] - 1;

            long sum = this.prefix[x2][y2] -
                    ( (x1 <= 0 ? 0 : this.prefix[x1-1][y2]) + (y1 <= 0 ? 0 : this.prefix[x2][y1-1]) ) +
                    (x1==0 || y1==0 ? 0 : this.prefix[x1-1][y1-1]) ;
            System.out.println(sum);
            while(sum < 0){
                sum += M;
            }
            sum %= M;
            sums[i] = (int)sum;
        }
        return sums;
    }

    public int sumsSubMatrix(int x1, int y1, int x2, int y2){
        long sum = this.prefix[x2][y2] -
                ( (x1 <= 0 ? 0 : this.prefix[x1-1][y2]) + (y1 <= 0 ? 0 : this.prefix[x2][y1-1]) ) +
                (x1==0 || y1==0 ? 0 : this.prefix[x1-1][y1-1]) ;
        System.out.println(sum);
        while(sum < 0){
            sum += M;
        }
        sum %= M;
        int sums = (int)sum;
        return sums;
    }
}
