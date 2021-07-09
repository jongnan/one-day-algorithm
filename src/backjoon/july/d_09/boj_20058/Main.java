package backjoon.july.d_09.boj_20058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 시뮬레이션을 통해 풀이

// 배열을 해당 크기만큼 회전시키고 얼음이 녹는지를 확인
// 확인시 주의할 점은 주변에 얼음이 있는지 확인과 동시에 녹이는 것이 아닌 체크만 해야함
// 그리고 마지막에 한번에 녹여야 함
// 한번에 녹이지 않으면 순간순간마다 달라짐
// 이후 가장 큰 얼음 크기는 BFS를 통해 구함
public class Main {
    public static int[][] map;
    public static int[] dr = {-1,1,0,0};
    public static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int len = (1 << N);

        map = new int[len][len];
        for(int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < len; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++) {
            int q = Integer.parseInt(st.nextToken());
            fireStorm(1<<q);
        }

        int sum = 0;
        for(int r = 0; r < len; r++) {
            for(int c = 0; c < len; c++) {
                sum += map[r][c];
            }
        }
        System.out.println(sum);

        boolean[][] visit = new boolean[len][len];
        int maxCnt = 0;
        for(int r = 0; r < len; r++) {
            for(int c = 0;  c < len; c++) {
                if(visit[r][c] || map[r][c] == 0) continue;
                maxCnt = Math.max(maxCnt, getSize(r, c, visit));
            }
        }
        System.out.println(maxCnt);
    }
    public static void fireStorm(int l) {
        int N = map.length;

        for(int r = 0; r < N; r += l) {
            for(int c = 0; c < N; c += l) {
                rotate(r, c, l);
            }
        }

        boolean[][] isMelt = new boolean[N][N];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(map[r][c] == 0) continue;
                int cnt = 0;
                for(int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                    if(map[nr][nc] > 0) cnt++;
                }
                if(cnt < 3) isMelt[r][c] = true;
            }
        }
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < N; c++) {
                if(isMelt[r][c]) map[r][c] -= 1;
            }
        }
    }
    public static void rotate(int r, int c, int l) {
        int[][] temp = new int[l][l];
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < l; j++) {
                temp[i][j] = map[r+l-1-j][c+i];
            }
        }
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < l; j++) {
                map[r+i][c+j] = temp[i][j];
            }
        }
    }
    public static int getSize(int r, int c, boolean[][] visit) {
        int N = map.length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c});
        visit[r][c] = true;
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(visit[nr][nc] || map[nr][nc] == 0) continue;
                q.offer(new int[] {nr, nc});
                visit[nr][nc] = true;
                cnt++;
            }
        }
        return cnt;
    }
}
