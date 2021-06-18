package backjoon.june.D_18.BOJ_17610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 브루트포스를 이용한 풀이

// 추를 물을 담는 쪽으로 놓을 수도 있고 반대쪽으로 놓을 수 있고 아에 놓지 않을 수 있음
// 따라서 3가지 경우의 수가 존재
// 현재까지 사용한 추의 번호 = i, 그에 따른 무게 = w라고 할 때
// d[i][w]라는 2차원 불리언 배열을 만들고 해당 무게 조건이 만족되면 true로 변환
// 재귀를 통해 3가지 경우의 수 모두 확인하고 이에 따른 d 배열을 변환
// 이후 추를 다 사용했을 때(k) 만들어질 수 있는 수를 1 ~ sum 까지 확인
public class Main {
    static int k;
    static int[] w;
    static boolean[][] d;
    public static void dp(int idx, int weight) {
        if(idx > k || d[idx][weight]) return;
        // idx번째 추를 사용했을 때 weight가 만들어진 것이기 때문에 true
        d[idx][weight] = true;
        // 그릇 반대편에 추를 놓았을 때
        dp(idx + 1, weight + w[idx]);
        // 그릇과 함께 추를 놓았을 때
        dp(idx + 1, Math.abs(weight - w[idx]));
        // 추를 아에 놓지 않았을 때
        dp(idx + 1, weight);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = new int[k+1];
        int sum = 0;
        for(int i = 0; i < k; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            sum += w[i];
        }
        d = new boolean[k+1][sum+1];

        dp(0, 0);
        int cnt = 0;
        for(int i = 1; i <= sum; i++) {
            if(!d[k][i]) cnt++;
        }
        System.out.println(cnt);
    }
}
