package backjoon.july.d_20.boj_13424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드-와샬 알고리즘을 사용하여 풀이

// N이 100이하이기 때문에 플로이드 워샬 알고리즘을 사용하여도 최대 100^3으로 시간초과 X
// 테케의 길이가 만약 100이 넘어가면 시간초과 날지도...근데 안난거 보면...그정도 까진 아닌듯..

// 주의할점은 자신의 방은 무조건 0으로 초기화...
// 움직이지 않으니 길이도 0...
// 이후 사람마다 각 노드까지 거리를 더해준 다음 가장 작은 수를 체크
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] graph = new int[N+1][N+1];
            for(int i = 0; i <= N; i++)
                for(int j = 0; j <= N; j++) {
                    if(i == j) graph[i][j] = 0;
                    else graph[i][j] = Integer.MAX_VALUE;
                }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph[a][b] = w;
                graph[b][a] = w;
            }

            for(int k = 1; k <= N; k++) {
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= N; j++) {
                        if(graph[i][k] == Integer.MAX_VALUE
                            || graph[k][j] == Integer.MAX_VALUE) continue;
                        if(graph[i][j] > graph[i][k] + graph[k][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }

            int[] sumDist = new int[N+1];
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++) {
                int m = Integer.parseInt(st.nextToken());
                for(int j = 1; j <= N; j++) {
                    sumDist[j] += graph[m][j];
                }
            }

            int idx = 1;
            int min = sumDist[1];
            for(int i = 2; i <= N; i++) {
                if(min > sumDist[i]) {
                    idx = i;
                    min = sumDist[i];
                }
            }
            System.out.println(idx);
        }
    }
}
