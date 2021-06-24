package backjoon.june.D_24.BOJ_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BFS를 이용한 풀이

// 수빈이가 움직일 수 있는 경우의 수는 3가지 앞, 뒤, 순간이동
// 따라서 너비 우선 탐색을 진행하여 최소 위치를 찾음
// 여기서 중요한 것은 순간이동은 이동 시간이 0이 들기 때문에 가장 먼저 처리를 해주어야 함
// 그렇지 않으면 다른 수가 먼저 들어가 체크가 되므로 이상한 값이 나옴
// 예시 1 2 --> 답변 0
// 이를 해결하기 위해 우선순위 큐를 사용하여 걸린 시간을 기반으로 정렬
// 혹은 그냥 큐를 사용하여 순간이동을 먼저 처리
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visit = new boolean[100001];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        pq.offer(new int[] {N , 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            visit[cur[0]] = true;
            if(cur[0] == K) {
                System.out.println(cur[1]);
                return;
            }
            if(cur[0]-1 >= 0 && !visit[cur[0]-1]) pq.offer(new int[] {cur[0]-1, cur[1]+1});
            if(cur[0]+1 <= 100000 && !visit[cur[0]+1]) pq.offer(new int[] {cur[0]+1, cur[1]+1});
            if(cur[0]*2 <= 100000 && !visit[cur[0]*2]) pq.offer(new int[] {cur[0]*2, cur[1]});
        }
    }
}
