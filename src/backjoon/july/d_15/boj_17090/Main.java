package backjoon.july.d_15.boj_17090;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// DFS를 이용하여 풀이

// BOJ 16724 피리 부는 사나이와 굉장히 유사한 문제
// 똑같은 방식으로 풀되 탈출 할 수 있는(=맵 밖으로 나갈 수 있는) 그룹의 번호를 따로 저장
// 같은 그룹인지는 깊이 우선 탐색을 통해 체크
// 이미 체크된 그룹과 합쳐지는 공간은 원래 있던 그룹으로 합쳐짐

// 그룹 체크 이후 모든 위치에서 탈출 할 수 있는 여부를 확인 후 개수 체크
public class Main {
    static int N, M;
    static int[][] map;
    static int[][] group;
    // 탈출할 수 있는 그룹을 저장
    static Set<Integer> escape = new HashSet<>();
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static int dfs(int r, int c, int cnt) {
        // 이미 그룹핑이 되어있는 공간이 나온다면 해당 그룹으로 합쳐짐
        if(group[r][c] != 0) return group[r][c];
        // 넘버링
        group[r][c] = cnt;
        int nr = r + dr[map[r][c]];
        int nc = c + dc[map[r][c]];
        // 탈출 할 수 있는 경우의 수
        if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
            escape.add(cnt);
            return group[r][c];
        }
        // 넘버링을 계속해서하다 다른 그룹이 나올 수 있으므로 해당 그룹으로 변환
        return group[r][c] = dfs(nr, nc, cnt);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        group = new int[N][M];
        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < M; j++) {
                char c = row.charAt(j);
                if(c == 'U') map[i][j] = 0;
                else if(c == 'D') map[i][j] = 1;
                else if(c == 'L') map[i][j] = 2;
                else map[i][j] = 3;
            }
        }
        int cnt = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int result = dfs(i, j, cnt);
                if(result == cnt) cnt++;
            }
        }
        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(escape.contains(group[i][j])) ans++;
            }
        }
        System.out.println(ans);
    }
}
