package backjoon.august.d_06.boj_1469;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백트래킹을 이용한 풀이

// 만약 N이 10일때 총 만들수 있는 수열의 길이는 20
// 모든 경우의 수를 따지면 20!로 시간초과
// 따라서 해당 수를 선택한 위치 2곳에 넣을 수 있는지를 판별
public class Main {
    static int N;
    static int[] X;
    static int[] ans;
    static boolean[] visit;
    static boolean isFind;

    public static void bt(int d) {
        // 미리 판별을 하면서 오기 때문에 길이가 다 채워지면 출력
        if(d >= N * 2 && !isFind) {
            isFind = true;
            for (int i = 0; i < N * 2; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }

        // 해당 위치에 이미 수가 들어가 있으면 한칸 앞으로 가서 다시 시도
        if(ans[d] != -1) {
            bt(d + 1);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(isFind) return;
            // 방문 여부 파악
            if(visit[i]) continue;
            // 다음 위치가 들어갈 수 있는지 확인
            if(d + X[i] + 1 >= N * 2) continue;
            // 해당 위치 2곳에 들어갈 수 있는지 확인
            if(ans[d] != -1 || ans[d + X[i] + 1] != -1) continue;

            // 들어갈 수 있다면 넣고 다음으로 진행
            visit[i] = true;
            ans[d] = ans[d + X[i] + 1] = X[i];
            bt(d + 1);
            // 백트래킹
            if(isFind) return;
            visit[i] = false;
            ans[d] = ans[d + X[i] + 1] = -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        X = new int[N];
        for(int i = 0; i < N; i++) X[i] = Integer.parseInt(st.nextToken());
        // 사전순으로 빠르게 앞으로 와야하기 때문에 정렬
        Arrays.sort(X);

        ans = new int[N * 2];
        for(int i = 0; i < N*2; i++) ans[i] = -1;

        visit= new boolean[N];

        bt(0);

        if(!isFind) System.out.println(-1);
    }
}
