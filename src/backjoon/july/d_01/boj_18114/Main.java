package backjoon.july.d_01.boj_18114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 해시 맵을 사용한 풀이

// 물건은 최대 3개까지만 고를 수 있지만, N이 쵀대 5000개가 존재하므로
// 3개를 뽑는 경우의 수는 5000^3
// 여기서 중요한 것은 C가 되는 경우의 개수를 구하는 것이 아닌 조합이 있냐 없냐의 문제

// 1개와 2개까지는 시간 초과가 상관없음(N이 작으므로)

// 3개는 2개에서 변형시켜 구할 수 있음
// 우선 모든 물건의 무게를 Map에 저장(key : 물건 무게,  value : 개수)
// 2중 포문을 통해 물건 2개 i, j를 뽑았고, 이가 C값과 작을 때
// 3번째 무게는 C - (w[i] + w[j]) 가 됨
// 따라서 이 무게가 존재하는지 Map에서 검사
// 만약 i, j가 2개 이상 존재할 수 있으니 뽑힌 i, j의 개수는 1개씩 줄이고 판별
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] w = new int[N];
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < N; i++) {
            w[i] = Integer.parseInt(st.nextToken());
            if(m.containsKey(w[i])) m.put(w[i], m.get(w[i]) + 1);
            else m.put(w[i], 1);
            // 1개의 무게는 바로 체크 가능
            if(w[i] == C) {
                System.out.println(1);
                return;
            }
        }

        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                int sum = w[i] + w[j];
                // 뽑힌 2개의 값이 C와 같을 때
                if(sum == C) {
                    System.out.println(1);
                    return;
                }else if(sum < C) {
                    int iCnt = m.get(w[i]) - 1;
                    int jCnt = m.get(w[j]) - 1;
                    int remain = C - sum;
                    // 나머지 값이 i와 같고 i의 개수가 존재할 때
                    if((remain == i && iCnt > 0)
                        // 나머지 값이 j와 같고 j의 개수가 존재할 때
                            || (remain == j && jCnt > 0)
                        // 나머지 값이 i, j가 아닌 다른 값인데 개수가 존재할 때
                            || (remain != w[i] && remain != w[j] &&  m.containsKey(remain))) {
                        System.out.println(1);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }
}
