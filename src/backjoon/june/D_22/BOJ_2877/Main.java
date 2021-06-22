package backjoon.june.D_22.BOJ_2877;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 비트 마스킹을 사용하여 풀이

// 각 자리수마다 2^N 개의 수가 존재
// 따라서 몇 번째로만으로도 자리수를 알 수 있음
// 최대 10^9이기에 2^30까지 미리 수를 계산해 놓고 이를 1부터 빼가면서 몇 자리 수 인지 파악
// 이후 해당 자리수에서 몇번째 수에 해당하는 것은 해당 값을 2진수로 바꾼뒤 0을 4로 1을 7로만 바꾸면 알 수 있음
// 왜? 0과 1 그리고 4와 7의 관계가 똑같기 때문
// 여기서 주의할 점은 2진수로 변환했을 때 해당 자리수에 만족하지 못한다면 0을 앞에서 계속 채워야함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int[] cnt = new int[31];
        for(int i = 0; i < 31; i++) cnt[i] = (1 << i);
        int unit = 0;
        for(int i = 1; i < 31; i++) {
            if(K > cnt[i]) K -= cnt[i];
            else {
                unit = i;
                break;
            }
        }
        K--;
        StringBuilder bin = new StringBuilder(Integer.toBinaryString(K));
        while(bin.length() < unit) bin.insert(0, "0");
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < bin.length(); i++) {
            if(bin.charAt(i) == '0') ans.append("4");
            else ans.append("7");
        }
        System.out.println(ans.toString());
    }
}
