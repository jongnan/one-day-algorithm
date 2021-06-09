package backjoon.june.D_09.BOJ_3151;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for(int i = 0; i < N; i++) nums[i] = sc.nextInt();
        Arrays.sort(nums);

        int[] same = new int[N];
        int idx = 0;
        for(int i = 1; i < N; i++) {
            if(nums[idx] != nums[i]) {
                idx = i;
            }
            same[i] = idx;
        }

        long ans = 0;
        for(int i = 0; i < N - 2; i++) {
            int s = i + 1;
            int e = N - 1;
            while(s < e) {
                long sum = nums[i] + nums[s] + nums[e];
                if(sum == 0) {
                    if(nums[s] == nums[e]) {
                        ans += e - s;
                    }else {
                        int sameIdx = same[e];
                        if(sameIdx <= s) sameIdx = s + 1;
                        ans += e - sameIdx + 1;
                    }
                    s++;
                }
                else if(sum > 0) e--;
                else s++;
            }
        }
        System.out.println(ans);
    }
}
