package backjoon.july.d_14.boj_16724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS를 이용한 풀이

// 1000 * 1000의 맵을 한번만 돌면 되기 때문에 시간 초과 X
// 깊이 우선 탐색을 통해 끝 위치에 세이프 존을 건설하면 됨
// 끝 위치가 자신과 같을 때만 증가 시킴

// 전    후
// D L  1 1
// R U  1 1
// U U  2 3
// 과 같이 주어질 때 서칭이 시작할 때부터 시작한다면 총 3개의 그룹으로 나누어짐

// 전    후
// D L  1 1
// R U  1 1
// U U  1 1
// 하지만 하나의 그룹으로 합칠 수 있기 때문에 1개의 그룹만 나와야 함
// 따라서 끝 위치가 현재 그룹과 동일 할 때만 그룹 수를 높힘
public class Main {
    static int N,M;
    static int[][] map;
    static int[][] visit;
    static int ans = 1;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static int dfs(int r, int c, int cnt) {
        if(visit[r][c] != 0) return visit[r][c];
        visit[r][c] = cnt;
        return visit[r][c] = dfs(r + dr[map[r][c]], c + dc[map[r][c]], cnt);
    }
    public static int getDirNum(char c) {
        if(c == 'U') return 0;
        if(c == 'D') return 1;
        if(c == 'L') return 2;
        return 3;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visit = new int[N][M];
        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = getDirNum(row.charAt(j));
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(visit[i][j] != 0) continue;
                int cnt = dfs(i, j, ans);
                if(cnt == ans) ans++;
            }
        }

        System.out.println(ans - 1);
    }
}
