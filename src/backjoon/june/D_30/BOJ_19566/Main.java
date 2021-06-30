package backjoon.june.D_30.BOJ_19566;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 누적합을 사용한 풀이

// [l ~ r] / (r-l) = K 이므로 이는 곧 [l ~ r] = K (r-l)
// 이에 누적합을 사용하면 [1~r] - [1~l] = Kr - Kl
// 따라서 [1~r] - Kr = [1~l] - Kl
// 이는 즉 1 ~ N 까지 더한 값에 평균 * N 을 뺀 값들 중에 같은 수가 몇개 있는지 파악
// 예제 2를 보면 [-1 0 0 -2 0 0 -1 0 -2 -2] 가 나오게 된다.
// 여기서 0을 보게 되면 맨 앞에 값이 l이 될 때 r이 될 수 있는 경우의 수는 4가지
// 그 다음 0에서는 r이 될 수 있는 경우의 수는 3가지
// 즉, 0 : 5개 -> 4+3+2+1 가지의 경우의 수가 존재
// 이를 다 더해주고 0의 개수(자기 자체가 답)도 파악하여 덧셈
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Map<Long, Integer> cnt = new HashMap<>();
        long sum = 0;
        for(int i = 0; i < N; i++) {
            sum += Integer.parseInt(st.nextToken());
            long a = sum - (long)K * (i+1);
            if(cnt.containsKey(a)) cnt.put(a, cnt.get(a) + 1);
            else cnt.put(a, 1);
        }
        long ans = 0;
        for(Map.Entry<Long, Integer> e : cnt.entrySet()) {
            long s = 0;
            long n = e.getValue() - 1;
            while(n > 0) s += n--;
            ans += s;
            if(e.getKey() == 0) ans += e.getValue();
        }
        System.out.println(ans);
    }
}
