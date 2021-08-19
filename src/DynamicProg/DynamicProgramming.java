package DynamicProg;

public class DynamicProgramming {
    public static void main(String[] args) {

    }

    public boolean isMatchWildCard(String s, String p) {

        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[m+1][n+1];

        dp[0][0] = true;
        for(int i=1; i<=m; i++) if(p.charAt(i-1) == '*') dp[i][0] = dp[i-1][0]; else dp[i][0] = false;
        for(int i=1; i<=n; i++) dp[0][i] = false;

        for(int i=1; i<=m; i++){
            char pp = p.charAt(i-1);
            for(int j=1; j<=n; j++){
                char ss = s.charAt(j-1);
                if( ss==pp || pp == '?') dp[i][j] = dp[i-1][j-1];
                else if(pp == '*') dp[i][j] = dp[i-1][j] || dp[i][j-1];
                else dp[i][j] = false;
            }
        }
        return dp[m][n];
    }

    public boolean isMatchWildCardN(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[] dp = new boolean[n+1];
        boolean[] dp1 = new boolean[n+1];
        dp[0] = true;
        for(int i=0; i<m; i++){
            char pp = p.charAt(i);
            for(int ii=0; ii<=n; ii++) dp1[ii] = dp[ii];
            if(i==0 && pp == '*') dp[0] = true;
            else if (pp == '*' && p.charAt(i-1) == '*') dp[0] = dp1[0];
            else dp[0] = false;
            for(int j=1; j<=n; j++){
                char ss = s.charAt(j-1);
                if( ss==pp || pp == '?') dp[j] = dp1[j-1];
                else if(pp == '*') dp[j] = dp1[j] || dp[j-1];
                else dp[j] = false;
            }
        }
        return dp[n];
    }
}
