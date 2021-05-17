package backjoon.may_17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Planet {
    int x, y, z, idx;
    Planet(int x, int y, int z, int idx) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.idx = idx;
    }
}

class Edge implements Comparable<Edge> {
    int from, to, cost;
    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

public class BOJ_2887 {
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Planet[] planets = new Planet[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(x,y,z,i);
        }

        // 최소 스패닝 트리를 만들어야 한다.
        // 만약, 모든 간선을 다 구해야 된다면 10만 * 10만 이므로 시간 초과가 날것이다.
        // 따라서 다른 방법이 필요하다.
        // 문제에서 비용을 생각해보면 min( |Xa - Xb|, |Ya - Yb|, |Za - Zb| ) 이므로
        // X, Y, Z 중 가장 작은 간선만 생각하면 된다.
        // 각 좌표로 정렬을 하고난 뒤 두개씩 묶어서 봤을 때 가장 작은 비용이 나올 수 있으므로 해당 비용이 간선의 최소 비용이다.
        // 이후 크루스칼 알고리즘을 통해 연결을 해준다.
        // 만약 X1 -> X2로 가는 비용과 Z1 -> Z4로 가는 비용이 같을 수 있지만, 우리가 도출해 낼 값은 최종 비용이기 때문에 위와 같은 조건은 생각하지 않아도 된다.

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.sort(planets, (a,b)-> a.x - b.x);
        for(int i = 1; i < N; i++)
            pq.add(new Edge(planets[i-1].idx, planets[i].idx, Math.abs(planets[i-1].x - planets[i].x)));

        Arrays.sort(planets, (a,b)-> a.y - b.y);
        for(int i = 1; i < N; i++)
            pq.add(new Edge(planets[i-1].idx, planets[i].idx, Math.abs(planets[i-1].y - planets[i].y)));

        Arrays.sort(planets, (a,b)-> a.z - b.z);
        for(int i = 1; i < N; i++)
            pq.add(new Edge(planets[i-1].idx, planets[i].idx, Math.abs(planets[i-1].z - planets[i].z)));


        parents = new int[N];
        for(int i = 0; i < N; i++) parents[i] = i;

        int ans = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(!union(cur.from, cur.to)) continue;
            ans += cur.cost;
        }
        System.out.println(ans);
    }

    private static int find(int target) {
        if(parents[target] == target) return target;
        return parents[target] = find(parents[target]);
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return false;
        parents[pb] = pa;
        return true;
    }
}
