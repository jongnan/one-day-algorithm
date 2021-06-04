package backjoon.june.D_04.BOJ_20667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 어떻게 풀어야 할지 감이 잡히지 않음...
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[1001][501];
        for(int i = 0; i <= 1000; i++) for(int j = 0; j <= 500; j++) dp[i][j] = -1;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            for(int j = 0; j <= 1000; j++) {
                for(int k = 0; k <= 500; j++) {

                }
            }
        }

    }
}
