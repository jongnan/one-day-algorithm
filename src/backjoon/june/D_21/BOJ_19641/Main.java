package backjoon.june.D_21.BOJ_19641;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// DFS를 이용한 풀이
//
// 깊이 우선 탐색을 이용하여 해당 노드에 도착했을 때의 순서를 왼쪽 노드에 저장
// 모든 탐색이 끝나고 난 뒤에 순서를 오른쪽에 저장
//
// 주의 할 점은 탐색시에 번호 순대로 들어가는 것
public class Main {
    static List<Integer>[] tree;
    static int[][] nodes;
    static boolean[] visit;
    public static int dfs(int cur, int cnt) {
        visit[cur] = true;
        // 순서를 왼쪽에 저장
        nodes[cur][0] = cnt;
        int nCnt = cnt + 1;
        for(int next : tree[cur]) {
            if(visit[next]) continue;
            // 탐색하고 난 뒤의 숫자를 알기 위해
            nCnt = dfs(next, nCnt) + 1;
        }
        // 순서를 오른쪽에 저장
        nodes[cur][1] = nCnt;
        return nCnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new List[N+1];
        nodes = new int[N+1][2];
        visit = new boolean[N+1];
        for(int i = 0; i <= N; i++) tree[i] = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            while(true) {
                int next = Integer.parseInt(st.nextToken());
                if(next == -1) break;
                tree[cur].add(next);
            }
        }
        // 번호 순서대로 탐색을 하기 위해 모든 노드들에서 연결된 노드들을 정렬
        for(int i = 1; i <= N; i++) tree[i].sort(Comparator.comparingInt(a->a));
        int root = Integer.parseInt(br.readLine());
        // 루트 노드부터 시작
        dfs(root, 1);
        for(int i = 1; i <= N; i++) {
            System.out.println(i + " " + nodes[i][0] + " " + nodes[i][1]);
        }
    }
}