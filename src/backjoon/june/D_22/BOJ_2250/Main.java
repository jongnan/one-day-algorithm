package backjoon.june.D_22.BOJ_2250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 중위순회를 이용한 풀이
//
// 각 노드의 열과 행은 루트 노드를 기반으로 중위 순회를 돌면 계산 가능
// 맨 좌측에 있는 노드가 1번 노드가 될 것이고 맨 우측에 있는 노드가 N번째 노드가 됨
// 따라서 좌 -> 루 -> 오 순으로 깊이 우선탐색을 통해 순회하면서 해당 노드의 깊이와 위치를 찾아냄
//
// 여기서 중요한 점은 1번 노드가 반드시 루트 노드란 것을 보장하고 있지 않음
// 따라서 루트노드를 먼저 찾고 이후에 중위순회를 돌려 해당 위치를 찾아야 함
//
// 이후 깊이마다 제일 좌측과 우측을 측정하여 해당 깊이의 최대 너비를 측정
class Node {
    int left, right;
    int row, col;
    int parent = -1;
}
public class Main {
    static Node[] nodes;
    static int cnt = 1;
    static int maxDep = 1;
    public static void setPosition(int dep, int cur) {
        if(nodes[cur].left != -1) setPosition(dep + 1, nodes[cur].left);
        nodes[cur].col = cnt++;
        nodes[cur].row = dep;
        maxDep = Math.max(maxDep, dep);
        if(nodes[cur].right != -1) setPosition(dep + 1, nodes[cur].right);
    }
    public static int findRoot(int cur) {
        if(nodes[cur].parent == -1) return cur;
        return findRoot(nodes[cur].parent);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nodes = new Node[N+1];
        for(int i = 0; i <= N; i++) nodes[i] = new Node();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            nodes[num].left = l;
            if(l != -1) nodes[l].parent = num;
            nodes[num].right = r;
            if(r != -1) nodes[r].parent = num;
        }
        int root = findRoot(1);
        setPosition(1, root);
        int[][] edges = new int[maxDep+1][2];
        for(int i = 1; i <= maxDep; i++) edges[i][0] = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++ ){
            int curDep = nodes[i].row;
            edges[curDep][0] = Math.min(edges[curDep][0], nodes[i].col);
            edges[curDep][1] = Math.max(edges[curDep][1], nodes[i].col);
        }
        int maxDist = 0, ansDep = 1;
        for(int i = 1; i <= maxDep; i++) {
            int curDist = edges[i][1] - edges[i][0] + 1;
            if(maxDist < curDist) {
                maxDist = curDist;
                ansDep = i;
            }
        }
        System.out.println(ansDep + " " + maxDist);
    }
}
