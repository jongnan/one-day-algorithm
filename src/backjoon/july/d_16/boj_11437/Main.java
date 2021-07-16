package backjoon.july.d_16.boj_11437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// LCA(Lowest Common Ancestor) 최소 공통 조상 알고리즘을 통해 풀이

// 트리의 노드마다 깊이와 부모를 저장
// 비교할 두개의 노드를 같은 높이까지 끌어 올려주고 비교
// 같은 노드라면 출력, 아니라면 같은 노드가 나올때까지 두개 모두 부모를 비교
public class Main {
    static int[] depth;
    static List<Integer>[] tree;
    static int[] parent;
    // 깊이 우선 탐색을 통해 부모와 깊이를 저장
    public static void dfs(int cur, int dep) {
        depth[cur] = dep;
        for(int next : tree[cur]) {
            if(depth[next] != 0) continue;
            parent[next] = cur;
            dfs(next, dep+1);
        }
    }
    public static int lca(int a, int b) {
        int depA = depth[a];
        int depB = depth[b];

        // 같은 높이까지 끌어올림
        while(depA != depB) {
            if(depA < depB) {
                b = parent[b];
                depB = depth[b];
            }else {
                a = parent[a];
                depA = depth[a];
            }
        }

        // 두개 노드가 같지 않다면 부모를 비교
        while(a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new List[N+1];
        for(int i = 0; i <= N; i++) tree[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        depth = new int[N+1];
        parent = new int[N+1];

        dfs(1, 1);

        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(lca(a, b));
        }
    }
}
