package backjoon.june.D_02.BOJ_16118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라를 사용하여 풀이
// 달빛 여우의 경우 일반 다익스트라를 사용하여 거리를 측정
// 달빛 늑대의 경우 전략적으로 움직여야 하므로 홀수 = 시간 / 2, 짝수 = 시간 * 2
// 여기서 문제점이 시간이 홀수일 경우에는 소수점이 생길 수 있으므로 처음부터 모든 간선의 가중치를 2를 곱하고 시작
// 달빛 늑대는 번갈아가면서 가중치를 매기기 때문에 현재 상태를 저장하고 거리 배열 또한 2중으로 늘려야 함
class Road {
    int to, weight, isFast;
    Road(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
    Road(int to, int weight, int isFast) {
        this.to = to;
        this.weight = weight;
        this.isFast = isFast;
    }
}
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static PriorityQueue<Road> pq;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Road>[] graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            // 모든 가중치는 곱하기 2
            graph[a].add(new Road(b,d * 2));
            graph[b].add(new Road(a,d * 2));
        }

        int[] fox = new int[N+1];
        int[][] wolf = new int[N+1][2];
        for(int i = 0; i <= N; i++) fox[i] = Integer.MAX_VALUE;
        for(int i = 0; i <= N; i++)
            for(int j = 0; j < 2; j++) wolf[i][j] = Integer.MAX_VALUE;

        pq = new PriorityQueue<>((a, b) -> {
            if(a.weight == b.weight) return a.to - b.to;
            return a.weight - b.weight;
        });
        dijkstraForFox(fox, graph);
        dijkstraForWolf(wolf, graph);
        int ans = 0;
        for(int i = 1; i <= N; i++) {
            if(fox[i] < Math.min(wolf[i][0], wolf[i][1])) ans++;
        }
        System.out.println(ans);
    }
    // 달빛 여우의 다익스트라(평범)
    public static void dijkstraForFox(int[] dist, List<Road>[] graph) {
        pq.clear();
        dist[1] = 0;
        pq.offer(new Road(1, 0));

        while(!pq.isEmpty()) {
            Road cur = pq.poll();
            // 주의! 현재 지점까지 왔을 때 가중치가 이미 거리 배열의 값보다 크다면 계산할 필요가 없음
            if(cur.weight > dist[cur.to]) continue;
            for(Road next : graph[cur.to]) {
                if(dist[next.to] > next.weight + cur.weight) {
                    pq.offer(new Road(next.to, next.weight + cur.weight));
                    dist[next.to] = next.weight + cur.weight;
                }
            }
        }
    }

    public static void dijkstraForWolf(int[][] dist, List<Road>[] graph) {
        pq.clear();
        // 달빛 늑대의 경우 시작점이 두개, 두개 모두 0으로 초기화 할 시에는 반례가 존재
        // 따라서 현재 지점까지 걸어온 경우만 0으로 초기화
        dist[1][0] = 0;
        pq.offer(new Road(1, 0, 0));

        while(!pq.isEmpty()) {
            Road cur = pq.poll();
            // 주의! 현재 지점까지 왔을 때 가중치가 이미 거리 배열의 값보다 크다면 계산할 필요가 없음
            if(cur.weight > dist[cur.to][cur.isFast]) continue;
            for(Road next : graph[cur.to]) {
                int nextWeight = next.weight;
                // 현재 상태가 걸어왔으므로 다음 가중치는 절반
                if(cur.isFast == 0) nextWeight /= 2;
                // 현재 상태가 달려왔으므로 다음 가중치는 두배
                else nextWeight *= 2;
                int state = 1 - cur.isFast;

                if(dist[next.to][state] > nextWeight + cur.weight) {
                    pq.offer(new Road(next.to, nextWeight+cur.weight, state));
                    dist[next.to][state] = nextWeight+cur.weight;
                }
            }
        }
    }
}
