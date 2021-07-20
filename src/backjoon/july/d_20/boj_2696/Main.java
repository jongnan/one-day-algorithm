package backjoon.july.d_20.boj_2696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 우선순위 큐를 이용하여 풀이

// 우선순위 큐를 하나만 사용하는 것이 아닌 두개를 사용(min, max)
// 홀수번째에서 중간값을 알고 있어야 하므로 중간 값을 따로 저장

// 홀수번째에서 측정을 해야 하므로 매번 2개의 수가 들어오는 것을 확인
// 중간값과 들어오는 2개 값과의 관계에 따라 다음 중간값이 정해짐

// 들어올 2개 모두 중간값보다 클 때 다음 중간값은 다음으로 큰 수가 됨
// 따라서 중간값보다 큰 값들은 minHeap에 넣고 다음 큰 값을 계속해서 트래킹
// 중간값을 maxHeap에 넣어주고 minHeap의 첫번째 값과 교체

// 들어올 2개 모두 중간값보다 작을 때 다음 중간값은 다음으로 작은수가 됨
// 따라서 중간값보다 작은 값들은 maxHeap에 넣고 다음 작은 값을 계속해서 트래킹
// 중간값을 minHeap에 넣고 maxHeap의 첫번째 값과 교체

// 들어올 2개 중 1개는 중간값보다 크고 1개는 중간값보다 작을 때는 중간값이 바뀔 수 없음
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            int M = Integer.parseInt(br.readLine());
            int[] nums = new int[M];

            int unit = M / 10;
            StringTokenizer st;
            for(int i = 0; i < unit; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 10; j++) {
                    nums[i*10 + j] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M - (unit * 10); i++) {
                nums[unit*10 + i] = Integer.parseInt(st.nextToken());
            }

            int mid = nums[0];
            List<Integer> ans = new ArrayList<>();
            ans.add(mid);
            for(int i = 1; i < M; i++) {
                // 새로 들어올 값을 중간값과 비교하여 적절한 우선순위 큐에 넣음
                if(nums[i] > mid) minHeap.offer(nums[i]);
                else maxHeap.offer(nums[i]);

                // 홀수번째마다 중간값과 체크
                // 이때는 각 우선순위 큐의 사이즈로 확인
                // 동일하게 들어온다면 두 큐의 크기는 같고
                // 만약 작은 수 2개가 들어오면 maxHeap 개수가 더 크고
                // 큰 수 2개가 들어오면 minHeap 개수가 더 큼
                if((i + 1) % 2 == 1) {
                    if(minHeap.size() > maxHeap.size()) {
                        maxHeap.offer(mid);
                        mid = minHeap.poll();
                    }else if(minHeap.size() < maxHeap.size()) {
                        minHeap.offer(mid);
                        mid = maxHeap.poll();
                    }
                    ans.add(mid);
                }
            }
            System.out.println(ans.size());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < ans.size(); i++) {
                sb.append(ans.get(i));
                sb.append(" ");
                if((i + 1) % 10 == 0) sb.append("\n");
            }
            System.out.println(sb.toString());
        }
    }
}
