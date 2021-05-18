package backjoon.may_18;

import java.util.Scanner;

public class BOJ_17398 {
    static int[] parents;

    // 그룹을 분할하는 방법을 떠올리지 못함
    // 검색한 결과 Union-Find를 활용하여 풀이
    // 즉, 순서대로 분할한다는 것을 역순으로 생각해 본다면 두개의 그룹을 하나로 엮는것과 동일
    // 따라서 분할 역순으로 Union-Find를 사용하여 그룹을 묶어가면서 계산
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int Q = sc.nextInt();

        int[][] lines = new int[M][2];
        for(int i = 0; i < M; i++) lines[i] = new int[]{sc.nextInt(), sc.nextInt()};

        int[] seq = new int[Q];
        boolean[] checked = new boolean[M];
        for(int i = 0; i < Q; i++) {
            seq[i] = sc.nextInt() - 1;
            checked[seq[i]] = true;
        }

        parents = new int[N+1];
        for(int i = 0; i <= N; i++) parents[i] = -1;

        for(int i = 0; i < M; i++) {
            if(checked[i]) continue;
            merge(lines[i][0], lines[i][1]);
        }

        long ans = 0;
        for(int i = Q-1; i >= 0; i--) {
            for(int j = 1; j <= N; j++) System.out.print(parents[j] + " ");
            System.out.println();
            int ra = find(lines[seq[i]][0]);
            int rb = find(lines[seq[i]][1]);
            if(ra == rb) continue;
            // 음수 * 음수 이므로 값에 절대값을 씌우지 않아도 됨
            ans += (long)parents[ra] * parents[rb];
            merge(ra, rb);
        }
        System.out.println(ans);
    }

    private static int find(int target) {
        if(parents[target] < 0) return target;
        // Union-Find 최적화
        return parents[target] = find(parents[target]);
    }

    private static void merge(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return;
        // 만약 두개의 그룹이 하나로 묶인다면 상위 그룹은 여태 묶인 그룹들의 수를 저장
        // 하위 그룹은 상위 그룹의 번호를 나타냄
        // 이는 parents의 값들이 -1로 초기화 되어있고 절대값이 1이기 때문에 가능
        // 하지만 주의해야 할 점은 음수 + 음수 = 음수 이므로 개수는 음수로 저장
        parents[ra] += parents[rb];
        parents[rb] = ra;
    }
}