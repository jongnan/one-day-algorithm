package backjoon.may.D_24.BOJ_14267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// DFS를 사용하여 풀이
// 칭찬 데이터를 입력할 때마다 이를 갱신 시킨다면 최대 10만 * 10만 시간이 걸림(무조건 시간초과)
// 따라서 칭찬한 데이터를 한번에 모으고 이후에 칭찬을 뿌려주면 됨
// 1번이 무조건 사장이므로 사장을 필두로 아래 직원들을 갱신 시키면 됨
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static List<Integer>[] juniors;
    public static int[] cost;

    public static void sumCompliment(int num) {
        for(int j : juniors[num]) {
            cost[j] += cost[num];
            sumCompliment(j);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        juniors = new List[n+1];
        for(int i = 1; i <= n; i++) juniors[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int supervisor = Integer.parseInt(st.nextToken());
            if(supervisor == -1) continue;
            juniors[supervisor].add(i);
        }

        cost = new int[n+1];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            cost[num] += Integer.parseInt(st.nextToken());
        }

        sumCompliment(1);

        for(int i = 1; i <= n; i++) System.out.print(cost[i] + " ");
        System.out.println();
    }
}