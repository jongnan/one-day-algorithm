package backjoon.june.D_08.BOJ_14699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// DP를 사용하여 풀이
// 한번 올라간 위치는 그대로이기 때문에 해당 위치를 저장
// 가장 중요한 것은 단방향 그래프이며, 높이가 높은곳으로 이동(같으면 이동 X)
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] d;
    public static List<Integer>[] graph;
    public static int dp(int cur) {
        if(d[cur] != 0) return d[cur];
        for(int i = 0; i < graph[cur].size(); i++) {
            d[cur] = Math.max(d[cur], dp(graph[cur].get(i)));
        }
        return ++d[cur];
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] height = new int[N+1];
        for(int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(height[a] > height[b]) graph[b].add(a);
            else if(height[a] < height[b]) graph[a].add(b);
        }
        d = new int[N+1];
        for(int i = 1; i <= N; i++) System.out.println(dp(i));
    }
}
