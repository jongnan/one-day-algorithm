package backjoon.june.D_11.BOJ_21279;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 우선순위 큐를 이용하여 풀이
// 중요한 점은 호석이가 시작하는 지점은 (0,0)으로 고정
// 만약, X축을 늘리면서 확인하는 동시에 Y축을 늘려가며 확인하면 10만 * 10만이므로 시간초과
// 따라서 다른 방법이 필요

// 두개의 축 중, 하나를 선택하여 해당 축을 기준으로 몇개의 광물이 존재하는지 확인
// X축을 기준으로 했다면 X축을 늘려가면서 해당 위치에 있는 광물들을 우선순위 큐에 넣음
// 우선순위 큐에 넣는 이유는 해당 위치에서 광물이 C보다 클 수 있으므로 넣은 광물을 제거해야되는데
// 이때 Y축으로 가장 높은 광물부터 빼기 위함
// 이는 광산뒤집기가 직사각형 범위이므로 중간 광물들을 빼낼 수 없음
// 따라서 가장 위에있는 광물부터 빼내기 위함
// 이 조건을 만족하는 한에서 광물들의 아름다움의 합산을 비교
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        List<int[]>[] axisX = new List[100001];
        for(int i = 0; i <= 100000; i++) axisX[i] = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            axisX[x].add(new int[] {y, v});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        long sum = 0;
        long ans = 0;
        for(int i = 0; i <= 100000; i++) {
            if(axisX[i].isEmpty()) continue;
            for(int[] mineral : axisX[i]) {
                pq.offer(mineral);
                sum += mineral[1];
            }
            while(!pq.isEmpty() && pq.size() > C) {
                int[] deleteMineral = pq.poll();
                sum -= deleteMineral[1];
            }
            ans = Math.max(sum, ans);
        }
        System.out.println(ans);
    }
}
