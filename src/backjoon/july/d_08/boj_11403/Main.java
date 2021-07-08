package backjoon.july.d_08.boj_11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드-와샬 알고리즘을 사용하여 풀이

// 모든 정점의 개수(N)이 100이기 때문에 플로이드-와샬 알고리즘을 사용하면 100^3 시간이 걸리므로 시간 초과는 나지 않음
// 시작 노드 i, 중간 노드 k, 끝 노드 j
// i -> k 로 갈 수 있으면서 k -> j로 갈수 있다면 i -> j로 갈 수 있음
public class Main {
    public static int[][] map;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        floyd_warshall();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void floyd_warshall() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                }
            }
        }
    }
}
