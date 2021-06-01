package backjoon.may.D_26.BOJ_20495;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이분 탐색을 사용하여 풀이
// 최솟값이 되려면 해당 번째 수는 가장 작은 수가 되야하고 나머지 수들은 가장 큰 수가 되면 됨
// 최댓값이 되려면 그 반대로 진행
// 따라서 먼저 값의 범위를 받고 최솟값과 최댓값들을 저장
// 이후 이분 탐색을 하기위해 다른 배열에 저장하여 정렬
// 최솟값을 최댓값들 사이에서 lowerBound 위치를 찾고
// 최댓값을 최솟값들 사이에서 upperBound 위치를 찾음
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int lowerBound(int[] data, int target) {
        int l = 0, h = data.length;
        while(l < h) {
            int m = (l + h) / 2;
            if(data[m] >= target) {
                h = m;
            }else {
                l = m + 1;
            }
        }
        return h;
    }
    public static int upperBound(int[] data, int target) {
        int l = 0, h = data.length;
        while(l < h) {
            int m = (l + h) / 2;
            if(data[m] <= target) {
                l = m + 1;
            }else {
                h = m;
            }
        }
        return h;
    }
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] low = new int[N];
        int[] high = new int[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            low[i] = a - b;
            high[i] = a + b;
        }

        int[] sortedLow = new int[N];
        for(int i = 0; i < N; i++) sortedLow[i] = low[i];
        Arrays.sort(sortedLow);
        int[] sortedHigh = new int[N];
        for(int i = 0; i < N; i++) sortedHigh[i] = high[i];
        Arrays.sort(sortedHigh);

        for(int i = 0; i < N; i++) {
            int l = lowerBound(sortedHigh, low[i]) + 1;
            int h = upperBound(sortedLow, high[i]);
            System.out.println(l + " " + h);
        }
    }
}
