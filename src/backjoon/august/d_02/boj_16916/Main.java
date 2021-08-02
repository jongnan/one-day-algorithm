package backjoon.august.d_02.boj_16916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// KMP 알고리즘을 사용하여 풀이

// 문자열의 길이가 최대 100만이므로 2중 포문을 사용한다면 시간초과

// 일일히 비교하는 것이 아닌 KMP 알고리즘을 사용하면 O(N+M) 시간에 할 수 있음
// KMP는 접두사와 접미사를 이용하여 다음 검색할 위치로 점프(https://bowbowbow.tistory.com/6)

// 예시
// ABCDABCDABEE
// ABCDABE
//       ↑ E만 다름 따라서 옮겨야함
// ABCDABCDABEE
//  ABCDABE     ←와 같이 한번씩만 움직이면 낭비
// ABCDABE에서 E 이전에 문자열중 접두사와 접미사가 같은 것중 가장 긴 것은 AB
// (AB)CD(AB)E 따라서 앞에 AB위치를 뒤 AB위치로 옮김
// ABCDABCDABEE
//     ABCDABE  ← 다시 비교 시작
//
// 이런식으로 비교해 나아감

// 해당 문제는 해당 문자열이 존재하는지 한번만 알면 되므로 있으면 바로 참을 리턴
public class Main {
    // 동일한 접두사, 접미사 길이를 저장하는 Pi 배열
    public static int[] getPi(String str) {
        char[] c = str.toCharArray();
        int[] pi = new int[str.length()];
        int j = 0;
        for(int i = 1; i < c.length; i++) {
            while(j > 0 && c[i] != c[j]) j = pi[j-1];
            if(c[i] == c[j]) pi[i] = ++j;
        }
        return pi;
    }
    // KMP 알고리즘
    public static boolean KMP(String ori, String cmp) {
        char[] oriArr = ori.toCharArray();
        char[] cmpArr = cmp.toCharArray();
        int[] pi = getPi(cmp);

        int j = 0;
        for(int i = 0; i < oriArr.length; i++) {
            // 만약 다르다면 해당 접미사 길이만큼 j를 움직임
            while(j > 0 && oriArr[i] != cmpArr[j]) j = pi[j-1];
            if(oriArr[i] == cmpArr[j]) {
                // J가 모든 길이를 다 돌아도 같을 때 해당 문자열은 존재
                if(j == cmpArr.length - 1) return true;
                j++;
            }
        }
        // 문자열 자체가 존재하지 않음
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String cmp = br.readLine();

        System.out.println(KMP(origin, cmp) ? 1 : 0);
    }
}
