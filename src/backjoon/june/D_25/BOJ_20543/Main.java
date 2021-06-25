package backjoon.june.D_25.BOJ_20543;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그리디 + 누적합을 이용한 풀이
//
// https://countrysides.tistory.com/54
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[][] map = new long[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Long.parseLong(st.nextToken()) * -1;
            }
        }

        long[][] ans = new long[N][N];
        int r = M / 2;
        int s = r;
        int e = N - r;

        for(int i = s; i < e; i++) {
            for(int j = s; j < e; j++) {
                ans[i][j] = map[i-r][j-r];
                if(i-r-1 >= 0) ans[i][j] -= map[i-r-1][j-r];
                if(j-r-1 >= 0) ans[i][j] -= map[i-r][j-r-1];
                if(i-r-1 >= 0 && j-r-1 >= 0) ans[i][j] += map[i-r-1][j-r-1];
                if(i-M >= 0) ans[i][j] += ans[i-M][j];
                if(j-M >= 0) ans[i][j] += ans[i][j-M];
                if(i-M >= 0 && j-M >= 0) ans[i][j] -= ans[i-M][j-M];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            StringBuilder sub = new StringBuilder();
            for(int j = 0; j < N; j++) {
                sub.append(ans[i][j]);
                sub.append(" ");
            }
            sb.append(sub.toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
