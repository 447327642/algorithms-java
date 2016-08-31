// Interleaving String
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) return false;
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        
        if (l1 + l2 != l3) return false;
        
        // dp[i][j] = whether s3[i+j] is composed of s1[0,i] and s2[0,j]
        boolean[][] dp = new boolean[l1+1][l2+1];
        
        dp[0][0] = true;
        
        for (int i = 1; i <= l1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int j = 1; j <= l2; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[l1][l2];
    }
}