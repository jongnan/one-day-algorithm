package backjoon.july.d_12.boj_14567;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 위상 정렬을 이용한 풀이

// 한 학기에 들을 수 있는 과목의 수는 정해져 있지 않고 모든 과목은 항상 개설되므로
// 언제나 한계 없이 수강할 수 있다는 것
// 그리고 다음 과목을 듣기 위해서 어떠한 과목을 들어야 한다는 것은 단방향 그래프로 나타낼 수 있음
// 따라서 위상 정렬을 하고 해당 순번을 각 노드마다 저장
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 위상 정렬을 하기 위한 배열(자신으로 올 경로가 몇개인지 파악)
        int[] indegree = new int[N+1];
        List<Integer>[] graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // 그래프를 그려주는 동시에 목적지에 indegree를 늘려줌
            graph[A].add(B);
            indegree[B] += 1;
        }

        // 큐를 통해 정렬
        Deque<int[]> q = new ArrayDeque<>();
        int[] result = new int[N+1];
        // 현재 indegree가 0인 노드들을 찾아 넣어줌
        for(int i = 1; i <= N; i++) {
            // 노드번호, 정렬순서를 넣어줌
            if(indegree[i] == 0) q.offer(new int[] {i, 1});
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            // 큐에서 꺼내는 동시에 순서를 저장(나중에 한번에 출력을 위함)
            result[cur[0]] = cur[1];
            for(int n : graph[cur[0]]) {
                // 다음 노드들의 indegree를 줄여주고 다음 노드가 0이라면 큐에 넣어줌
                indegree[n]--;
                if(indegree[n] == 0) q.offer(new int[] {n, cur[1] + 1});
            }
        }

        for(int i = 1; i <= N; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}
