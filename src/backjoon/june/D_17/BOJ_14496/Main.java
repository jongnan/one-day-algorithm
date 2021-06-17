package backjoon.june.D_17.BOJ_14496;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BFS와 Dijkstra를 이용한 풀이

// 변환할 수 있는 문자들을 그래프 형태로 변환
// 어떠한 문자에서 다른 문자로 변경할 때 최소 변경 횟수는 곧 최소 거리
// 최소 거리를 구하는 방법은 BFS와 Dijkstra가 존재
// 두 가지 모두 시간 안에 가능
public class Main {
    static int N;
    static List<Integer>[] graph;
    public static int bfs(int origin, int target) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N+1];
        q.offer(new int[] {origin, 0});
        visit[origin] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == target) return cur[1];
            for(int next : graph[cur[0]]) {
                if(visit[next]) continue;
                visit[next] = true;
                q.offer(new int[] {next, cur[1] + 1});
            }
        }
        return -1;
    }
    public static int dijkstra(int origin, int target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        for(int i = 0; i <= N; i++) dist[i] = Integer.MAX_VALUE;
        dist[origin] = 0;
        pq.offer(origin);

        while(!pq.isEmpty()) {
            int cur = pq.poll();
            int nd = dist[cur] + 1;
            for(int next : graph[cur]) {
                if(dist[next] > nd) {
                    dist[next] = nd;
                    pq.offer(next);
                }
            }
        }
        return dist[target] == Integer.MAX_VALUE ? -1 : dist[target];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[c].add(d);
            graph[d].add(c);
        }
        System.out.println(dijkstra(a, b));
    }
}
