package backjoon.june.D_21.BOJ_1414;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 크루스칼 알고리즘을 사용하여 풀이

// 다솜이가 최대한으로 길게 랜선을 줘야함
// 그렇기 때문에 전체 랜선 길이에서 컴퓨터를 연결할 수 있는 최소한의 랜선길이를 빼면
// 최대한으로 줄 수 있는 랜선의 길이가 나옴
// 최소한으로 컴퓨터를 연결하는 방법으로 크루스칼 알고리즘을 사용

// 주의 할 점
// 우선순위 큐를 사용하여 랜선 길이별로 정렬하면 0인 것이 가장 빨리 나오게 됨
// 0은 연결이 되어있지 않은 상태이기 때문에 넘겨야 함
public class Main {
    static int[] parent;
    public static int find(int target) {
        if(parent[target] == 0) return target;
        return parent[target] = find(parent[target]);
    }
    public static boolean union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return false;
        parent[rb] = ra;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        int totalLen = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        for(int i = 1; i <= N; i++) {
            String row = br.readLine();
            for(int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                int len = 0;
                if(c >= 'a' && c <= 'z') len = c - 'a' + 1;
                if(c >= 'A' && c <= 'Z') len = c - 'A' + 27;
                totalLen += len;
                pq.offer(new int[] {len, i, j+1});
            }
        }

        int minLen = 0;
        int cnt = 1;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == 0) continue;
            if(union(cur[1], cur[2])) {
                cnt++;
                minLen += cur[0];
            }
        }

        if(cnt != N) System.out.println(-1);
        else System.out.println(totalLen - minLen);
    }
}
