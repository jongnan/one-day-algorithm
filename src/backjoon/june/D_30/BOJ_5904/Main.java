package backjoon.june.D_30.BOJ_5904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀를 통한 풀이

// S(1) = m o o m o o o m o o 일때, S(1) = S(0) [m o o o] S(0)
// 여기서 N이 속한 부분을 찾아야 함

// 1. 앞쪽이라면 S(0)에서 다시 N을 찾아야 함
// 2. 중간에 새로 생긴 문자열이라면, N에서 이전 수열의 길이를 뺀 값이 1일때 m 이고 나머지 위치에서는 o
// 3. 뒤쪽이라면 N에서 이전 수열 길이와 새로 생긴 문자열의 길이를 빼고 S(0)에서 N을 다시 찾아야 함

// 이를 재귀적으로 반복하면 언젠가는 중간에 새로 생긴 문자열에서 걸림
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int k = 0, len = 3;
        while(len < N) len += ++k + 3 + len;
        while(true) {
            int pre = (len - (k + 3)) / 2;
            if(N > pre) {
                N -= pre;
                if(N <= k + 3) break;
                N -= k + 3;
            }
            len = pre;
            k--;
        }
        if(N == 1) System.out.println("m");
        else System.out.println("o");
    }
}
