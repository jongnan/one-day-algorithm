package backjoon.july.d_21.boj_14921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정렬 후 투 포인터를 사용하여 풀이

// 모든 개수가 10만개 이므로 모든 경우의 수를 다 해보면 시간초과
// 따라서 정렬을 한 뒤 가장 큰 수(r의 위치)와 작은 수(l의 위치)를 더해서 비교함
// 왜? 정렬을 한 뒤 두 위치의 값을 더해 보고 0보다 계속해서 작아지거나 커진다면 비교할 필요가 없기 때문

// 0과 가장 가까워야 하므로 절대값을 사용하여 비교
// 만약 절대값이 작다면 현재 값을 저장
// 이후, 더한 값이 0보다 작다면 l을 증가 반대로 0보다 크면 r을 감소
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int l = 0, r = arr.length - 1;

        // 절대값 비교
        int diff = Integer.MAX_VALUE;
        // 정답
        int ans = 0;
        while(l < r) {
            int sum = arr[l] + arr[r];
            // 절대값을 비교
            if(diff > Math.abs(sum)) {
                diff = Math.abs(sum);
                ans = sum;
            }
            // 더한 값이 0보다 작거나 같으면 l을 증가
            if(sum <= 0) {
                l++;
            }
            // 크다면 r을 감소
            else {
                r--;
            }
        }
        System.out.println(ans);
    }
}
