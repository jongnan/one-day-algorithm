package backjoon.may.D_25.BOJ_10986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간 합을 이용한 풀이
// [2~4] = [1~4] - [1~1]
// 이 구간의 합이 M으로 나눠 떨어져야 하므로
// [2~4] % M = ([1~4] - [1~1]) % M ===(분배법칙)==> [1~4]%M - [1~1]%M = 0
// 입력을 예시로 보면 [1~4] = 7, [1~1] = 1 이들을 M으로 나눈 나머지는 각각 1이다.
// 즉, 나머지 연산 후 같은 값일 때 해당 구간은 M으로 나누어 떨어진다는 소리
// 따라서 모든 구간의 합에 나머지 연산자를 해보면 1 0 0 1 0 이 나오게 된다.
// 여기서 0이 나온 구간은 차를 구하지 않아도 0이기 때문에 바로 카운팅 해준다.
// 이후 같은 값을 가진 두개의 구간의 조합을 구하여 카운팅 해준다.
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] remaindersCnt = new long[M];
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum = (sum + Integer.parseInt(st.nextToken())) % M;
            // 해당 나머지 값이 몇개가 나오는지 체크
            remaindersCnt[sum]++;
        }

        // 0인 값은 바로 카운팅
        long ans = remaindersCnt[0];
        // 나머지 값이 같은 수의 조합 카운팅
        for(int i = 0; i < M; i++) {
            ans += (remaindersCnt[i] * (remaindersCnt[i] - 1)) / 2;
        }
        System.out.println(ans);
    }
}
