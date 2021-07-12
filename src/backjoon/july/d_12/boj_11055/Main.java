package backjoon.july.d_12.boj_11055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다이나믹 프로그래밍을 통한 풀이

// N이 최대 1000이기에 2중 포문은 돌 수 있음
// 따라서 자기 이전 수들을 돌면서 자신보다 작고 여태까지 더해진 수가 가장 큰 수를 찾고 이를 자신과 더함
// 최종 값에서 가장 큰 수를 찾고 이를 출력
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[N];
        int ans = 0;
        for(int i = 0; i < N; i++) {
            dp[i] = nums[i];
            int s = 0;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j] && dp[j] > s) s = dp[j];
            }
            dp[i] += s;
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
