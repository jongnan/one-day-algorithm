package backjoon.june.D_28.BOJ_10711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// BFS를 이용한 풀이

// 모든 공간에서 8방향을 모두 체크 한다면  1000 ^ 3의 시간이 걸림
// 1000 * 1000 맵에 모래가 0,0에만 점이고 나머지가 모두 1일 때 1000의 파도가 치면 모두 같아짐
// 따라서 현재 무너질 수 있는 공간을 큐에 넣어둔 뒤 모두 부셔주면서 다음에 부셔질 모래들을 다시 넣음
// 이를 큐에 더이상 들어갈 공간이 없을 때까지 반복
public class Main {
    static int[] dr = {-1,1,0,0,-1,-1,1,1};
    static int[] dc = {0,0,-1,1,-1,1,-1,1};
    static char[][] map;
    static boolean[][] visit;
    static int H, W;
    static int checkSand(int r, int c) {
        int notSand = 0;
        for(int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
            if(map[nr][nc] == '.') notSand++;
        }
        return notSand;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visit = new boolean[H][W];
        Deque<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < H; i++) {
            String row = br.readLine();
            for(int j = 0; j < W; j++) map[i][j] = row.charAt(j);
        }

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(map[i][j] == '.' || map[i][j] == '9') continue;
                if(checkSand(i,j) >= map[i][j] - '0') {
                    q.offer(new int[] {i , j});
                    visit[i][j] = true;
                }
            }
        }

        Deque<int[]> next = new ArrayDeque<>();
        int cnt = 0;
        while(!q.isEmpty()) {
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                map[cur[0]][cur[1]] = '.';
                next.offer(cur);
            }
            while(!next.isEmpty()) {
                int[] cur = next.poll();
                for(int i = 0; i < 8; i++) {
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                    if(map[nr][nc] == '.' || map[nr][nc] == '9' || visit[nr][nc]) continue;
                    if(checkSand(nr,nc) >= map[nr][nc] - '0') {
                        q.offer(new int[] {nr, nc});
                        visit[nr][nc] = true;
                    }
                }
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}
