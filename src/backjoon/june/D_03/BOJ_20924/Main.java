package backjoon.june.D_03.BOJ_20924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// DFS를 사용하여 트리 탐색
// 기가 노드를 찾는 것이 핵심
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static List<int[]>[] nodes;
    public static boolean[] visit;
    public static long rootLen = 0;
    public static long branchLen = 0;
    public static int R;

    public static void dfs(int cur, long len, boolean isBranch) {
        List<int[]> curNode = nodes[cur];
        visit[cur] = true;

        // 기둥과 가지를 계속해서 갱신
        if(!isBranch) rootLen = len;
        else branchLen = Math.max(branchLen, len);

        // 기가 노드를 찾기
        // 기가 노드는 중간 노드가 될 수 있고 루트 노드가 될 수 있음
        // 기가 노드가 중간 노드일 때는 연결되어 있는 간선의 개수가 2보다 클 때
        // 기가 노드가 루트 노드일 때는 연결되어 있는 간선의 개수가 2이상일 때
        if((curNode.size() > 2 && !isBranch) ||
            (cur == R && curNode.size() >= 2)) {
            isBranch = true;
            len = 0;
        }

        for (int[] next : curNode) {
            if(visit[next[0]]) continue;
            dfs(next[0], len + next[1], isBranch);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nodes = new List[N+1];
        for(int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        visit = new boolean[N+1];

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            nodes[a].add(new int[] {b, d});
            nodes[b].add(new int[] {a, d});
        }

        dfs(R, 0, false);
        System.out.println(rootLen + " " + branchLen);
    }
}
