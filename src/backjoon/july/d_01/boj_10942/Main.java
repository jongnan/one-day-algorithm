package backjoon.july.d_01.boj_10942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 다이나믹프로그래밍을 이용한 풀이(Bottom - Up)

// 길이가 1, 2, 3이상일 때를 나누어서 팰린드롬을 확인
// 길이 1 : 무조건 팰린드롬
// 길이 2 : 연속된 두개의 값이 같은지 확인
// 길이 3 이상 : 길이를 통해 시작과 끝을 값을 비교하고 이전 구간이 팰린드롬인지 확인
//              즉, 시작과 끝이 같을 때 [시작+1 , 끝-1] 구간이 팰린드롬이라면 해당 값도 팰린드롬
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());

        int[][] isPal = new int[N][N];
        for(int i = 0; i < N; i++) isPal[i][i] = 1;
        for(int i = 0; i < N - 1; i++) {
            if(nums[i] == nums[i+1]) isPal[i][i+1] = 1;
        }
        for(int len = 2; len < N; len++) {
            for(int s = 0; s < N - len; s++) {
                int e = s + len;
                if(nums[s] == nums[e] && isPal[s+1][e-1] == 1) isPal[s][e] = 1;
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            sb.append(isPal[s][e]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
