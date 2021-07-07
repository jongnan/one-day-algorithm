package backjoon.july.d_07.boj_20010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 최소 스패닝 트리와 다익스트라를 이용한 풀이

// 마을을 최소한의 비용으로 연결을 해야하므로 이는 최소 스패닝 트리를 이용
// 이때 Union-Find 알고리즘 사용

// 만든 트리로 다익스트라 알고리즘을 이용하여 가장 큰 비용을 구해야 함
// 만약, 벨만-포드 알고리즘을 사용시에는 1000^3 시간이 걸리므로 시간 초과
// 반면 다익스트라 알고리즘은 1000(정점) * log 1000(간선) 시간이 걸림
// 하지만 다익스트라의 경우 시작점이 다르면 거리도 다르므로 모든 정점에서 확인
// 따라서 1000 * 1000 * log(1000) 시간이 걸리므로 시간 초과는 나지 않음

// 문제에서는 최단거리가 아닌 최대거리 이므로 다익스트라를 이용시에 우선순위 큐에서 가장 큰수부터 꺼냄
public class Main {
    static int[] parents;
    static List<int[]>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new List[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        List<int[]> list = new ArrayList<>();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.add(new int[] {a, b, w});
        }

        list.sort((a, b) -> {
            if(a[2] == b[2]) return a[0] - b[0];
            return a[2] - b[2];
        });

        parents = new int[N];
        for(int i = 0; i < N; i++) parents[i] = i;

        long totWeight = 0;
        for(int i = 0; i < K; i++) {
            int[] cur = list.get(i);
            if(union(cur[0], cur[1])) {
                totWeight += cur[2];
                graph[cur[0]].add(new int[] {cur[0], cur[1], cur[2]});
                graph[cur[1]].add(new int[] {cur[1], cur[0], cur[2]});
            }
        }

        System.out.println(totWeight);

        int ans = 0;
        for(int i = 0; i < N; i++) {
            ans = Math.max(ans, dijkstra(i, N));
        }
        System.out.println(ans);
    }
    public static int dijkstra(int s, int N) {
        int[] dist = new int[N];
        for(int i = 0; i < N; i++) dist[i] = Integer.MAX_VALUE;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[2] - a[2]);
        dist[s] = 0;
        for(int[] n : graph[s]) pq.offer(n);

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(dist[cur[0]] + cur[2] < dist[cur[1]]) {
                dist[cur[1]] = dist[cur[0]] + cur[2];
                for(int[] n : graph[cur[1]]) pq.offer(n);
            }
        }

        int max = 0;
        for(int i = 0; i < N; i++) max = Math.max(max, dist[i]);
        return max;
    }

    public static int find(int target) {
        if(parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }
    public static boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return false;
        parents[rb] = ra;
        return true;
    }
}
