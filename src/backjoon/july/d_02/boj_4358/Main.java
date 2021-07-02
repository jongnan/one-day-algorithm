package backjoon.july.d_02.boj_4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

// 트리맵을 이용한 풀이

// 나무를 사전 순으로 출력해야 하므로 key를 정렬해주는 트리 맵을 사용
// 나무 별로 카운팅을 해주고 전체 나무의 수에 몇 퍼센트인지 확인
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tree = "";
        Map<String, Integer> treeInfo = new TreeMap<>();
        int totCnt = 0;
        while((tree = br.readLine()) != null) {
            totCnt++;
            if(treeInfo.containsKey(tree)) treeInfo.put(tree, treeInfo.get(tree) + 1);
            else treeInfo.put(tree, 1);
        }

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> e : treeInfo.entrySet()) {
            sb.append(e.getKey());
            sb.append(" ");
            double avg = ((double)e.getValue() / totCnt) * 100;
            sb.append(String.format("%.4f", avg));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
