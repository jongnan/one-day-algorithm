package backjoon.july.d_29.boj_16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구현으로 풀이

// 용사는 무조건 모든 방을 돌 수 밖에 없음
// 각 방을 돌면서 해당 방에서 필요한 최소 hp를 구함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long atk = Integer.parseInt(st.nextToken());
        long hp = 0;
        long maxHp = Integer.MAX_VALUE;

        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            // 몬스터일 경우
            if(t == 1) {
                int m = h % atk == 0 ? 1 : 0;
                hp -= a * (h / atk - m);
            }
            // 포션일 경우
            else {
                atk += a;
                hp += h;
                if(hp >= 0) hp = 0;
            }
            maxHp = Math.min(maxHp, hp);
        }
        System.out.println(maxHp * -1 + 1);
    }
}
