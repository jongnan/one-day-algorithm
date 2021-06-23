package backjoon.june.D_23.BOJ_16947;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// DFS와 BFS를 사용한 풀이

// 순환을 찾기 위해서 DFS를 활용, 순환과의 최소 거리를 찾기 위해서 BFS 활용
// 순환인 것을 찾기 위해서는 boolean형 배열을 사용하여 방문 여부를 판단하기에는 어려움이 존재
// 따라서 방문 여부는 int형 배열을 사용
// 0 : 방문하지 않음, 1 : 방문, 2 : 싸이클

// 싸이클인 점들을 확인했다면, 이후 BFS를 사용
// 큐에 싸이클인 노드들만 넣고 이후 다른 노드의 거리를 갱신
public class Main {
    static List<Integer>[] graph;
    static int[] visit;
    public static int dfs(int pre, int cur) {
        // return -2 : 싸이클이 존재하지만 포함되지 않을 경우
        // return -1 : 싸이클을 찾지 못함
        // return 1 ~ N : 싸이클이 존재하고 그 안에 포함 될 경우
        if(visit[cur] != 0) return cur;
        visit[cur] = 1;
        for(int next : graph[cur]) {
            // 이전 방문했던 것은 바로 방문 X
            if(next == pre) continue;
            int result = dfs(cur, next);
            if(result == -2) return -2;
            // 계속해서 방문하다 한번 이상 방문한 곳은 자신의 노드를 반환
            if(result >= 0) {
                // 싸이클 체크
                visit[cur] = 2;
                if(result == cur) return -2;
                else return result;
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        graph = new List[N+1];
        visit = new int[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        dfs(0, 1);

        int[] dist = new int[N+1];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(visit[i] == 2) {
                dist[i] = 0;
                q.offer(i);
            }else {
                dist[i] = -1;
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : graph[cur]) {
                if(dist[next] == -1) {
                    q.offer(next);
                    dist[next] = dist[cur] + 1;
                }
            }
        }

        for(int i = 1; i <= N; i++) System.out.print(dist[i] + " ");
        System.out.println();
    }
}
