package backjoon.june.D_03.BOJ_12764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 우선순위 큐를 이용한 풀이
// 현재 자리, 남아있는 자리, 사용하고 있는 자리에 대해 정의
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int seat = 0;
        // 자리별 사용 빈도수
        int[] seatCnt = new int[N+1];
        // 남아있는 PC 목록
        PriorityQueue<Integer> leftSeat = new PriorityQueue<>();
        // 사용중인 PC 목록 (종료시간, PC 번호)
        PriorityQueue<int[]> useSeat = new PriorityQueue<>((a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        int[][] times = new int[N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }
        // 시작시간 순, 끝나는 순으로 정렬
        Arrays.sort(times, (a,b) -> {
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        // 전체 시간을 돌기
        for(int i = 0; i < N; i++) {
            // 사용하고 있는 자리의 끝나는 시간과 시작시간을 비교하여 끝나는 사람을 체크
            while(!useSeat.isEmpty()) {
                if(useSeat.peek()[0] <= times[i][0]) {
                    int[] seatInfo = useSeat.poll();
                    // 끝난 자리는 다시 사용하지 않는 자리 큐에 넣기
                    leftSeat.offer(seatInfo[1]);
                }else {
                    break;
                }
            }
            // 남는 자리가 없을 경우 총 자리 수를 증가
            if(leftSeat.isEmpty()) {
               useSeat.offer(new int[] {times[i][1], seat});
               seatCnt[seat++]++;
            }
            // 남는 자리수가 있을 때는 해당 자리의 빈도수를 증가
            else {
               int seatNum = leftSeat.poll();
               useSeat.offer(new int[] {times[i][1], seatNum});
               seatCnt[seatNum]++;
            }
        }
        System.out.println(seat);
        for(int i = 0; i < seat; i++) {
            System.out.print(seatCnt[i] + " ");
        }
        System.out.println();
    }
}
