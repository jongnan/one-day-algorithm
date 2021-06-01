package backjoon.may.D_28.BOJ_14675;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트리의 특성을 생각
// 단절점과 단절선 모두 해당 위치에 있는 노드 혹은 간선을 없앴을 때 그래프가 두개로 나뉨
// 트리의 경우, 모든 간선은 단절선에 해당 왜? 우회로가 없기 때문
// 단절점은 리프 노드 혹은 루트 노드에 해당하는 것 빼고 모든 노드가 단절점에 해당
// 따라서 단절선의 경우 모두 yes, 단절점은 indegree 배열을 구한 뒤 간선이 1개인 경우만 찾아서 no
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] indegree = new int[N+1];
        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            indegree[Integer.parseInt(st.nextToken())]++;
            indegree[Integer.parseInt(st.nextToken())]++;
        }

        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            String ans = "yes";
            if(t == 1 && indegree[k] == 1) ans = "no";
            System.out.println(ans);
        }
    }
}
