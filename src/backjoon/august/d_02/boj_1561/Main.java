package backjoon.august.d_02.boj_1561;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이분탐색을 통한 풀이

// N이 최대 20억이므로 1중 포문으로도 간당간당
// 마지막 시간이 T라고 했을 때 해당 시간까지 탄 사람은
// M + (T / time[0]) + (T / time[1]) + ... + (T / time[M-1]) 명
// 이를 통해 N명일 때의 마지막 시간 T를 이분탐색을 통해 구함
// 만약 N이 M보다 작거나 같으면 해당 서칭을 할 필요가 없음

// 이후 이전 시간과 비교하여 다른 놀이기구 중에 가장 번호가 큰 것을 선택
// 이전 시간과 비교하면 현재 시간에서 어디서 늘어난 것인지를 알기 때문
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] time = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) time[i] = Integer.parseInt(st.nextToken());

        // N이 M보다 작으면 바로 출력
        if(N <= M) {
            System.out.println(N);
            return;
        }

        // 최댓값으로는 20명이 최대 30분 걸리는 놀이기구를 탈 수 있으므로 600억
        long l = 0, r = 2000000000L * 30L;
        while(l <= r) {
            long m = (l + r) / 2;
            long cnt = M;
            for(int i = 0; i < M; i++) cnt += m / time[i];
            if(cnt >= N) r = m - 1;
            else l = m + 1;
        }

        long t = l, preT = l-1;
        long preCnt = M;
        for(int i = 0; i < M; i++) preCnt += preT / time[i];

        // 이전 시간과 현재 시간의 차이
        int left = N - (int)preCnt;
        int cnt = 0;
        int i = 0;
        // 이전 시간과 비교
        while(cnt < left) {
            if(t / time[i] != preT / time[i]) cnt++;
            i++;
        }
        System.out.println(i);
    }
}
