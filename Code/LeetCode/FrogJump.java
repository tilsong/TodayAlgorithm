// 풀이 링크 https://song229.notion.site/Frog-Jump-14b15151246847deb8008c41b5f40f26
class FrogJump {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;

        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                int diff = stones[i] - stones[j];
                if (diff>=n) {
                    continue;
                }
                if ((diff-1>=0 && dp[j][diff-1]) || dp[j][diff] || (diff+1<n && dp[j][diff+1])) { 
                    dp[i][diff] = true;
                }
            }
        }

        for (int j=0; j<n; j++) {
            if (dp[n-1][j]) {
                return true;
            }
        }
        return false;
    }
}
