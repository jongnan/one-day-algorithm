package backjoon.june.D_15.BOJ_20440;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 누적합과 범위 압축을 사용하여 풀이

// int형이 21억개가 존재하므로 누적합만 사용한다면 메모리가 초과
// 따라서 구간을 압축해야 함
// 시작과 끝을 구분 짓지 않고 각 구간 별로 볼 수 있음
// 예제 1의 경우, [2,4] [4,6] [6,10] [10,16] 으로 구간을 나뉘어짐
// 따라서 중복을 제외한 시간이 N개가 주어진다면 N-1개의 구간으로 나누어질 수 있음
// 중복 제거는 Set을 사용하고 구간을 순서대로 놓기 위해 List 형태로 변경 후 정렬
// 이후 미리 저장해둔 시작과 끝을 순차적으로 해당 구간에다 더해줌
// 구간 합을 구한 배열을 보고 최대 모기의 수를 카운트해주며 범위를 측정
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] sArr = new int[N];
        int[] eArr = new int[N];
        Set<Integer> sector = new HashSet<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sArr[i] = Integer.parseInt(st.nextToken());
            eArr[i] = Integer.parseInt(st.nextToken());
            sector.add(sArr[i]);
            sector.add(eArr[i]);
        }
        List<Integer> sectorList = new ArrayList<>(sector);
        sectorList.sort(Comparator.comparingInt(a -> a));
        int[] sum = new int[sectorList.size()];
        for(int i = 0; i < N; i++) {
            int start = Collections.binarySearch(sectorList, sArr[i]);
            int end = Collections.binarySearch(sectorList, eArr[i]);
            for(int t = start; t < end; t++) sum[t]++;
        }
        int cnt = 0;
        int sIdx = -1, eIdx = -1;
        for(int i = 0; i < sectorList.size(); i++) {
            if(cnt < sum[i]) {
                cnt = sum[i];
                sIdx = i;
                eIdx = i;
            }
            if(cnt == sum[i] && i-1 == eIdx) eIdx = i;
        }
        System.out.println(cnt);
        System.out.println(sectorList.get(sIdx) + " " + sectorList.get(eIdx+1));
    }
}
