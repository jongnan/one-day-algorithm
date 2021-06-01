package backjoon.may.D_19;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BOJ_1926 {
    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    private static int bfs(int r, int c) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r,c});
        visit[r][c] = true;
        int area = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(map[nr][nc] == 0 || visit[nr][nc]) continue;
                q.offer(new int[] {nr, nc});
                visit[nr][nc] = true;
                area++;
            }
        }
        return area;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visit = new boolean[n][m];
        for(int r = 0; r < n; r++) for(int c = 0; c < m; c++) map[r][c] = sc.nextInt();

        int maxArea = 0, cnt = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                if(map[r][c] == 0 || visit[r][c]) continue;
                maxArea = Math.max(maxArea, bfs(r,c));
                cnt++;
            }
        }

        System.out.println(cnt);
        System.out.println(maxArea);
    }
}
