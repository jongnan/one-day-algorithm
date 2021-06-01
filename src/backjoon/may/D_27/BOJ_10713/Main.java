package backjoon.may.D_27.BOJ_10713;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적합으로 풀이
// 비용이 최소로 되게 하려면 해당 지하철을 몇번 탔는지 확인
// 즉 3->5 로 간다면, 3->4, 4->5 두가지 경로에 횟수를 추가해주고
// 마지막에 기차표를 샀을 때와 카드를 샀을 때 어떤것이 더 이득인지 판별
// 여기서 경로의 횟수를 측정할 때, 하나씩 추가해준다면 O(N * M)의 시간이 걸리므로 시간 초과
// 따라서 누적합으로 이를 측정
// 3 -> 5로 간다면 3에서 +1을 해주고 5에서 -1을 해줌
// 즉, 3부터는 구간이 증가한 것이고 5부터는 구간이 하락한것 ==> 기울기
// 예제 1을 보면 1 -> 3 -> 2 -> 4
// 1 -> 3  =======> 1에서 +1, 3에서 -1
// 3 -> 2  =======> 2에서 +1, 3에서 -1
// 2 -> 4  =======> 2에서 +1, 4에서 -1
// 1 2 -2 -1  ===(누적합)===> 1 3 1 0
// 기울기 값을 보고 누적합을 통해 해당 철도의 사용 빈도를 만들 수 있음
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] route = new int[M];
        for(int i = 0; i < M; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[N+1];
        for(int i = 1; i < M; i++) {
            if(route[i-1] > route[i]) {
                cnt[route[i-1]]--;
                cnt[route[i]]++;
            }else {
                cnt[route[i]]--;
                cnt[route[i-1]]++;
            }
        }

        int[][] cost = new int[N+1][3];
        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        long ans = 0, sum = 0;
        for(int i = 1; i < N; i++) {
            sum += cnt[i];
            ans += Math.min(sum * cost[i][0], sum * cost[i][1] + cost[i][2]);
        }
        System.out.println(ans);
    }
}
