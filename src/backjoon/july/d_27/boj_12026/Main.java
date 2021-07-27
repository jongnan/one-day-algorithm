package backjoon.july.d_27.boj_12026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 다이나믹 프로그래밍을 통한 풀이

// N이 최대 1000이기 때문에 2중 포문을 돌아도 시간초과가 나지 않음
// 따라서 i 일때 다음으로 갈 수 있는 i+1 ~ N 까지 돌며 거리 갱신(i가 도달하지 않은 곳이라면 넘김)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] blocks = br.readLine().toCharArray();
        int[] dp = new int[N];
        for(int i = 0; i < N; i++) dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;
        for(int i = 0; i < N - 1; i++) {
            // 도달하지 않은 곳은 시작 X
            if(dp[i] == Integer.MAX_VALUE) continue;
            for(int j = i + 1; j < N; j++) {
                int dist = j - i;
                // 다음 위치로 갈 수 있는지 확인
                if((blocks[i] == 'B' && blocks[j] == 'O')
                || (blocks[i] == 'O' && blocks[j] == 'J')
                || (blocks[i] == 'J' && blocks[j] == 'B')) {
                    // 갈 수 있다면 거리 체크하여 갱신
                    dp[j] = Math.min(dp[j], dp[i] + dist * dist);
                }
            }
        }
        if(dp[N-1] == Integer.MAX_VALUE) dp[N-1] = -1;
        System.out.println(dp[N-1]);
    }
}
