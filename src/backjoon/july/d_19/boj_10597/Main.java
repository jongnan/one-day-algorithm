package backjoon.july.d_19.boj_10597;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 부루트 포스를 이용한 풀이

// 1~N 까지의 수열이므로 주어진 문자열의 길이를 가지고 N을 찾는 것이 중요
// 만약 문자열의 길이가 10 미만이면 모든 수가 1의 자리수로 문자열의 길이가 곧 N
// 만약 문자열의 길이가 10 이상이면 1자리수 개수와 2자리수 개수를 더해주어야 함
// 즉, 1자리수(9) + 2자리수((문자열 길이 - 9) / 2) = N

// 이후 백트래킹을 통해 모든 경우의 수를 생각
// 만들어질 수는 1자리 또는 2자리기 때문에 1자리를 선택했을 경우와 2자리를 선택했을 경우로 나눔
// 문자열의 모든 길이를 돌았다면 수열이 만들어졌기 때문에 다른 순열이 나올 필요가 없음
// 따라서 boolean으로 체크하여 더이상 수열을 구하지 않도록 함
public class Main {
    // 수 체크 최대 수열의 개수가 50까지 였으므로 최대 50까지 나올 수 있음
    static boolean[] visit = new boolean[51];
    static String num;
    // 최대 길이 N
    static int maxValue;
    // 수열을 만들었는지 체크
    static boolean isFind = false;

    public static void backtracking(int d, List<Integer> list) {
        // 수열을 만들었을 때 출력
        if(d >= num.length()) {
            for(int i = 0; i < list.size(); i++) System.out.print(list.get(i) + " ");
            System.out.println();
            isFind = true;
            return;
        }
        if(isFind) return;

        // 1자리수와 2자리수 체크
        for(int len = 1; len <= 2; len++) {
            // 길이가 문자열을 넘어갈 경우는 넘김
            if(d + len > num.length()) continue;
            int n = Integer.parseInt(num.substring(d, d + len));
            // 최대 값이 넘어가거나 이미 체크한 수라면 넘김
            if(n > maxValue || visit[n]) continue;
            visit[n] = true;
            list.add(n);
            backtracking(d + len, list);
            // 끝났을 경우를 다시한번 체크
            if(isFind) return;
            visit[n] = false;
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = br.readLine();

        if(num.length() < 10) maxValue = num.length();
        else maxValue = (num.length() - 9) / 2 + 9;

        backtracking(0, new ArrayList<>());
    }
}
