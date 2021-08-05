package backjoon.august.d_05.boj_1316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// Set을 이용한 풀이

// 연속된 문자열의 끝에서 Set에 해당 문자열을 넣어주기
// 만약 다음 문자열 체크에서 Set에 있다면 그룹 단어가 아님
public class Main {
    public static boolean isGroupStr(String str) {
        char[] arr = str.toCharArray();
        Set<Character> s = new HashSet<>();
        for(int i = 0; i < arr.length - 1; i++) {
            if(s.contains(arr[i])) return false;
            if(arr[i] != arr[i+1]) s.add(arr[i]);
        }
        if(s.contains(arr[str.length() - 1])) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        while(N-- > 0) {
            if(isGroupStr(br.readLine())) ans++;
        }
        System.out.println(ans);
    }
}
