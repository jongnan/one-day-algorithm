package backjoon.june.D_17.BOJ_1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백트래킹을 이용한 풀이

// N이 최대 10이므로 체스판의 최대 크기는 10 * 10
// r, c 위치에 비숍을 놓을지 말지 체크를 해야하므로 최대 2^(10*10)의 시간이 걸림
// 따라서 시간 초과
//
// 비숍의 특성을 살려야 함
// 비숍은 대각선으로 움직일 수만 있음
// 만약 체스판을 [검 흰 검 흰 검] 이렇게 각 위치마다 색상을 엇갈리게 놓는다고 했을 때
// 검정 위치에 있는 비숍은 검정칸으로만 움직일 수 있음
// 따라서 흰색에 존재하는 비숍과는 별개로 놓을 수 있음
// 검정색 칸은 N/2 * N/2 개, 흰색 칸도 N/2 * N/2 개이므로 각자 백트래킹을 시도했을때
// 2^(N/2 * N/2) + 2^(N/2 * N/2)의 시간이 걸림
public class Main {
    static int N;
    static int[][] map;
    // lt = left-top (왼쪽 위에서 오른쪽 아래 방향)
    // rt = right-top (오른쪽 위에서 왼쪽 아래 방향)
    static int[] lt, rt;
    static int[] ans = {0, 0};

    public static void tracking(int r, int c, int cnt, int color) {
        // 검흰이 교차로 있어야 하므로 이를 계산
        if(c >= N) {
            r++;
            c = c % 2 == 0 ? 1 : 0;
        }
        // 모든 로우를 살펴보았을 때 가장 많이 놓았을 경우를 체크
        if(r >= N) {
            ans[color] = Math.max(ans[color], cnt);
            return;
        }
        // 해당 위치에 비숍을 넣을 수 있을지 판단한 뒤 넣을 수 있다면 무조건 넣고 봄
        if(map[r][c] == 1 && lt[c - r + N - 1] == 0 && rt[r + c] == 0) {
            lt[c - r + N - 1] = 1;
            rt[r + c] = 1;
            tracking(r , c + 2, cnt + 1, color);
            lt[c - r + N - 1] = 0;
            rt[r + c] = 0;
        }
        // 해당 위치에 넣을 수 있기도하고 안넣을 수 도 있을 때는 둘 다 확인
        tracking(r, c + 2, cnt, color);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        // lt와 rt는 최대 2*N개를 가질 수 있음
        lt = new int[2 * N];
        rt = new int[2 * N];

        tracking(0, 0, 0, 0);
        tracking(0, 1, 0, 1);
        System.out.println(ans[0] + ans[1]);
    }
}
