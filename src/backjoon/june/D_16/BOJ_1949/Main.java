package backjoon.june.D_16.BOJ_1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// DP를 사용하여 풀이

// 해당 문제의 풀이는 2213 문제와 동일
public class Main {
    static int[] peoples;
    static List<Integer>[] graph;
    static int[][] d;
    static boolean[] visit;
    public static int dp(int cur, int state) {
        if(d[cur][state] != -1) return d[cur][state];
        if(state == 1) d[cur][state] = peoples[cur];
        else d[cur][state] = 0;
        for(int next : graph[cur]) {
            if(visit[next]) continue;
            visit[next] = true;
            if(state == 1) d[cur][state] += dp(next, 0);
            else d[cur][state] += Math.max(dp(next, 0), dp(next, 1));
            visit[next] = false;
        }
        return d[cur][state];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        peoples = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) peoples[i] = Integer.parseInt(st.nextToken());
        graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        d = new int[N+1][2];
        for(int i = 0; i <= N; i++) {
            d[i][0] = -1;
            d[i][1] = -1;
        }
        visit = new boolean[N+1];
        visit[1] = true;
        int ans = Math.max(dp(1,0), dp(1, 1));
        System.out.println(ans);
    }
}
