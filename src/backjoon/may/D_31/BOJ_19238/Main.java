package backjoon.may.D_31.BOJ_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// bfs를 사용한 시뮬레이션
// 현재 택시 위치에서 갈 수 있는 고객 위치를 bfs로 구하며 거리가 같을 시에는 조건에 맞춤
// 해당 위치를 갈 수 있는지 체크하고 안된다면 -1 출력 후 리턴
// 고객을 태우고 해당 고객이 갈 목적지를 bfs로 거리를 구함
// 목적지까지 갈 수 없다면 -1 출력 후 리턴
// 갈 수 있다면 택시를 해당 위치로 움직이고(연료 사) 움직인 거리 *2 만큼 재충전용
// 해당 고객은 고객 명단에서 삭제
// 이를 모든 고객이 다 될 때 까지 수행

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] dr = {-1,1,0,0};
    public static int[] dc = {0,0,-1,1};
    public static int[][] map;
    public static int N;

    public static int bfs(int[] s, int[] d) {
        boolean[][] visit = new boolean[N+1][N+1];
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {s[0],s[1],0});
        visit[s[0]][s[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == d[0] && cur[1] == d[1]) return cur[2];
            for(int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if(nr < 1 || nr > N || nc < 1 || nc > N) continue;
                if(visit[nr][nc] || map[nr][nc] == 1) continue;
                q.offer(new int[] {nr,nc,cur[2] + 1});
                visit[nr][nc] = true;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] baeck = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        List<int[]> clients = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            clients.add( new int[] {
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
            });
        }
        while(clients.size() != 0) {
            int idx = -1;
            int dist = Integer.MAX_VALUE;
            int[] start = new int[2];
            for(int i = 0; i < clients.size(); i++) {
                int[] cur = clients.get(i);
                int d = bfs(baeck, new int[] {cur[0], cur[1]});
                if(dist > d) {
                    idx = i;
                    dist = d;
                    start = new int[] {cur[0], cur[1]};
                }else if(dist == d) {
                    if(start[0] == cur[0]) {
                        if(start[1] > cur[1]) {
                            idx = i;
                            start = new int[] {cur[0], cur[1]};
                        }
                    } else if (start[0] > cur[0]) {
                        idx = i;
                        start = new int[] {cur[0], cur[1]};
                    }
                }
            }
            if(idx == -1 || dist == -1 || dist > fuel) {
                System.out.println(-1);
                return;
            }
            fuel -= dist;
            int[] curClient = clients.get(idx);
            clients.remove(idx);
            baeck = new int[] {curClient[0], curClient[1]};

            dist = bfs(baeck, new int[] {curClient[2], curClient[3]});
            if(dist == -1 || dist > fuel) {
                System.out.println(-1);
                return;
            }
            fuel -= dist;
            fuel += dist * 2;
            baeck = new int[] {curClient[2], curClient[3]};
        }

        System.out.println(fuel);
    }
}
