package backjoon.may_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_14248 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int start = Integer.parseInt(br.readLine());

        int cnt = 1;
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N+1];
        q.offer(start);
        visit[start] = true;

        while(!q.isEmpty()) {
            int cur = q.poll();
            int left = cur-A[cur];
            if(left > 0 && !visit[left]) {
                q.offer(left);
                visit[left] = true;
                cnt++;
            }
            int right = cur+A[cur];
            if(right <= N && !visit[right]) {
                q.offer(right);
                visit[right] = true;
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
