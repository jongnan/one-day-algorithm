package backjoon.may.D_26.BOJ_3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상 정렬을 통해 풀이
// 만약 사이클이 존재한다면 순위를 정할 수 없으므로 IMPOSSIBLE
// 또, 정렬을 하던 도중 큐에 2개 이상의 팀이 존재한다면 그 둘의 등수는 정확히 측정할 수 없으므로 ?

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] graph = new int[n+1][n+1];
            int[] indegree = new int[n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] seq = new int[n];
            for(int i = 0; i < n; i++) seq[i] = Integer.parseInt(st.nextToken());

            // 입력받은 등수를 기반으로 그래프와 indegree 배열을 작성
            // indegree 배열은 해당 팀에 연결된 간선의 수를 의미
            for(int i = 0; i < n; i++) {
                for(int j = i+1; j < n; j++) {
                    graph[seq[i]][seq[j]] = 1;
                    indegree[seq[j]]++;
                }
            }

            // m개의 바뀐 등수를 입력 받으면서 기존 그래프와 indegree 배열을 변경
            int m = Integer.parseInt(br.readLine());
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 입력받은 순서는 상관없고 기존 방향에서 반대방향으로만 변경하면 됨
                // a -> b 로 존재한다면
                if(graph[a][b] == 1) {
                    graph[a][b] = 0;
                    graph[b][a] = 1;
                    indegree[a]++;
                    indegree[b]--;
                }
                // b -> a 로 존재한다면
                else {
                    graph[b][a] = 0;
                    graph[a][b] = 1;
                    indegree[b]++;
                    indegree[a]--;
                }
            }

            Deque<Integer> q = new ArrayDeque<>();
            List<Integer> result = new ArrayList<>();
            boolean isUncertain = false;
            boolean isImpossible = false;
            for(int i = 1; i <= n; i++) if(indegree[i] == 0) q.offer(i);
            if(q.isEmpty()) isImpossible = true;

            while(!q.isEmpty()) {
                // 두 팀이 한번에 들어와 순서를 정확하게 측정할 수 없음
                if(q.size() > 1) {
                    isUncertain = true;
                    break;
                }

                int cur = q.poll();
                result.add(cur);
                for(int i = 1; i <= n; i++) {
                    if(graph[cur][i] == 1) {
                        indegree[i]--;
                        if(indegree[i] == 0) q.add(i);
                    }
                }
                // 모든 정점을 방문하지 않았는데, indegree 배열에 0이 없다면
                // 그래프 안에 사이클이 존재
                if(result.size() != n && q.isEmpty()) {
                    isImpossible = true;
                    break;
                }
            }
            if(isUncertain) {
                System.out.println("?");
                continue;
            }
            if(isImpossible) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for(int num : result) sb.append(num).append(" ");
            System.out.println(sb.toString());
        }
    }
}
