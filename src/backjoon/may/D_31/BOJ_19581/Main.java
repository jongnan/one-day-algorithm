package backjoon.may.D_31.BOJ_19581;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// dfs를 사용하여 풀이
// 임의의 점에서 dfs를 사용하면 해당 위치에서 가장 먼 지점을 알 수 있음
// 이 먼 점에서 또 다시 한번 dfs를 사용하면 트리의 지름을 알 수 있음
// 이는 귀류법으로 증명이 가능
// 이제 양 끝점에서 서로를 제외하고 지름을 구함
// 왜? 각 정점마다 연결된 가중치가 다르기 때문에 두 끝점에서 반대편 끝점을 제외한 길이를 재고 그 중 더 큰 것을 출력(작은 것은 3번째 지름)
class Vertex {
    int to, weight;
    Vertex(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static List<Vertex>[] graph;
    public static int leaf = 0, dist = 0;
    public static void dfs(int cur, int w, boolean[] visit, int exception) {
        visit[cur] = true;
        if(dist < w) {
            dist = w;
            leaf = cur;
        }
        for(Vertex next : graph[cur]) {
            if(visit[next.to] || next.to == exception) continue;
            dfs(next.to, w + next.weight, visit, exception);
        }
    }
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[to].add(new Vertex(from, weight));
            graph[from].add(new Vertex(to, weight));
        }

        dfs(1, 0, new boolean[N+1], 0);
        int a = leaf;

        dist = 0;
        dfs(leaf, 0, new boolean[N+1], 0);
        int b = leaf;

        dist = 0;
        dfs(a, 0, new boolean[N+1], b);
        int secR1 = dist;

        dist = 0;
        dfs(b, 0, new boolean[N+1], a);
        int secR2 = dist;

        System.out.println(Math.max(secR1, secR2));
    }
}
