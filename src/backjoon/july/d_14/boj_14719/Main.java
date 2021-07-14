package backjoon.july.d_14.boj_14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 2중 포문을 이용한 풀이

// 너비의 길이가 최대 500이므로 최대 500 * 500을 돌려도 시간 초과 X(높이는 상관 X)
// 현재 기준으로 왼쪽에서 가장 큰 높이를 찾고, 오른쪽에서 가장 큰 높이를 찾아
// 그 중 낮은 곳과 현재 높이를 뺀 값을 총합에 계속해서 더함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] height = new int[W];
        for(int i = 0; i < W; i++) height[i] = Integer.parseInt(st.nextToken());

        int sum = 0;
        for(int i = 1; i < W - 1; i++) {
            int l = height[i];
            int r = height[i];
            for(int j = 0; j < i; j++) l = Math.max(l, height[j]);
            for(int j = i+1; j < W; j++) r = Math.max(r, height[j]);
            sum += Math.min(l, r) - height[i];
        }
        System.out.println(sum);
    }
}
