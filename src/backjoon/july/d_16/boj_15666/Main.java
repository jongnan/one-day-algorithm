package backjoon.july.d_16.boj_15666;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백트래킹을 이용한 풀이

// 중복을 피해야 하므로 입력부터 같은 수는 받지 않음
// 이후 오름차순으로 미리 정렬한 뒤 백트래킹
public class Main {
    static int N, M;
    static List<Integer> nums;
    public static void backtracking(int d, List<Integer> n) {
        if(n.size() == M) {
            for(int i = 0; i < n.size(); i++) System.out.print(n.get(i) + " ");
            System.out.println();
            return;
        }

        for(int i = d; i < nums.size(); i++) {
            n.add(nums.get(i));
            backtracking(i, n);
            n.remove(n.size() - 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(nums.contains(num)) continue;
            nums.add(num);
        }

        nums.sort(Comparator.comparingInt(a -> a));

        backtracking(0, new ArrayList<>());
    }
}
