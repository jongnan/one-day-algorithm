package backjoon.may.D_28.BOJ_8980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 그리디 알고리즘을 사용하여 풀이
// 여기서 중요한 점은 시작점을 기준으로 보면 안되고 끝나는 점을 기준으로 봐야 한다는 것
// 6 60
// 5
// 1 2 30
// 2 5 70
// 5 6 60
// 3 4 40
// 1 6 40
// 다음과 같은 입력이 주어졌을 때 시작점을 기준으로 받으면 1번 마을에서
// 2번으로 가는 것 30, 6번으로 가는것 30을 선택하게 됨
// 트럭은 6번에 도착할 때까지 30을 계속해서 가져가야 하므로 이는 비효율적
// 따라서 도착을 기준으로 정렬하며 그다음은 시작점을 보면 됨
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        int[][] boxes = new int[M][3];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            boxes[i][0] = Integer.parseInt(st.nextToken());
            boxes[i][1] = Integer.parseInt(st.nextToken());
            boxes[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(boxes, (a,b) -> {
            if(a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int ans = 0;
        // 각 마을에서 담을 수 있는 박스를 저장하는 공간
        int[] truck = new int[N+1];
        for(int i = 0; i < boxes.length; i++) {
            int s = boxes[i][0];
            int e = boxes[i][1];
            int w = boxes[i][2];

            int pw = w;
            int maxCapacity = 0;
            // 시작하는 지점부터 도착하는 지점 바로 전까지 가장 큰 수를 구해야함
            // 이는 당연히 전체 무게를 넘길 수 없으므로 최대 무게를 측정하고 그에 맞게 무게를 들어야 함
            for(int j = s; j < e; j++) maxCapacity = Math.max(maxCapacity, truck[j]);
            if(maxCapacity + w > C) pw = C - maxCapacity;

            // 더할 무게를 측정했다면 시작 ~ 도착-1 까지 모든 경로에 해당 무게를 더해줌
            for(int j = s; j < e; j++) truck[j] += pw;
            ans += pw;
        }
        System.out.println(ans);
    }
}
