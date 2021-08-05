package backjoon.august.d_05.boj_9489;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트리를 이용한 풀이

// 그룹핑을 통해서 해당 위치의 부모를 저장해 둠
// 예제와 같이 다음 배열이 존재시에 확인
// (1) (3 4 5) (8 9) (15) (30 31 32)
//  0   1 1 1   2 2   3    4  4  4
// 연속된 수가 아닐 시 그룹 수를 늘려서 그룹핑을 하게 되면 해당 그룹의 부모 인덱스를 저장할 수 있음
// 즉, (8 9) 배열은 3의 자식이므로 2가 됨
// 이는 문제에서 3번째 조건 때문
//
// 사촌을 구하기 위해서는 부모들은 다르지만, 부모의 부모가 같으면 됨
// 이를 1~n 까지돌면서 확인
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        while(n != 0 && k != 0) {
            st = new StringTokenizer(br.readLine());

            // 숫자 저장
            int[] arr = new int[n+1];
            arr[0] = -1;
            // 부모의 인덱스 저장
            int[] parent = new int[n+1];
            parent[0] = -1;

            // 그룹의 넘버링
            int cnt = -1;
            // k의 인덱스
            int target = 0;
            for(int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if(arr[i] == k) target = i;
                // 숫자가 연속되지 않으면 넘버링을 올림
                if(arr[i] != arr[i-1] + 1) cnt++;
                // 넘버링
                parent[i] = cnt;
            }

            int ans = 0;
            for(int i = 1; i <= n; i++) {
                // 부모가 다르면서 부모의 부모는 같으면 사촌
                if(parent[i] != parent[target] && parent[parent[i]] == parent[parent[target]])
                    ans++;
            }
            System.out.println(ans);

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
        }
    }
}
