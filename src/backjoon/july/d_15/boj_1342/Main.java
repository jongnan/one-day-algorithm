package backjoon.july.d_15.boj_1342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백트래킹을 이용한 풀이

// 문자열이 최대 10개이므로 10!의 경우의 수가 존재
// 따라서 모든 수를 탐색을 해도 시간초과 X

// 문자열의 경우 소문자만 존재하고 중복이 가능하므로 26개의 배열을 만들어 개술르 저장
// 해당 배열을 가지고 백트래킹을 통해 문자열을 만듦
// 만들 때 행운 문자열인치를 판별하며 모든 문자열이 완성되었을 때 카운팅
// 중복된 문자가 있을 때는 문자열을 만들었을 때 중복이 발생할 수 있음
// aab -> a(0)a(1)b, a(1)a(0)b, a(0)ba(1), a(1)ba(0), ba(0)a(1), ba(1)a(0)
// 하지만 해당 방법은 무조건 순서대로 문자를 사용하기 때문에 0번째 문자와 1번째 문자가 뒤섞일 수 없음
public class Main {
    static int[] charCnt;
    static int size;
    static int cnt;
    public static void backtracking(String str) {
        if(str.length() == size) {
            cnt++;
            return;
        }

        for(int i = 0; i < 26; i++) {
            if(charCnt[i] == 0) continue;
            char cur = (char) (i + 'a');
            if(str.length() != 0 && str.charAt(str.length() - 1) == cur) continue;
            charCnt[i]--;
            backtracking(str + cur);
            charCnt[i]++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        charCnt = new int[26];
        char[] arr = br.readLine().toCharArray();
        size = arr.length;
        for(char c : arr) charCnt[c - 'a']++;
        backtracking("");
        System.out.println(cnt);
    }
}
