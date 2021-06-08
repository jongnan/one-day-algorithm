package backjoon.june.D_08.BOJ_5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Trie 구조를 통해 풀이
// 0~9까지 이므로 배열 10개를 만들고 그 안에 서브 노드를 추가 및 리프 노드인지 확인
class Node {
    Node[] child = new Node[10];
    boolean isLeaf = false;
}
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] str = new String[n];
            for(int i = 0; i < n; i++) str[i] = br.readLine();
            Arrays.sort(str);

            Node root = new Node();
            boolean isAvail = true;
            for(int i = 0; i < n; i++) {
                Node cur = root;
                for(int j = 0; j < str[i].length(); j++) {
                    int num = str[i].charAt(j) - '0';
                    // 만약 해당 위치의 노드가 리프 노드인데, 만약 하위 노드가 존재한다면 중복
                    if(cur.isLeaf && cur.child[num] != null) {
                        isAvail = false;
                        break;
                    }
                    if(cur.child[num] == null) cur.child[num] = new Node();
                    if(j == str[i].length() - 1) cur.isLeaf = true;
                    else cur = cur.child[num];
                }
                if(!isAvail) break;
            }

            if(isAvail) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
