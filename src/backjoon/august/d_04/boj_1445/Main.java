package backjoon.august.d_04.boj_1445;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라를 이용한 풀이(우선순위 큐)

// 우선순위 큐에 현재까지 지나온 {쓰래기와 쓰래기 근처, 행, 열} 저장
// 이때, 쓰래기 수가 가장 적은 수, 그다음으로 쓰래기 근처가 적은 수로 정렬
// 꽃이 있는 위치에 도달할 때까지 반복
class Node {
    // 지나온 쓰래기 수, 지나온 쓰래기 근처 수, 행, 열
    int g, ng, r, c;
    Node(int g, int ng, int r, int c) {
        this.g = g;
        this.ng = ng;
        this.r = r;
        this.c = c;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        boolean[][] visit = new boolean[N][M];

        int sr = 0, sc = 0, er = 0, ec = 0;
        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }else if(map[i][j] == 'F') {
                    er = i;
                    ec = j;
                }
            }
        }

        int[] dr = new int[] {-1,1,0,0};
        int[] dc = new int[] {0,0,-1,1};

        // 우선순위 큐, 지나온 쓰래기 순으로 오름차순, 같다면 지나온 쓰래기 근처 수 오름차순
        PriorityQueue<Node> pq = new PriorityQueue<>((pre, nxt) -> {
            if(pre.g == nxt.g) return pre.ng - nxt.ng;
            return  pre.g - nxt.g;
        });
        pq.offer(new Node(0,0, sr, sc));
        visit[sr][sc] = true;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            // 다음 방향 체크
            for(int dir = 0; dir < 4; dir++) {
                int nr = cur.r + dr[dir];
                int nc = cur.c + dc[dir];

                // 다음 위치에 꽃이 있으면 끝
                if(nr == er && nc == ec) {
                    System.out.println(cur.g + " " + cur.ng);
                    return;
                }

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(visit[nr][nc]) continue;

                if(map[nr][nc] == 'g') pq.offer(new Node(cur.g+1, cur.ng, nr, nc));
                else if(map[nr][nc] == '.') {
                    // 그냥 길일 때 주변에 쓰래기가 있는지 체크
                    boolean isNear = false;
                    for(int k = 0; k < 4; k++) {
                        int nnr = nr + dr[k];
                        int nnc = nc + dc[k];
                        if(nnr < 0 || nnr >= N || nnc < 0 || nnc >= M) continue;
                        if(map[nnr][nnc] == 'g') {
                            isNear = true;
                            break;
                        }
                    }
                    if(isNear) pq.offer(new Node(cur.g, cur.ng+1, nr, nc));
                    else pq.offer(new Node(cur.g, cur.ng, nr, nc));
                }
                visit[nr][nc] = true;
            }
        }
    }
}
