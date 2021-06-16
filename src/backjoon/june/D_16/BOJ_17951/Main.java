package backjoon.june.D_16.BOJ_17951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이분 탐색을 통한 풀이

// 0 ~ 만들 수 있는 최대 점수 사이에서 중간 값을 찾고 해당 중간 값을 기준으로
// 몇개의 그룹을 만들 수 있을지를 판별
// 만약 K개 혹은 그 이상으로 만들 수 있다면, 점수가 낮으므로 right를 mid로 바꾸어 탐색
// K개 이하라면 점수가 너무 높으므로 left를 mid로 바꾸어 탐색
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] paper = new int[N];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
            sum += paper[i];
        }
        int l = 0, r = sum + 1;
        while(l + 1 < r) {
            int m = (l + r) / 2;
            int groupCnt = 0;
            int s = 0;
            for(int i = 0; i < N; i++) {
                s += paper[i];
                if(s >= m) {
                    groupCnt++;
                    s = 0;
                }
            }
            if(groupCnt >= K) l = m;
            else r = m;
        }
        System.out.println(l);
    }
}
