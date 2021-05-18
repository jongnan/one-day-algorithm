package backjoon.may_18;

import java.util.Scanner;

public class BOJ_5557 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for(int i = 0; i < N; i++) nums[i] = sc.nextInt();

        long[][] dp = new long[N][21];
        dp[0][nums[0]] = 1;
        for(int i = 1; i < N - 1; i++) {
            for(int j = 0; j <= 20; j++) {
                if(dp[i-1][j] == 0) continue;
                if(j + nums[i] <= 20) dp[i][j + nums[i]] += dp[i-1][j];
                if(j - nums[i] >= 0) dp[i][j - nums[i]] += dp[i-1][j];
            }
        }
        System.out.println(dp[N-2][nums[N-1]]);
    }
}