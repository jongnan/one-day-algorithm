package backjoon.july.d_05.boj_11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다이나믹 프로그래밍을 사용하여 풀이

// 현재 자리 이전에 자기보다 작은 수들중에 가장 긴 수를 찾고 그 수보다 1 증가시킴
// 즉, 2중 포문을 사용 1번째 - 1 ~ N, 2번째 - 1~ 첫번째 포문의 인덱스
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];
        for(int i = 0; i < N; i++) {
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--) {
               if(arr[i] > arr[j] && dp[j] + 1 > dp[i]) dp[i] = dp[j] + 1;
            }
        }

        int ans = 0;
        for(int i = 0; i < N; i++) ans = Math.max(dp[i], ans);
        System.out.println(ans);
    }
}
