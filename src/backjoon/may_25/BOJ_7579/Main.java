package backjoon.may_25.BOJ_7579;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] m = new int[N];
        int[] c = new int[N];
        for(int i = 0; i < N; i++) m[i] = sc.nextInt();
        for(int i = 0; i < N; i++) c[i] = sc.nextInt();

//        // 메모리 기준 1차원 DP
//        // dp[M] -> 메모리를 M만큼 사용했을 때 최소 비용
//        // dp[M] = min(dp[M], dp[M - m[i]] + c[i]);
//        int sum = 0;
//        for(int i = 0; i < N; i++) sum += m[i];
//        int[] dp = new int[sum + 1];
//        for(int i = 0; i <= sum; i++) dp[i] = 987654321;
//
//        dp[0] = 0;
//        dp[m[0]] = c[0];
//
//        for(int i = 1; i < N; i++) {
//            for(int j = sum; j >= m[i]; j--) {
//                dp[j] = Math.min(dp[j], dp[j - m[i]] + c[i]);
//            }
//        }
//        int ans = dp[M];
//        for(int i = M + 1; i <= sum; i++) {
//            ans = Math.min(dp[i], ans);
//        }
//        System.out.println(ans);

        // 물건과 코스트 기준 2차원 DP
        // dp[N][K] = 1 ~ N번까지 사용하여 코스트가 K일 때 최대 메모리
        // dp[N][K] = max(dp[N-1][K], dp[N-1][K - c[i]] + m[i]);
        //  해당 단계에서 앱을 비활성화 하지 않음, 비활성화 함

        int sum = 0;
        for(int i = 0; i < N; i++) sum += c[i];
        int[][] dp = new int[N][sum + 1];

        dp[0][c[0]] = m[0];
        for(int i = 1; i < N; i++) {
            for(int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if(j - c[i] < 0) continue;
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j-c[i]] + m[i]);
            }
        }
        for(int i = 0; i <= sum; i++) {
            if(dp[N-1][i] >= M) {
                System.out.println(i);
                break;
            }
        }
    }
}
