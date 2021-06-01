package backjoon.june.D_01.BOJ_12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// BFS를 사용하여 풀이
// 중복된 경우도 존재하므로 해당 위치에 여러번 도달 할 수 있음
// 하지만 이미 도달한 공간에 도착했던 시간보다 더 늦는다면 의미가 없으므로 해당 경우에는 제외
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        int[] visit = new int[100001];
        for(int i = 0; i <= 100000; i++) visit[i] = Integer.MAX_VALUE;

        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {N, 0});
        visit[N] = 0;
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            // 동생 위치에 도달 했을 때
            if(cur[0] == K) {
                // 새롭게 도달한 시간이 더욱 작을 때는 카운트 초기화
                if(visit[K] > cur[1]) {
                    visit[K] = cur[1];
                    cnt = 1;
                }
                // 도달한 시간이 같을 때는 카운트 +1
                else if(visit[K] == cur[1]) cnt++;
                continue;
            }

            // 한 칸 이전으로 움직임
            int pre = cur[0] - 1;
            if(pre >= 0 && visit[pre] >= cur[1] + 1) {
                visit[pre] = cur[1] + 1;
                q.offer(new int[] {pre, cur[1]+1});
            }

            // 한 칸 다음으로 움직임
            int next = cur[0] + 1;
            if(next <= 100000 && visit[next] >= cur[1] + 1) {
                visit[next] = cur[1] + 1;
                q.offer(new int[] {next, cur[1]+1});
            }

            // 텔레포트
            int teleport = cur[0] * 2;
            if(teleport <= 100000 && visit[teleport] >= cur[1] + 1) {
                visit[teleport] = cur[1] + 1;
                q.offer(new int[] {teleport, cur[1]+1});
            }
        }

        System.out.println(visit[K]);
        System.out.println(cnt);
    }
}
