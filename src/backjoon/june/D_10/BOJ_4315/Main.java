package backjoon.june.D_10.BOJ_4315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 그래프 탐색을 통한 풀이
// 리프 노드에서부터 자신이 가지고 있는 구슬의 개수를 보고 판별

// 리프 노드의 구슬의 개수를 판별, 3가지 경우의 수가 발생
//      1. 현재 구슬의 개수가 0 일 때
//          구슬이 존재하지 않으므로 부모에게서 구슬을 받아와야 함(움직임 + 1)
//      2. 현재 구슬의 개수가 1이상 일 때
//          구슬이 넘쳐나므로 부모에게 자신의 구슬을 제외하고 보냄(움직임 + 남는 구슬의 개수)
//      3. 현재 구슬의 개수가 음수 일때
//          이때도 부모에게서 구슬을 받아와야 함(움직임 + (음수값 * -1) + 1)
//
// 마이너스가 발생할 수 있는 경우는 부모노드가 0일 때 자식노드에서 구슬이 필요할 때
// 즉, 대출의 형태로 볼 수 있으며 상위 노드에서 구슬을 빌려와 매꾸는 형식
//
// 해당 리프노드에 작업이 끝났다면 필요가 없으므로 부모노드에서 이를 제외
// 제외 시키고나서 부모 노드들 중에 리프노드가 될 수 있기 때문에 이를 다시 리프노드에 추가
// 이를 루트 노드로 갈 때까지 반복
class Box {
    int ball;
    int parent;
    List<Integer> child = new ArrayList<>();
}
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) break;
            Box[] boxes = new Box[n+1];
            for(int i = 0; i <= n; i++) boxes[i] = new Box();
            List<Integer> leaf = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                boxes[v].ball = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                if(d == 0) leaf.add(v);
                for(int j = 0; j < d; j++) {
                    int c = Integer.parseInt(st.nextToken());
                    boxes[v].child.add(c);
                    boxes[c].parent = v;
                }
            }
            int move = 0;
            List<Integer> nextLeaf;
            while(leaf.size() != 1) {
                nextLeaf = new ArrayList<>();
                for(int i = 0; i < leaf.size(); i++) {
                    Box cur = boxes[leaf.get(i)];
                    if(cur.ball <= 0) {
                        move += -cur.ball + 1;
                    }else {
                        move += cur.ball - 1;
                    }
                    boxes[cur.parent].ball += cur.ball - 1;
                    boxes[cur.parent].child.remove(leaf.get(i));
                    if(boxes[cur.parent].child.isEmpty()) nextLeaf.add(cur.parent);
                }
                leaf = nextLeaf;
            }
            System.out.println(move);
        }
    }
}
