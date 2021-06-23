package backjoon.june.D_23.BOJ_5567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BFS를 이용한 풀이

// 친구와 친구의 친구를 카운팅만 하면 되는 문제
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new List[n+1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int f = 0;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] check = new boolean[n+1];
        check[1] = true;
        q.offer(new int[] {1, 0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[1] >= 2) continue;
            for(int friend : graph[cur[0]]) {
                if(check[friend]) continue;
                check[friend] = true;
                q.offer(new int[] {friend, cur[1] + 1});
                f++;
            }
        }

        System.out.println(f);
    }
}
