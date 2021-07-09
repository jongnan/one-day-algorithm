package backjoon.july.d_09.boj_13911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘을 이용한 풀이

// 모든 집 혹은 맥도날드, 스타벅스에서 다익스트라를 사용하면 시간초과 발생
// V가 최대 10000, E가 최대 300000 10000 * log 300000 이기 때문에 될 것 같이 보이나
// 각 위치에서의 최단 거리를 구해야기 때문에 각 정점에서 다익스트라를 사용해야 함
// 따라서 시간은 10000 * 10000 * log 300000 이 걸리게 됨
// 이는 집 혹은 맥도날드, 스타벅스에도 해당

// 여기서의 방법은 거리가 0인 더미 노드를 하나 만드는 것
// 즉, 모든 맥도날드와 연결된 더미노드 하나, 모든 스타벅스와 연결된 더미노드 하나를 추가
// 추가된 더미 노드에서 다익스트라를 돌리면 2번으로 끝남
public class Main {
    public static List<int[]>[] graph;
    public static int[] mac;
    public static int[] star;
    public static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 더미노드 생성으로 V+2개를 생성
        graph = new List[V+3];
        for(int i = 0; i <= V+2; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new int[] {v, w});
            graph[v].add(new int[] {u, w});
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        mac = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            mac[i] = Integer.parseInt(st.nextToken());
            // 더미노드에 연결
            graph[V+1].add(new int[] {mac[i], 0});
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        star = new int[S];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++) {
            star[i] = Integer.parseInt(st.nextToken());
            // 더미노드에 연결
            graph[V+2].add(new int[] {star[i], 0});
        }

        // 맥도날드 더미노드에서 시작
        int[] macDist = dijkstra(V+1);
        // 스타벅스 더미노드에서 시작
        int[] starDist = dijkstra(V+2);

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= V; i++) {
            // 집이 아닌 곳은 거리가 0일 수 밖에 없으므로 제외
            if(macDist[i] == 0 || starDist[i] == 0) continue;
            // 최대거리 제한이 넘는 곳은 제외
            if(macDist[i] > x || starDist[i] > y) continue;
            ans = Math.min(ans, macDist[i] + starDist[i]);
        }
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public static int[] dijkstra(int s) {
        int[] dist = new int[V+3];
        for(int i = 0; i <= V+2; i++) dist[i] = Integer.MAX_VALUE;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        dist[s] = 0;
        pq.offer(new int[] {s, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            for(int[] n : graph[cur[0]]) {
                if(dist[n[0]] <= dist[cur[0]] + n[1]) continue;
                dist[n[0]] = dist[cur[0]] + n[1];
                pq.offer(new int[] {n[0], dist[n[0]]});
            }
        }

        return dist;
    }
}
