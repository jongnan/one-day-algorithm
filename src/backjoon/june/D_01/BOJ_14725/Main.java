package backjoon.june.D_01.BOJ_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Tree 구조를 Map을 통해 풀이
// 단순 트리를 구성하고 트리를 출력하면 되는 문제
// 여기서 신경 써야할 부분은 출력을 사전순
// 따라서 TreeMap을 사용하여 사전순으로 정렬
class Node {
    String name;
    int depth;
    Map<String, Node> child;

    Node(String name, int depth) {
        this.name = name;
        this.depth = depth;
        this.child = new TreeMap<>(Comparator.naturalOrder());
    }
}

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public final static String DASH = "--";
    public static void print(Map<String, Node> map) {
        for(Map.Entry<String, Node> entry : map.entrySet()) {
            Node cur = entry.getValue();
            for(int i = 0; i < cur.depth; i++) System.out.print(DASH);
            System.out.println(cur.name);
            if(!cur.child.isEmpty()) print(cur.child);
        }
    }
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<String, Node> cave = new TreeMap<>(Comparator.naturalOrder());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            Map<String, Node> curDepCave = cave;
            for(int dep = 0; dep < m; dep++) {
                String name = st.nextToken();
                if(!curDepCave.containsKey(name)) {
                    Node n = new Node(name, dep);
                    curDepCave.put(name, n);
                }
                curDepCave = curDepCave.get(name).child;
            }
        }
        print(cave);
    }
}
