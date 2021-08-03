package backjoon.august.d_03.boj_5624;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수학을 이용한 풀이

// 세개를 모두 찾을 시에는 5000^3 이므로 시간초과
// a + b + c = x 라고 할 때, a + b = x - c 도 같음
// 따라서 a + b한 값을 체크해 두고 x - c가 나온다면 좋은 수가 됨
// 이 방법으로는 2개만 찾으면 되므로 5000^2 이므로 시간초과 X
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        // 음수도 체크할 수 있도록 2개를 더한 값중 최소가 -20만이기 때문에 20만을 더해 0~40만까지 체크
        boolean[] sum = new boolean[400001];
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < i; j++) {
                if(sum[A[i] - A[j] + 200000]) {
                    cnt++;
                    break;
                }
            }
            for(int j = 0; j <= i; j++) {
                sum[A[i] + A[j] + 200000] = true;
            }
        }
        System.out.println(cnt);
    }
}
