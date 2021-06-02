package backjoon.june.D_02.BOJ_9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// LCS를 이용하여 풀이
// 검색해서 이해하는 것이 가장 빠름
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] t = new int[a.length+1][b.length+1];
        for(int i = 1; i <= a.length; i++) {
            for(int j = 1; j <= b.length; j++) {
                if(a[i-1] == b[j-1]) {
                    t[i][j] = t[i-1][j-1] + 1;
                }else {
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
                }
            }
        }
        System.out.println(t[a.length][b.length]);
    }
}
