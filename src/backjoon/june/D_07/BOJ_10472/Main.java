package backjoon.june.D_07.BOJ_10472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// BFS를 이용한 풀이
class Node {
    char[][] map;
    int cnt;
    Node(char[][] map, int cnt) {
        this.map = map;
        this.cnt = cnt;
    }
}
public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] dr = new int[] {-1,1,0,0};
    public static int[] dc = new int[] {0,0,-1,1};
    public static boolean check(char[][] map) {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] != '.') return false;
            }
        }
        return true;
    }
    public static String mapToStr(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                sb.append(map[i][j]);
            }
        }
        return sb.toString();
    }
    public static char[][] copy(char[][] map) {
        char[][] m = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                m[i][j] = map[i][j];
            }
        }
        return m;
    }
    public static int bfs(char[][] map) {
        Set<String> visit = new HashSet<>();
        Deque<Node> q = new ArrayDeque();
        String mapStr = mapToStr(map);
        visit.add(mapStr);
        q.offer(new Node(map, 0));
        if(check(map)) return 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    char[][] newMap = copy(cur.map);
                    newMap[i][j] = newMap[i][j] == '.' ? '*' : '.';
                    for(int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;
                        newMap[nr][nc] = newMap[nr][nc] == '.' ? '*' : '.';
                    }
                    String str = mapToStr(newMap);
                    if(check(newMap)) return cur.cnt + 1;
                    if(visit.contains(str)) continue;
                    visit.add(str);
                    q.offer(new Node(newMap, cur.cnt + 1));
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        int P = Integer.parseInt(br.readLine());
        while(P-- > 0) {
            char[][] map = new char[3][3];
            for(int i = 0; i < 3; i++) {
                String row = br.readLine();
                for(int j = 0; j < row.length(); j++) {
                    map[i][j] = row.charAt(j);
                }
            }
            System.out.println(bfs(map));
        }
    }
}
