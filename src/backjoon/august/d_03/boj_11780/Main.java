package backjoon.august.d_03.boj_11780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 플로이드-워샬 알고리즘을 사용하여 풀이

// 모든 노드의 개수가 100이므로 플로이드-워샬 알고리즘을 사용해도 시간초과 X
// 또한 중간마다 최소 거리가 되는 곳에 기록(path 배열)
// 나중에 큐를 통해 이를 기록된 것을 복구
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n+1][n+1];
        int[][] path = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) for(int j = 1; j <= n; j++) path[i][j] = j;

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(graph[a][b] == 0) graph[a][b] = c;
            else {
                if(graph[a][b] > c) graph[a][b] = c;
            }
        }
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(i == j) continue;
                    if(graph[i][k] == 0 || graph[k][j] == 0) continue;
                    if(graph[i][j] == 0 || graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(graph[i][j] == 0) {
                    System.out.println(0);
                    continue;
                }
                int vertex = i;
                q.offer(vertex);
                while(path[vertex][j] != j) {
                    vertex = path[vertex][j];
                    q.offer(vertex);
                }
                q.offer(j);
                System.out.print(q.size() + " ");
                while(!q.isEmpty()) {
                    System.out.print(q.poll() + " ");
                }
                System.out.println();
            }
        }
    }
}
