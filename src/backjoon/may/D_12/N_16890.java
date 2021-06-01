package backjoon.may.D_12;

import java.util.*;

public class N_16890 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String gu = sc.nextLine();
        String cube = sc.nextLine();

        List<Character> guArr = new ArrayList<>();
        List<Character> cubeArr = new ArrayList<>();

        int N = gu.length();
        for(int i = 0; i < N; i++) {
            guArr.add(gu.charAt(i));
            cubeArr.add(cube.charAt(i));
        }
        Collections.sort(guArr);
        cubeArr.sort(Collections.reverseOrder());

        System.out.println(guArr);
        System.out.println(cubeArr);

        char[] name = new char[N];

        int turn = 0;
        int front = 0, end = N - 1;
        int gs = 0, ge = (N+1) / 2 - 1, cs = 0, ce = N / 2 - 1;
        while(front <= end) {
            if(turn % 2 == 0) {
                if(guArr.get(gs) < cubeArr.get(cs)) name[front++] = guArr.get(gs++);
                else name[end--] = guArr.get(ge--);
            } else{
                if(guArr.get(gs) < cubeArr.get(cs)) name[front++] = cubeArr.get(cs++);
                else name[end--] = cubeArr.get(ce--);
            }
            turn++;
        }

        StringBuilder answer = new StringBuilder();
        for(char c : name) answer.append(c);
        System.out.println(answer.toString());
    }
}
