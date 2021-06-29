package backjoon.june.D_29.BOJ_13022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 단순 구현 문제

// 문자열을 돌면서 해당 조건이 맞는지 확인
// w -> o -> l -> f 순으로 돌아야 하기 때문에 유효검사를 진행하는 문자를 돌때 해당 순으로 판별
public class Main {
    public static char[] wolf = new char[] {'w','o','l','f'};
    // 같은 문자열의 마지막 인덱스를 반환
    public static int countChar(char target, String str, int idx) {
        // 만약 해당 순서의 문자가 오지 않는다면 -1 반환(문자 순서 판단 - 1번 조건)
        if(idx >= str.length() || str.charAt(idx) != target) return -1;
        while(idx < str.length() - 1) {
            if(str.charAt(idx) == str.charAt(idx+1)) idx++;
            else break;
        }
        return idx;
    }
    // 해당 문자의 길이가 같은지 판단(1번 조건)
    public static boolean isAvailLength(int pre, int next, int len) {
        if(next == -1 || next - pre + 1 != len) {
            System.out.println(0);
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int idx = 0;
        // 문자열을 처음부터 반복(여러개의 유효 문자가 나올 수 있기 때문)
        while(idx < str.length()) {
            int n = 0;
            // w o l f 순으로 돌면서 확인
            for(int i = 0; i < 4; i++) {
                int nIdx = countChar(wolf[i], str, idx);
                if(i == 0) n = nIdx - idx + 1;
                if(!isAvailLength(idx, nIdx, n)) return;
                idx = nIdx + 1;
            }
        }
        System.out.println(1);
    }
}
