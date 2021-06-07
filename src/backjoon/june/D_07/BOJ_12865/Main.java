package backjoon.june.D_07.BOJ_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 냅색을 이용한 풀이
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N+1][K+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            for(int j = 0; j <= K; j++) {
                dp[i][j] = dp[i-1][j];
                if(j - W >= 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-W] + V);
            }
        }
        System.out.println(dp[N][K]);
    }
}
