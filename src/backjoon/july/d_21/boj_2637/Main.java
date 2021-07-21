package backjoon.july.d_21.boj_2637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상정렬을 이용한 풀이
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 위상정렬시 필요한 배열
        int[] indegree = new int[N+1];
        List<int[]>[] graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            indegree[Y]++;
            graph[X].add(new int[] {Y, K});
        }

        // 몇개가 필요한지 저장하는 공간
        int[] cnt = new int[N+1];
        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(indegree[i] == 0) q.offer(i);
        }

        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            int cur = q.poll();
            // 그래프에서 가장 마지막에 존재하는 노드
            if(graph[cur].size() == 0) ans.add(cur);
            for(int[] n : graph[cur]) {
                indegree[n[0]]--;
                // 현재 자신이 만들어져야 한다면 cnt[cur]이 0보다 큼
                // 따라서 해당 수를 연결되어 있는 수와 곱해서 전달
                cnt[n[0]] += n[1] * (cnt[cur] > 0 ? cnt[cur] : 1);
                if(indegree[n[0]] == 0) q.offer(n[0]);
            }
        }
        // 부품 번호가 작은 순으로 정렬
        ans.sort(Comparator.comparingInt(a->a));
        for(int n : ans) {
            System.out.println(n + " " + cnt[n]);
        }
    }
}
