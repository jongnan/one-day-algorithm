package backjoon.august.d_04.boj_7511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// union-find 알고리즘을 사용하여 풀이

// 친구인지 아닌지 확인하기 위해서는 같은 그룹에 존재하는지 여부만 판단하면 됨
// 따라서 유니온 파인드 알고리즘을 사용
public class Main {
    static int[] parents;
    public static int find(int target) {
        if(target == parents[target]) return target;
        return parents[target] = find(parents[target]);
    }
    public static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return;
        parents[rb] = ra;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 1;
        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            sb.append("Scenario ").append(t++).append(":").append("\n");
            int n = Integer.parseInt(br.readLine());
            parents = new int[n];
            for(int i = 0; i < n; i++) parents[i] = i;

            int k = Integer.parseInt(br.readLine());
            for(int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int m = Integer.parseInt(br.readLine());
            for(int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if(find(u) == find(v)) sb.append(1);
                else sb.append(0);
                sb.append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
