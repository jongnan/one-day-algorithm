package backjoon.july.d_19.boj_2900;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적합 또는 세그먼트 트리를 이용한 풀이
// https://www.acmicpc.net/blog/view/9 에 세그먼트 트리에 대해 잘 나와있음

// 기본적인 누적합으로 푸는것이 세그먼트 트리를 이용한것보다 빠름
// 어차피 Something 함수를 모두 돌려 놓고 시작을 하기 때문
// 만약 질의를 하는 과정에서 Something 함수를 돌렸을 때는 세그먼트가 더 빠를것으로 예상
// 왜? 기본 배열이 Something 함수를 돌린 값으로 바뀌고 누적합을 다시 구해야하기 때문
public class Main {
    static int N, K, h, size;
    static long[] tree;

    public static long init(int node, int start, int end) {
        if(start == end) return tree[node] = 0;
        return tree[node] =
            init(node * 2, start, (start+end)/2)
                + init(node * 2 + 1, (start+end)/2 + 1, end);
    }

    public static void update(int node, int start, int end, int index, int diff) {
        if(index < start || index > end) return;
        tree[node] += diff;
        if(start != end) {
            update(node * 2, start, (start+end)/2, index, diff);
            update(node * 2 + 1, (start+end)/2 + 1, end, index, diff);
        }
    }

    public static long sum(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return 0;
        if(left <= start && end <= right) return tree[node];
        return sum(node * 2, start, (start+end)/2, left, right)
            + sum(node * 2 + 1, (start+end)/2 + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        h = (int) Math.ceil(Math.log(N) / Math.log(2));
        size = (1 << (h+1));
        tree = new long[size];

        int[] cnt = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) cnt[Integer.parseInt(st.nextToken())]++;

        long[] a = new long[N];
        for(int i = 1; i < N; i++) {
            if(cnt[i] == 0) continue;
            int idx = 0;
            while(idx < N) {
                a[idx] += cnt[i];
                idx += i;
            }
        }

        long[] sums = new long[N+1];
        sums[1] = a[0];
        for(int i = 2; i <= N; i++) sums[i] = sums[i - 1] + a[i - 1];

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            sb.append(sums[right+1] - sums[left]);
            sb.append("\n");
        }
        System.out.println(sb.toString());

//        for(int i = 1; i < N; i++) {
//            if(cnt[i] == 0) continue;
//            int diff = cnt[i];
//            int idx = 0;
//            while(idx < N) {
//                update(1, 0, N-1, idx, diff);
//                idx += i;
//            }
//        }

//        StringBuilder sb = new StringBuilder();
//        int Q = Integer.parseInt(br.readLine());
//        while(Q-- > 0) {
//            st = new StringTokenizer(br.readLine());
//            int left = Integer.parseInt(st.nextToken());
//            int right = Integer.parseInt(st.nextToken());
//            sb.append(sum(1, 0, N-1, left, right));
//            sb.append("\n");
//        }
//        System.out.println(sb.toString());
    }
}
