package backjoon.july.d_26.boj_20007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다익스트라를 이용한 풀이

// 떡을 한번에 하나씩만 들고 갈 수 있으며 하루의 거리는 최대 X
// 따라서 현재 위치에서부터 왕복으로 오가면서 갈 수 있는 최대한의 집까지 간 뒤 카운팅
// 만약 가장 멀리 있는집에 한번에 가지 못할 시에는 미리 체크하여 -1 출력
public class Main {
    static List<int[]>[] graph;
    static int N, M, X, Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        graph = new List[N];
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new int[] {B, C});
            graph[B].add(new int[] {A, C});
        }

        int[] dist = dijkstra();

        Arrays.sort(dist);
        if(dist[dist.length-1] * 2 > X) {
            System.out.println(-1);
            return;
        }
        int sum = 0;
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            if(sum + dist[i] * 2 < X) {
                sum += dist[i] * 2;
            }else {
                if(sum + dist[i] * 2 == X) sum = 0;
                else sum = dist[i] * 2;
                cnt++;
            }
        }
        if(sum > 0) cnt++;
        System.out.println(cnt);
    }
    public static int[] dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int[] dist = new int[N];
        for(int i = 0; i < N; i++) dist[i] = Integer.MAX_VALUE;
        dist[Y] = 0;
        pq.offer(new int[] {Y, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(int[] n : graph[cur[0]]) {
                if(dist[n[0]] > n[1] + cur[1]) {
                    dist[n[0]] = n[1] + cur[1];
                    pq.offer(new int[] {n[0], dist[n[0]]});
                }
            }
        }
        return dist;
    }
}
