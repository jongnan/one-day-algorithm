package backjoon.may.D_19;

import java.util.*;

class Bus {
    int to, money;
    Bus(int to, int money) {
        this.to = to;
        this.money = money;
    }
}

// 다익스트라를 사용하는 문제
// 다익스트라에서 매번 최소인 정점을 선택하는 것은 최단 거리가 될 수 있는 가능성이 가장 높기때문(그리드)
// 따라서 해당 알고리즘은 우선순위 큐를 사용하여 들어간 정점의 weight가 가장 작은 정점을 추출
// 다익스트라는 모든 정점들은 무조건 한번은 접근(탐색)-> O(E)
// 우선순위 큐에 정점이 들어갈 수 있는 최대의 수 -> O(E) + 해당 정접을 삽입 삭제 -> O(logE) ==> O(E * logE)
// 총 시간 복잡도는 O(E + E*logE) = O(E * logE),  E < V^2 -> O(E * logV)
public class BOJ_11779 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        List<Bus>[] graph = new List[N+1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int money = sc.nextInt();
            graph[from].add(new Bus(to, money));
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        int[] dist = new int[N+1];
        int[] prev = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Bus> pq = new PriorityQueue<>(Comparator.comparingInt(b -> b.money));
        pq.offer(new Bus(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Bus b = pq.poll();
            if(b.to == end) break;
            // 해당 정점에 인접한 간선들을 모두 검사
            for(Bus next : graph[b.to]) {
                if(dist[next.to] > b.money + next.money) {
                    dist[next.to] = b.money + next.money;
                    // 갱신할 때 자신이 어디서 왔다는 것을 기록
                    prev[next.to] = b.to;
                    pq.offer(new Bus(next.to, dist[next.to]));
                }
            }
        }

        // 결과 값에서부터 역순으로 찾아가기 위해 스택 사용
        Deque<Integer> stack = new ArrayDeque<>();
        int cur = end;
        while(cur != 0) {
            stack.push(cur);
            cur = prev[cur];
        }

        System.out.println(dist[end]);
        System.out.println(stack.size());
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}
