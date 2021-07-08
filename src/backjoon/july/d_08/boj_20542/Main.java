package backjoon.july.d_08.boj_20542;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Levenshtein 거리 알고리즘을 사용하여 풀이

// 해당 알고리즘은 위키피디아에 자세히 나와 있음 (https://en.wikipedia.org/wiki/Levenshtein_distance)
// 이 알고리즘은 다이나믹 프로그래밍 알고리즘을 사용하는 것으로 이전까지 진행했던 문자열을 보고
// 가장 적게 바뀐 경우를 선택

// 문제에서는 추가적으로 i = i, j, l 이고 v = v, w 이므로 해당 조건만 추가
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String s = br.readLine();
        String ans = br.readLine();

        int[][] dist = new int[n+1][m+1];
        for(int i = 0; i <= n; i++) dist[i][0] = i;
        for(int j = 0; j <= m; j++) dist[0][j] = j;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(isSame(s.charAt(i-1), ans.charAt(j-1))) {
                    dist[i][j] = dist[i-1][j-1];
                }else {
                    dist[i][j] = Math.min(Math.min(dist[i-1][j] + 1, dist[i][j-1] + 1), dist[i-1][j-1] + 1);
                }
            }
        }

        System.out.println(dist[n][m]);
    }
    public static boolean isSame(char a, char b) {
        if(a == b) return true;
        if(a == 'i' && (b == 'j' || b == 'l')) return true;
        if(a == 'v' && b == 'w') return true;
        return false;
    }
}
