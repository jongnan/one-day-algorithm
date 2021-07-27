package backjoon.july.d_27.boj_2307;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라를 이용한 풀이
//
// 모든 간선을 한번씩 없애면서 다익스트라를 통해 N까지 갈 수 있느냐를 확인할 때
// 시간 복잡도의 경우, M * M * log N 으로 시간 초과가 날 수 있음
// 만약 최단 거리에 존재하지 않은 간선들을 없앨 경우, 어차피 최단 거리로 N까지 가기 때문에 똑같음
// 따라서 최단 거리 상에 있는 간선들을 없앨 경우에만 N까지의 거리를 측정하면 됨
// 따라서 시간은 최대 N * M * log N이 나옴
public class Main {
    static List<int[]>[] graph;
    static int[] path;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        path = new int[N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] {b, t});
            graph[b].add(new int[] {a, t});
        }
        // 처음에는 모든 간선에서 최단 거리를 찾아야 하므로 간선을 제외하는 것을 없음
        int originTime = dijkstra(-1, -1);
        int ans = 0;
        int idx = N;
        // 최단 거리에 있는 간선들만 확인
        while(path[idx] != 0) {
            int t =  dijkstra(path[idx], idx);
            // 중간에 한번이라도 도달하지 못하면 바로 -1 출력 후 종료
            if(t == -1) {
                System.out.println(-1);
                return;
            }
            ans = Math.max(ans, t);
            idx = path[idx];
        }
        System.out.println(ans - originTime);
    }
    // 넘어갈 간선은 a -> b
    public static int dijkstra(int a, int b) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((pre,next) -> {
            if(pre[1] == next[1]) return pre[0] - next[0];
            return pre[1] - next[1];
        });

        int[] dist = new int[N+1];
        for(int i = 0; i <= N; i++) dist[i] = Integer.MAX_VALUE;
        dist[1] = 0;
        pq.offer(new int[] {1, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(dist[cur[0]] < cur[1]) continue;
            for(int[] n : graph[cur[0]]) {
                // 넘어갈 간선 체크
                if(cur[0] == a && n[0] == b || cur[0] == b && n[0] == a) continue;
                if(dist[n[0]] > cur[1] + n[1]) {
                    dist[n[0]] = cur[1] + n[1];
                    pq.offer(new int[] {n[0], dist[n[0]]});
                    // 최단거리의 경우, 모든 간선들이 존재할 때 찾아야 하므로 조건 걸기
                    if(a == -1 && b == -1) path[n[0]] = cur[0];
                }
            }
        }

        return dist[N] == Integer.MAX_VALUE ? -1 : dist[N];
    }
}
