package backjoon.july.d_29.boj_9466;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬을 이용한 풀이

// 사이클이 존재한다면 위상정렬 후에도 사이클이 되는 노드에는 연결된 간선이 존재
// 따라서 연결된 간선이 없는 노드들만 수를 세주면 됨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer>[] graph = new List[n+1];
            for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
            int[] ind = new int[n+1];
            for(int i = 1; i <= n; i++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i].add(num);
                ind[num]++;
            }

            Deque<Integer> q = new ArrayDeque<>();
            for(int i = 1; i <= n; i++) if(ind[i] == 0) q.offer(i);

            while(!q.isEmpty()) {
                int cur = q.poll();
                for(int nxt : graph[cur]) {
                    ind[nxt]--;
                    if(ind[nxt] == 0) q.offer(nxt);
                }
            }
            int cnt = 0;
            for(int i = 1; i <= n; i++) {
                if(ind[i] == 0) cnt++;
            }
            System.out.println(cnt);
        }
    }
}
