package backjoon.june.D_04.BOJ_9084;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DP를 이용하여 풀이
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] money = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) money[i] = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(br.readLine());

            int[] dp = new int[M+1];
            dp[0] = 1;
            for(int i = 0; i < N; i++) {
                for(int j = money[i]; j <= M; j++) {
                    dp[j] += dp[j - money[i]];
                }
            }
            System.out.println(dp[M]);
        }
    }
}
