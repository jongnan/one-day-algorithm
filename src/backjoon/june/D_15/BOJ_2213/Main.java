package backjoon.june.D_15.BOJ_2213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// DP를 사용하여 풀이
// 인접하지 않은 노드들을 선택해 나아가면서 가중치가 가장 큰 경우의 수를 구함
// 선택하거나 선택하지 않거나 2가지 상태를 나타낼 수 있음
// 즉, K번째 노드를 선택했을 때는 1 -> d[K][1], 선택하지 않았을 때는 0 -> d[K][0]
// 여기서 주의 해야할 점은 해당 노드를 선택하지 않았다면 현재도 선택하지 않을 수 있다는 것
// 따라서 노드를 선택하면 다음 노드는 절대 선택하면 안되고,
// 현재 노드가 선택되지 않았다면 다음 노드는 선택될 수도 안될 수도 있다는 것
//
// 다음으로 해당 선택된 정점들을 출력해야 하는데 이 또한 재귀로 노드를 돌면서 확인
// 이 때, d배열을 보고 K번째 노드가 선택되어야 할지 말지를 판단
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] w;
    public static boolean[] visit;
    public static int[][] d;
    public static List<Integer>[] tree;
    public static List<Integer> selected = new ArrayList<>();
    public static int dp(int cur, int state) {
        if(d[cur][state] != -1) return d[cur][state];
        d[cur][state] = state == 1 ? w[cur] : 0;
        for(int next : tree[cur]) {
            if(visit[next]) continue;
            visit[next] = true;
            // 현 노드가 선택되었을 경우
            if(state == 1) d[cur][state] += dp(next, 0);
            // 현 노드가 선택되지 않았을 경우
            else d[cur][state] += Math.max(dp(next, 0), dp(next, 1));
            visit[next] = false;
        }
        return d[cur][state];
    }
    public static void trace(int cur, boolean isSelect) {
        if(isSelect) selected.add(cur);
        for(int next : tree[cur]) {
            if(visit[next]) continue;
            visit[next] = true;
            // 선택되지 않은 노드면 다음 d배열을 보고 넣을지 말지를 판단
            if(!isSelect) trace(next, d[next][1] > d[next][0]);
            // 선택된 노드면 다음 노드는 선택되지 않음
            else trace(next, false);
            visit[next] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = new int[n+1];
        for(int i = 1; i <= n; i++) w[i] = Integer.parseInt(st.nextToken());
        tree = new List[n+1];
        for(int i = 0; i <= n; i++) tree[i] = new ArrayList<>();
        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        d = new int[n+1][2];
        for(int i = 0; i <= n; i++) {
            d[i][0] = -1;
            d[i][1] = -1;
        }
        visit = new boolean[n+1];
        visit[1] = true;
        int ans = Math.max(dp(1, 0), dp(1, 1));
        System.out.println(ans);
        trace(1, d[1][1] > d[1][0]);
        selected.sort(Comparator.comparingInt(a -> a));
        for(int s : selected) System.out.print(s + " ");
        System.out.println();
    }
}
