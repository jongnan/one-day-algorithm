package backjoon.may.D_24.BOJ_17780;

import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static int[] dr = {0,0,-1,1};
    public static int[] dc = {1,-1,0,0};

    public static int getOppositeDir(int dir) {
        if(dir == 1) return 2;
        else if(dir == 2) return 1;
        else if(dir == 3) return 4;
        else return 3;
    }
    public static void main(String[] args) {
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[][] map = new int[N+1][N+1];
        // 앞뒤로 자유롭게 빼고 넣을 수 있는 덱 사용해서 말 쌓기
        Deque<Integer>[][] stack = new ArrayDeque[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                stack[i][j] = new ArrayDeque<>();
            }
        }

        int[][] horses = new int[K+1][3];
        for(int i = 1; i <= K; i++) {
            horses[i][0] = sc.nextInt();
            horses[i][1] = sc.nextInt();
            horses[i][2] = sc.nextInt();

            stack[horses[i][0]][horses[i][1]].push(i);
        }

        int turn = 1;
        while(turn <= 1000) {
            boolean isEnd = false;
            for(int i = 1; i <= K; i++) {
                int r = horses[i][0];
                int c = horses[i][1];
                int dir = horses[i][2];

                if(stack[r][c].getLast() != i) continue;

                int nr = r + dr[dir-1];
                int nc = c + dc[dir-1];

                // 맵을 벗어나거나 파란색
                if((nr < 1 || nr > N || nc < 1 || nc > N )
                    || map[nr][nc] == 2) {
                    int nDir = getOppositeDir(dir);
                    nr = r + dr[nDir-1];
                    nc = c + dc[nDir-1];
                    horses[i][2] = nDir;
                    if((nr < 1 || nr > N || nc < 1 || nc > N )
                        || map[nr][nc] == 2) {
                        continue;
                    }

                    while(!stack[r][c].isEmpty()) {
                        int cur;
                        if (map[nr][nc] == 0) {
                            cur = stack[r][c].pollLast();
                            stack[nr][nc].push(cur);
                        } else {
                            cur = stack[r][c].pop();
                            stack[nr][nc].push(cur);
                        }
                        horses[cur][0] = nr;
                        horses[cur][1] = nc;
                    }
               }
                // 흰색
                else if(map[nr][nc] == 0) {
                    // 다음 위치에 그대로 쌓기 및 리스트 갱신
                    while(!stack[r][c].isEmpty()) {
                        int cur = stack[r][c].pollLast();
                        stack[nr][nc].push(cur);
                        horses[cur][0] = nr;
                        horses[cur][1] = nc;
                    }
                }
                // 빨간색
                else if(map[nr][nc] == 1) {
                    // 다음 위치에 거꾸로 쌓기 및 리스트 갱신
                    while(!stack[r][c].isEmpty()) {
                        int cur = stack[r][c].pop();
                        stack[nr][nc].push(cur);
                        horses[cur][0] = nr;
                        horses[cur][1] = nc;
                    }
                }
                // 해당 위치에 말이 4개가 모였는지 확인
                if(stack[nr][nc].size() >= 4) {
                    isEnd = true;
                    break;
                }
            }
            if(isEnd) break;
            turn++;
        }

        if(turn > 1000) System.out.println(-1);
        else System.out.println(turn);
    }
}
