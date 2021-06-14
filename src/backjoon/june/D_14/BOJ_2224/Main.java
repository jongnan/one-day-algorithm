package backjoon.june.D_14.BOJ_2224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 플로이드-워샬 알고리즘을 사용하여 풀이
// A-Z,a-z 까지 52개의 배열로 나타낼 수 있음
// A-Z -> 0~25 , a-z -> 26~51
// 해당 배열을 만들고 주어진 명제에 따라 그래프를 그림
// 이후 플로이드-워샬 알고리즘을 사용하여 그래프를 이어줌
// (i -> k 가 갈 수 있고 k -> j 도 갈 수 있다면, i -> j 는 갈 수 있음)
// 이후 그래프를 돌면서 같은 문자들을 출력하지 않고 갈 수 있는 문자들을 모두 출력
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
       int N = Integer.parseInt(br.readLine());
       boolean[][] graph = new boolean[52][52];
       for(int i = 0; i < N; i++) {
           char[] q = br.readLine().toCharArray();
           int x = q[0] <= 90 ? q[0] - 'A' : q[0] - 'a' + 26;
           int y = q[5] <= 90 ? q[5] - 'A' : q[5] - 'a' + 26;
           graph[x][y] = true;
       }

       for(int k = 0; k < 52; k++) {
           for(int i = 0; i < 52; i++) {
               for(int j = 0; j < 52; j++) {
                   if(graph[i][k] && graph[k][j]) graph[i][j] = true;
               }
           }
       }
       int sum = 0;
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < 52; i++) {
           for(int j = 0; j < 52; j++) {
               if(!graph[i][j] || i == j) continue;
               sum += 1;
               int x = i < 26 ? i + 'A' : i + 'a' - 26;
               int y = j < 26 ? j + 'A' : j + 'a' - 26;
               sb.append((char)x).append(" => ").append((char)y).append("\n");
           }
       }
       System.out.println(sum);
       System.out.println(sb.toString());
    }
}
