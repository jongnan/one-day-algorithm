package backjoon.july.d_07.boj_11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 우선순위 큐를 이용한 풀이

// 자바에서 우선순위 큐가 힙 구조이므로 사용
// 대신 절대값을 비교해야 하기 때문에 배열 형태로 {기존값, 절대값} 형태로 저장
// 비교는 절대값을 기준으로 오름차순, 이후 기존값을 기준으로 오름차순
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input == 0) {
                if(pq.isEmpty()) sb.append(0);
                else sb.append(pq.poll()[0]);
                sb.append("\n");
                continue;
            }
            pq.offer(new int [] {input, Math.abs(input)});
        }
        System.out.println(sb.toString());
    }
}
