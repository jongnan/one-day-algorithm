package backjoon.may_27.BOJ_14950;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 우선순위 큐를 사용하여 풀이
// 도시를 정복할 때마다 그 상황에서 가장 비용이 작은 도로를 찾아 선택
// 방어 태세를 하는 것은 어차피 다같이 오르는 것이기 때문에 상관 없으며 나중에 한번에 더해주면 됨
// 나중에 한번에 t를 계산하여 더해줘야 하기 때문에 도로 비용의 순서를 따로 기록
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        List<int[]>[] city = new List[N+1];
        for(int i = 0; i <= N; i++) city[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            city[A].add(new int[] {B, C});
            city[B].add(new int[] {A, C});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visit = new boolean[N+1];
        List<Integer> seq = new ArrayList<>();
        pq.offer(new int[] {1,0});

        while(!pq.isEmpty()) {
            int[] cityInfo = pq.poll();
            // 만약 이전에 등록한 도시면 기록 X
            if(!visit[cityInfo[0]]) seq.add(cityInfo[1]);
            visit[cityInfo[0]] = true;

            for(int i = 0; i < city[cityInfo[0]].size(); i++) {
                int[] nextInfo = city[cityInfo[0]].get(i);
                if(visit[nextInfo[0]]) continue;
                pq.offer(nextInfo);
            }
        }
        long ans = 0;
        for(int i = 1; i < seq.size(); i++) {
            ans += seq.get(i) + ((long) t * (i-1));
        }
        System.out.println(ans);
    }
}
