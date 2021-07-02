package backjoon.july.d_02.boj_14712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백트래킹을 이용한 풀이

// 칸이 최대 25칸이므로 모든 경우의 수는 2^25
// 따라서 모든 경우의 수를 판별해도 시간 초과 X
// map에 순서대로 넴모를 올리지 않거나 올리는 형식으로 모든 경우의 수를 판단
// 하지만 넴모가 2*2 형태로 있을 때는 현재 위치에 넴모를 올리지 않음
public class Main {
    static int N, M;
    static boolean[][] map;
    static int ans = 0;
    public static void backtracking(int cnt) {
        // 모든 위치를 다 돌았으면 카운팅하고 반환
        if(cnt == N * M) {
            ans++;
            return;
        }

        // 다음 위치는 좌에서 우로 위에서 아래로
        int nr = cnt / M + 1;
        int nc = cnt % M + 1;

        // 넴모가 2*2가 될 경우에는 놓지 않음
        if(map[nr-1][nc] && map[nr][nc-1] && map[nr-1][nc-1]) {
            backtracking(cnt + 1);
        }else {
            // 해당 위치에 놓지 않을 경우
            backtracking(cnt + 1);

            // 해당 위치에 넴모를 놓을 경우
            map[nr][nc] = true;
            backtracking(cnt + 1);
            map[nr][nc] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N+1][M+1];
        backtracking(0);
        System.out.println(ans);
    }
}
