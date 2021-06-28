package backjoon.june.D_28.BOJ_12931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그리디 알고리즘을 이용한 풀이

// 0에서 해당 수를 만드나 해당 수에서 0을 만드나 똑같은 이치
// 따라서 모든 수를 0으로 만드는 최소 값을 찾아야 함

// 연산은 총 두개로 모든 값을 2로 나누거나 -1을 각각 할 수 있음
// 그렇기에 만약 모든 수가 짝수여서 한번에 나누기를 하던가
// 홀수가 섞여있다면 해당 수를 -1씩 진행하여 짝수를 만들어야 함
// 여기서 그리디하게 접근 할 수 있는 이유는 모든 수를 한번에 2로 나누었을 때가 더 빠르기 때문
public class Main {
    public static boolean isAllZero(int[] a) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] != 0) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int ans = 0;
        while(!isAllZero(arr)) {
            for(int i = 0; i < N; i++) {
                if(arr[i] % 2 == 0) continue;
                // 홀수가 섞여있을 경우 하나씩 빼서 짝수로 만듬
                arr[i]--;
                ans++;
            }
            // 모든 홀수를 짝수로 만들었기 때문에 바로 2로 나눌 수 있음
            // 하지만 모든 수가 0일 때는 이를 진행하면 안됌
            if(isAllZero(arr)) break;
            for(int i = 0; i < N; i++) {
                arr[i] /= 2;
            }
            ans++;
        }
        System.out.println(ans);
    }
}
