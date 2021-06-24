package backjoon.june.D_24.BOJ_14226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// BFS를 이용한 풀이

// 영선이가 할 수 있는 경우의 수는 클립보드에 저장, 붙여넣기, 삭제로 총 3가지
// S가 굉장히 작기 때문에 BFS를 사용 가능

// 여기서 가장 중요한 것은 dist[][] 를 설정하는 것
// dist[현재 있는 이모티콘][클립보드에 존재하는 이모티콘]으로 설정
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
        Deque<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[S+1][S+1];
        q.offer(new int[] {1, 0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == S) {
                System.out.println(dist[cur[0]][cur[1]]);
                return;
            }

            // 복사
            if(dist[cur[0]][cur[0]] == 0) {
                q.offer(new int[] {cur[0], cur[0]});
                dist[cur[0]][cur[0]] = dist[cur[0]][cur[1]] + 1;
            }

            // 붙여넣기
            if(cur[0] + cur[1] <= S && dist[cur[0] + cur[1]][cur[1]] == 0) {
                q.offer(new int[] {cur[0] + cur[1], cur[1]});
                dist[cur[0] + cur[1]][cur[1]] = dist[cur[0]][cur[1]] + 1;
            }

            // 삭제
            if(cur[0] - 1 >= 0 && dist[cur[0]-1][cur[1]] == 0) {
                q.offer(new int[] {cur[0] - 1, cur[1]});
                dist[cur[0] - 1][cur[1]] = dist[cur[0]][cur[1]] + 1;
            }
        }
    }
}
