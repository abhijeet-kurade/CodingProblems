package Scaler;

public class ArrayTwo {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0}
        };
        int[] A = {1};
        int[] B = {1};
        int[] C = {1};
        int[] D = {1};
        SubMatrixSums s = new SubMatrixSums(arr);
        s.sumsSubMatrix(A, B, C, D);
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
