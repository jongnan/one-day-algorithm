package backjoon.july.d_26.boj_2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 이분탐색을 이용한 풀이

// 최댓값을 임의로 지정하고 이로 만들 수 있는 그룹의 수를 측정
// 최댓값이 될 수 있는 가장 작은 값은 받은 숫자 중 가장 큰 수
// 최댓값이 될 수 있는 가장 큰 값은 모든 수를 더한 값
// 따라서 l = 입력 받은 수 중 가장 큰 값, r = 모든 값을 더한 값
// 이후 이분탐색을 통해 최대값을 측정

// 예시
// 4 6
// 1 1 1 4 1 1
// 답
// 17
// 2 1 1 2 혹은 1 2 1 2 혹은 3 1 1 1

// 따라서 최대값이 만들어질 수 있도록 최대한 묶되 M개를 만족할 수 있도록 체크하면서 넣음

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        int l = 0, r = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            r += nums[i];
            l = Math.max(l, nums[i]);
        }

        while(l < r) {
            int m = (l + r) / 2;
            int s = 0;
            int unit = 1;
            for(int i = 0; i < N; i++) {
                s += nums[i];
                if(s > m) {
                    s = nums[i];
                    unit++;
                }
            }
            if(unit > M) l = m + 1;
            else r = m;
        }
        System.out.println(l);

        List<Integer> ans = new ArrayList<>();
        int idx = 0;
        while(idx < N) {
            // 만약 여태까지 만든 그룹의 수와 아직 체크하지 않은 값들의 수를 더했을 때 M이 된다면 나머지 수들은 개별로 묶임
            if(ans.size() + (N - idx) == M) break;
            int sum = 0;
            int cnt = 0;
            // 개별로 묶이지 않은 값들은 최대한 묶어 최대값이 넘지 않도록 묶음
            while(idx < N && ans.size() + (N - idx) >= M && sum + nums[idx] <= l) {
                sum += nums[idx];
                cnt++;
                idx++;
            }
            // 묶은 값은 따로 저장
            ans.add(cnt);
        }
        // 남은 수들을 개별로 묶기
        while(idx++ < N) ans.add(1);

        for(int i = 0; i < ans.size(); i++) System.out.print(ans.get(i) + " ");
        System.out.println();
    }
}
