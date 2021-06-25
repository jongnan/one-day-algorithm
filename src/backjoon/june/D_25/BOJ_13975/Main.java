package backjoon.june.D_25.BOJ_13975;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 그리디를 이용한 풀이
//
// 우선순위 큐를 사용하여 현재 상태에서 가장 작은 두개의 파일을 합치고 다시 큐에 넣기
// 이를 큐에 1개가 남을 때까지 진행
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++) pq.offer(Long.parseLong(st.nextToken()));

            long ans = 0;
            while(pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                ans += a+b;
                pq.offer(a+b);
            }
            System.out.println(ans);
        }
    }
}
