package backjoon.august.d_06.boj_20442;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 투 포인터를 이용한 풀이

// 투 포인터를 사용하기 전에 현재 위치에서 왼쪽에 있는 K의 개수와 오른쪽에 있는 K의 개수를 구함

// 만약 왼쪽 R의 위치 : l, 오른쪽 R의 위치 : r 일 때,
// 최대 길이는 l 왼쪽에 존재하는 K와 r 오른쪽에 존재하는 K의 개수중 작은 개수를 선택하고 곱하기 2를 함
// KKKRRRRKKKK 라는 문자열이 있을 때
//    ↑  ↑
//    l  r
// l의 왼쪽 K의 개수 = 3, r의 오른쪽 K의 개수 = 4
// ㅋㅋ루ㅋㅋ 문자열은 K가 양끝에 하나씩 있어야 하므로 K의 개수가 맞아야 함
// 그러므로 3을 선택하고 길이가 양쪽을 더해야 하므로 곱하기 2

// 따라서 i 위치보다 왼쪽에 있는 K의 수와 오른쪽에 있는 K의 수를 구해서 배열에 넣어줌

// 이후 가운데 R의 개수를 구해야 함
// R의 개수는 r - l + 1로 구할 수 있다고 생각할 수 있지만, 중간에 K가 끼어있을 수도 있음(KKKRRKRKRKKKK 이런식으로)
// 그러므로 r - l + 1 을 하고  가운데에 있는 K의 개수를 빼주어야 함
// 이는 앞서 구한 K의 개수로 쉽게 구할 수 있음

// 그리고 포인터를 움직이는 방식은 K의 개수 배열을 보고 체크
// 만약 lk[l] < rk[r] 이라면 오른쪽 r을 아무리 줄여도 K개수는 왼쪽 기준으로 되기 때문에 소용이 없음
// 따라서 l을 늘려야 함
// 이 반대의 경우에는 r을 줄여야 함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();
        int[] lk = new int[n];
        int[] rk = new int[n];

        int kCnt = 0;
        for(int i = 0; i < n; i++) {
            if(str.charAt(i) == 'K') kCnt++;
            lk[i] = kCnt;
        }
        kCnt = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(str.charAt(i) == 'K') kCnt++;
            rk[i] = kCnt;
        }
        int l = 0, r = n - 1;
        int cnt = 0;
        while(l <= r) {
            // R의 위치 찾기
            while(l < n && str.charAt(l) == 'K') l++;
            while(r >= 0 && str.charAt(r) == 'K') r--;
            if(l > r) break;

            // 길이 구하기
            //                   양 옆의 K의 개수             선택된 R 사이 길이, 선택된 R 사이의 K 개수
            cnt = Math.max(cnt, (2 * Math.min(lk[l], rk[r])) + (r - l + 1) - (lk[r] - lk[l]));

            // 왼쪽과 오른쪽 K의 개수를 보고 어느 포인터를 움직일지 생각
            if(lk[l] < rk[r]) l++;
            else r--;
        }
        System.out.println(cnt);
    }
}
