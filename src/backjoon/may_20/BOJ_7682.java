package backjoon.may_20;

import java.util.Scanner;

public class BOJ_7682 {
    private static boolean checkWin(char target, String map) {
        for(int i = 0; i < 9; i += 3) {
            if( map.charAt(i) == target &&
                map.charAt(i) == map.charAt(i+1) &&
                map.charAt(i) == map.charAt(i+2))
                return true;
        }

        for(int i = 0; i < 3; i++) {
            if( map.charAt(i) == target &&
                map.charAt(i) == map.charAt(i+3) &&
                map.charAt(i) == map.charAt(i+6))
                return true;
        }

        if(map.charAt(0) == target &&
        map.charAt(0) == map.charAt(4) &&
        map.charAt(0) == map.charAt(8)) return true;

        if(map.charAt(2) == target &&
        map.charAt(2) == map.charAt(4) &&
        map.charAt(2) == map.charAt(6)) return true;

        return false;
    }

    private static boolean isValid(String map) {
        int cntX = 0;
        int cntO = 0;

        for(char c : map.toCharArray()) {
            if(c == 'X') cntX++;
            else if(c == 'O') cntO++;
        }
        //X가 놓을 수 있는 수는 총 5개, O가 놓을 수 있는 수는 총 4개이다.
        if(cntX > 5 || cntO > 4) return false;
        //O는 X보다 더 많이 둘 수 없다.
        if(cntO > cntX) return false;
        //둘의 차이는 절대 1개이상 나지 않는다.
        if(cntX - cntO > 1) return false;

        boolean isOWin = checkWin('O', map);
        boolean isXWin = checkWin('X', map);

        // X가 한 턴 더 진행했을 경우, O가 끝낼 수 있는 상황이였는지 확인
        if(cntX > cntO && isOWin) return false;
        // 서로 놓은 개수가 같을 경우, X가 끝낼 수 있는 상황이였는지 확인
        if(cntX == cntO && isXWin) return false;

        // 둘다 빙고기 아닌데 끝났을 경우 확인
        if(cntX < 5 && cntO < 4 && !isXWin && !isOWin) return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String tc = sc.nextLine();
        while(!tc.equals("end")) {
            if(isValid(tc)) System.out.println("valid");
            else System.out.println("invalid");
            tc = sc.nextLine();
        }
    }
}
