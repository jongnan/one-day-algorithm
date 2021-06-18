package backjoon.june.D_18.BOJ_19583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// Set과 문자열 비교를 통한 풀이

// 채팅 시간 문자열을 받으면서 시작 시간 문자열보다 작거나 같은 사람을 찾아 Set에 넣음
// 채팅 시간 문자열이 개총이 끝난 시각보다 크거나 같으면서 스트림밍 종료 시간보다 작거나 같으면
// 입장한 사람들을 저장해 놓은 Set을 확인
// 여기서 주의할 점은 출첵을 이미 한 사람이 또 채팅을 칠 수 있으므로 출첵한 인원들을 다른 Set에
// 저장 후에 이도 같이 체크
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        String end = st.nextToken();
        String endStreaming = st.nextToken();

        Set<String> join = new HashSet<>();
        Set<String> check = new HashSet<>();
        String input = "";
        int cnt = 0;
        while((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            String time = st.nextToken();
            String name = st.nextToken();
            // 채팅 시간과 개총 시작 시간을 비교하여 입장 확인
            if(time.compareTo(start) <= 0) {
                join.add(name);
                continue;
            }
            // 채팅 시간과 개총 종료 시간 스트리밍 종료 시간 비교하여 출첵 확인
            if(time.compareTo(end) >= 0 && time.compareTo(endStreaming) <= 0) {
                // 입장을 확인과 동시에 출첵 중복 확인
                if(join.contains(name) && !check.contains(name)) {
                    check.add(name);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
