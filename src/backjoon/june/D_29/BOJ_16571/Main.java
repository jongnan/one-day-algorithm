package backjoon.june.D_29.BOJ_16571;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부루트 포스를 이용한 풀이

// 핵심은 현재 플레이어가 가장 최선의 수를 두는 것
// 따라서 다음 플레이어의 결과가 져야지만 가장 최선의 수를 둔 것
// win = 1, draw = 0, lose = -1
public class Main {
    static int[][] map;
    public static boolean checkWinner(int turn) {
        for(int i = 0; i < 3; i++) {
            if(map[i][0] == turn && map[i][0] == map[i][1] && map[i][0] == map[i][2]) return true;
        }
        for(int i = 0; i < 3; i++) {
            if(map[0][i] == turn && map[0][i] == map[1][i] && map[0][i] == map[2][i]) return true;
        }
        if(map[0][0] == turn && map[0][0] == map[1][1] && map[0][0] == map[2][2]) return true;
        if(map[0][2] == turn && map[0][2] == map[1][1] && map[0][2] == map[2][0]) return true;

        return false;
    }
    public static int playGame(int cur) {
        // 만약 상대방이 이겼다면 -1 반환
        if(checkWinner(3 - cur)) return -1;

        // 범위가 -1, 0, 1 이기 때문에 2가 가장 큰 수
        int min = 2;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(map[i][j] != 0) continue;
                map[i][j] = cur;
                // 상대방의 결과 중에 가장 선호하는 것은 -1
                min = Math.min(min, playGame(3 - cur));
                map[i][j] = 0;
            }
        }
        // 해당 결과가 변경이 되지 않았거나(2), 비겼다면(0) 0 반환
        if(min == 2 || min == 0) return 0;
        // 상대방의 결과와는 반대로 내 결과가 나오기 때문에 -1을 곱한 것을 반환
        return min * -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[3][3];
        int x = 0, o = 0;
        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) x++;
                if(map[i][j] == 2) o++;
            }
        }
        int curPlayer = x > o ? 2 : 1;
        int ans = playGame(curPlayer);
        if(ans == 1) System.out.println('W');
        else if(ans == -1) System.out.println('L');
        else System.out.println('D');
    }
}
