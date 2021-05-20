package backjoon.may_20;

import java.util.Scanner;

public class BOJ_10775 {
    // 그리디로 풀이
    // 간단하게 현재 들어온 비행기가 원하는 게이트에 들어가고 만약 그럴수 없다면,
    // 해당 비행기는 원하는 게이트보다 작은 게이트에 하나씩 도킹 시도
    // 만약 도킹할 수 없다면 바로 끝내고 여태 도킹했던 비행기의 수를 출력
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int G = sc.nextInt();
//        int P = sc.nextInt();
//        boolean[] gate = new boolean[G+1];
//        int cnt = 0;
//        for(int i = 0; i < P; i++) {
//            int cur = sc.nextInt();
//            while(cur > 0) {
//                if(!gate[cur]) {
//                    gate[cur] = true;
//                    cnt++;
//                    break;
//                }
//                cur--;
//            }
//            if(cur == 0) break;
//        }
//        System.out.println(cnt);
//    }

    // Union-Find로 풀이
    // 위 풀이에서는 만약 도킹할 수 없을 경우 일일히 체크를 하지만,
    // 이 시간을 줄이기 위해 Union-Find를 사용
    // 동일한 위치에 접근했을 때 다음에 들어갈 위치를 미리 지정해두는 것
    // 즉, g[4] = -1 이였고 4번 게이트에 비행기가 온다면, g[4] = 3
    // 이 때 또다시 4번 게이트에 비행기가 온다면 g[4]를 보고 g[3]에 2를 기록
    // 이러한 식으로 0까지 가며, 0이 나왔을 경우 도킹할 수 없는 상태이므로 중단
    static int[] p;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();
        int P = sc.nextInt();

        p = new int[G+1];
        for(int i = 0; i <= G; i++) p[i] = -1;

        int cnt = 0;
        for(int i = 0; i < P; i++) {
            int cur = find(sc.nextInt());
            if(cur == 0) break;
            cnt++;
            union(cur, cur - 1);
        }
        System.out.println(cnt);
    }

    private static int find(int target) {
        if(p[target] < 0) return target;
        return p[target] = find(p[target]);
    }

    private static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return;
        p[ra] = rb;
    }
}
