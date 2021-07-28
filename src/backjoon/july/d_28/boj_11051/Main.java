package backjoon.july.d_28.boj_11051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합과 다이나믹 프로그래밍을 이용한 풀이

// 단순한 조합 식으로는 시간 초과나 구하는데 어려움을 겪을 수 있음
// nCr = ( n! ) / (r! * (n-r)!)
// 특히 나눗셈에 대한 나머지 연산은 분배 법칙으로 할 수 없기 때문에 페르마 소정리가 필요

// 따라서 다음과 같이 점화식을 이용하여 풀이
// nCr = n-1Cr-1 + n-1Cr
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int MOD = 10007;

        int[][] C = new int[1001][1001];
        for(int n = 1; n <= 1000; n++) {
            for(int r = 0; r <= n; r++) {
                if(r == 0 || (n == r)) {
                    C[n][r] = 1;
                    continue;
                }
                C[n][r] = ((C[n-1][r-1] % MOD) + (C[n-1][r] % MOD)) % MOD;
            }
        }
        System.out.println(C[N][K]);
    }
}
