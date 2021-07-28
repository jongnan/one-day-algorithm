package backjoon.july.d_28.boj_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상 정렬을 통한 풀이

// 두 학생의 키를 비교한 것을 그래프로 연결
// 관련 없는 학생의 키는 어디에 들어가도 상관없으므로 위상정렬을 사용해도 무방
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        int[] con = new int[N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
            con[B]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) {
            if(con[i] == 0) q.offer(i);
        }

        StringBuilder ans = new StringBuilder();

        while(!q.isEmpty()) {
            int cur = q.poll();
            ans.append(cur);
            ans.append(" ");
            for(int nxt : graph[cur]) {
                con[nxt]--;
                if(con[nxt] == 0) q.offer(nxt);
            }
        }

        System.out.println(ans.toString());
    }
}
