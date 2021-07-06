package backjoon.july.d_05.boj_13397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이분탐색을 이용한 풀이

// left = 0, right = 배열에서 가장 큰 수를 사용하여 구간을 정함
// 해당 구간 안에서 중간 값을 최대값으로 칭하고 해당 값을 M개의 구간이 나누어 지는지 확인
// 만약 구간이 M 보다 작게 나올 경우에는 left를 mid + 1
// 반대로 구간이 M보다 크게 나올 경우에는 right를 mid - 1
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int l = 0, r = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            r = Math.max(arr[i], r);
        }

        while(l <= r) {
            int m = (r + l) / 2;
            int max = 0, min = Integer.MAX_VALUE, cnt = 1;
            for(int i = 0; i < N; i++) {
                max = Math.max(max, arr[i]);
                min = Math.min(min, arr[i]);
                // 구간의 최대 값은 해당 구간 안에 최대 값과 최소 값을 뺀 값
                // 구긴의 최대값이 미리 체크한 값보다 클 시에는 구간을 나눔
                if(max - min > m) {
                    cnt++;
                    max = arr[i];
                    min = arr[i];
                }
            }
            if(cnt <= M) r = m - 1;
            else l = m + 1;
        }
        System.out.println(l);
    }
}
