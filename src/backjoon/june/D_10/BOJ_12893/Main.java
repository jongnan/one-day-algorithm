package backjoon.june.D_10.BOJ_12893;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Uion-Find를 사용해서 풀이
// 전형적인 union-find 문제이지만 추가적인 것이 필요
// 바로 나의 적이라면 적들의 동료 즉, 적들의 입장에서는 동료이기 때문에 이를 추가
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] parents, enemy;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        enemy = new int[N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ra = find(a);
            int rb = find(b);
            if(ra == rb) {
                System.out.println(0);
                return;
            }
            // 만약 a의 적이 존재하지 않는다면 적으로 등록
            if(enemy[ra] == 0) enemy[ra] = rb;
            // 적이 존재한다면 적의 동료로 b를 추가
            else union(enemy[ra], rb);

            // 만약 b의 적이 존재하지 않는다면 적으로 등록
            if(enemy[rb] == 0) enemy[rb] = ra;
            // 적이 존재한다면 적의 동료로 a를 추가
            else union(enemy[rb], ra);
        }
        System.out.println(1);
    }
    public static int find(int target) {
        if(parents[target] == 0) return target;
        return parents[target] = find(parents[target]);
    }
    public static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return;
        parents[rb] = ra;
    }
}
