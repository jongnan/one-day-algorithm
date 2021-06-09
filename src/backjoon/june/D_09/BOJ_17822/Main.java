package backjoon.june.D_09.BOJ_17822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BFS를 이용한 단순 구현
// 주의 할 점은 인접한 원판들을 잘 선택
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] dr = new int[] {-1,1,0,0};
    public static int[] dc = new int[] {0,0,-1,1};
    public static int N, M;
    public static int[][] disk;
    public static int[] turnDisk(int[] disk, int dir) {
        int temp;
        if(dir == 0) {
            temp = disk[disk.length-1];
            for(int i = disk.length - 1; i > 1; i--) {
                disk[i] = disk[i-1];
            }
            disk[1] = temp;
        }else {
            temp = disk[1];
            for(int i = 1; i < disk.length - 1; i++) {
                disk[i] = disk[i+1];
            }
            disk[disk.length-1] = temp;
        }
        return disk;
    }
    public static boolean deleteDisk(int r, int c) {
        boolean[][] visit = new boolean[N+1][M+1];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r,c});
        visit[r][c] = true;
        boolean isDelete = false;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr < 1 || nr > N) continue;
                if(nc < 1) nc = M;
                else if(nc > M) nc = 1;
                if(visit[nr][nc] || disk[nr][nc] != disk[r][c]) continue;
                q.offer(new int[] {nr,nc});
                visit[nr][nc] = true;
                disk[nr][nc] = 0;
                isDelete = true;
            }
        }
        if(isDelete) disk[r][c] = 0;
        return isDelete;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        disk = new int[N+1][M+1];
        for(int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= M; c++) {
                disk[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for(int i = x; i <= N; i += x) {
                for(int j = 0; j < k; j++) {
                    turnDisk(disk[i], d);
                }
            }

            boolean isDelete = false;
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= M; j++) {
                    if(disk[i][j] == 0) continue;
                    if(deleteDisk(i, j)) isDelete = true;
                }
            }
            if(!isDelete) {
                int sum = 0, cnt = 0;
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= M; j++) {
                        if(disk[i][j] == 0) continue;
                        sum += disk[i][j];
                        cnt++;
                    }
                }
                double avg = (double)sum / cnt;
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= M; j++) {
                        if(disk[i][j] == 0) continue;
                        if(disk[i][j] < avg) disk[i][j] += 1;
                        else if(disk[i][j] > avg) disk[i][j] -= 1;
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                ans += disk[i][j];
            }
        }
        System.out.println(ans);
    }
}
