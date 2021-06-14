package backjoon.june.D_14.BOJ_2233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// LCA를 이용한 풀이
// 해당 이진 수를 보고 트리를 만들고 해당 노드의 깊이 또한 저장(LCA를 사용하기 위함)
// 노드 저장시 해당 위치에서 들어가고 나가는 노드 번호를 따로 저장(nodeInfo)
// nodeInfo를 보고 어떤 노드가 선택되었는지 파악 후, 최소 공통 조상을 찾음
class Node {
    int num;
    int parent;
    int dep;
    List<Integer> child;
    Node(int num) {
        this.num = num;
        this.child = new ArrayList<>();
    }
}
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Node> nodes = new HashMap<>();
        for(int i = 1; i <= N; i++) nodes.put(i, new Node(i));
        String binary = br.readLine();
        int idx = 1;
        // 스택을 사용하여 이진수를 트리로 변환
        Deque<Node> stack = new ArrayDeque<>();
        int[] nodeInfo = new int[2*N];
        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '0') {
                Node cur = nodes.get(idx);
                if(stack.isEmpty()) {
                    cur.parent = idx - 1;
                }else {
                    Node p = stack.getFirst();
                    cur.parent = p.num;
                    p.child.add(idx);
                }
                cur.dep = stack.size()+1;
                stack.push(cur);
                nodeInfo[i] = idx;
                idx++;
            }else {
                if(!stack.isEmpty()) {
                    Node cur = stack.pop();
                    nodeInfo[i] = cur.num;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int i = nodeInfo[Integer.parseInt(st.nextToken())-1];
        int j = nodeInfo[Integer.parseInt(st.nextToken())-1];
        Node in = nodes.get(i);
        Node jn = nodes.get(j);

        while(true) {
            if(in.num == jn.num) {
                for(int k = 0; k < 2*N; k++) {
                    if(nodeInfo[k] == in.num) {
                        System.out.print((k + 1) + " ");
                    }
                }
                System.out.println();
                break;
            }
            // i의 깊이  > j의 깊이 -> i를 i의 부모로 갱신
            if(in.dep > jn.dep) in = nodes.get(in.parent);
            // i의 깊이 < j의 깊이 -> j를 j의 부모로 갱신
            else if(in.dep < jn.dep) jn = nodes.get(jn.parent);
            // 만약 둘의 깊이 같을 때는 둘 다 갱신
            else {
                in = nodes.get(in.parent);
                jn = nodes.get(jn.parent);
            }
        }
    }
}
