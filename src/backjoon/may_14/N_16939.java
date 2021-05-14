package backjoon.may_14;

import java.util.*;

public class N_16939 {
    static int[] cube = new int[25];

    public static boolean isAvail(int[] c) {
        for(int i = 1; i <= 24; i += 4) {
            if(c[i] != c[i+1] || c[i] != c[i+2] || c[i] != c[i+3]) return false;
        }
        return true;
    }

    public static boolean turnCube(int cmd) {
        int[] nextCube = new int[25];
        for(int i = 1; i <= 24; i++) nextCube[i] = cube[i];

        if(cmd == 1) {
            int temp = nextCube[1];
            nextCube[1] = nextCube[22];
            nextCube[22] = nextCube[9];
            nextCube[9] = nextCube[5];
            nextCube[5] = temp;

            temp = nextCube[3];
            nextCube[3] = nextCube[24];
            nextCube[24] = nextCube[11];
            nextCube[11] = nextCube[7];
            nextCube[7] = temp;
        }else if(cmd == 2) {
            int temp = nextCube[1];
            nextCube[1] = nextCube[5];
            nextCube[5] = nextCube[9];
            nextCube[9] = nextCube[22];
            nextCube[22] = temp;

            temp = nextCube[3];
            nextCube[3] = nextCube[7];
            nextCube[7] = nextCube[11];
            nextCube[11] = nextCube[24];
            nextCube[24] = temp;
        }else if(cmd == 3) {
            int temp = nextCube[2];
            nextCube[2] = nextCube[6];
            nextCube[6] = nextCube[10];
            nextCube[10] = nextCube[21];
            nextCube[21] = temp;

            temp = nextCube[4];
            nextCube[4] = nextCube[8];
            nextCube[8] = nextCube[12];
            nextCube[12] = nextCube[23];
            nextCube[23] = temp;
        }else if(cmd == 4) {
            int temp = nextCube[2];
            nextCube[2] = nextCube[21];
            nextCube[21] = nextCube[10];
            nextCube[10] = nextCube[6];
            nextCube[6] = temp;

            temp = nextCube[4];
            nextCube[4] = nextCube[23];
            nextCube[23] = nextCube[12];
            nextCube[12] = nextCube[8];
            nextCube[8] = temp;
        }else if(cmd == 5) {
            int temp = nextCube[5];
            nextCube[5] = nextCube[17];
            nextCube[17] = nextCube[21];
            nextCube[21] = nextCube[13];
            nextCube[13] = temp;

            temp = nextCube[6];
            nextCube[6] = nextCube[18];
            nextCube[18] = nextCube[22];
            nextCube[22] = nextCube[14];
            nextCube[14] = temp;
        }else if(cmd == 6) {
            int temp = nextCube[5];
            nextCube[5] = nextCube[13];
            nextCube[13] = nextCube[21];
            nextCube[21] = nextCube[17];
            nextCube[17] = temp;

            temp = nextCube[6];
            nextCube[6] = nextCube[14];
            nextCube[14] = nextCube[22];
            nextCube[22] = nextCube[18];
            nextCube[18] = temp;
        }else if(cmd == 7) {
            int temp = nextCube[7];
            nextCube[7] = nextCube[19];
            nextCube[19] = nextCube[23];
            nextCube[23] = nextCube[15];
            nextCube[15] = temp;

            temp = nextCube[8];
            nextCube[8] = nextCube[20];
            nextCube[20] = nextCube[24];
            nextCube[24] = nextCube[16];
            nextCube[16] = temp;
        }else if(cmd == 8) {
            int temp = nextCube[7];
            nextCube[7] = nextCube[15];
            nextCube[15] = nextCube[23];
            nextCube[23] = nextCube[19];
            nextCube[19] = temp;

            temp = nextCube[8];
            nextCube[8] = nextCube[16];
            nextCube[16] = nextCube[24];
            nextCube[24] = nextCube[20];
            nextCube[20] = temp;
        } else if(cmd == 9) {
            int temp = nextCube[3];
            nextCube[3] = nextCube[16];
            nextCube[16] = nextCube[10];
            nextCube[10] = nextCube[17];
            nextCube[17] = temp;

            temp = nextCube[4];
            nextCube[4] = nextCube[14];
            nextCube[14] = nextCube[9];
            nextCube[9] = nextCube[19];
            nextCube[19] = temp;
        }
        else if(cmd == 10) {
            int temp = nextCube[3];
            nextCube[3] = nextCube[17];
            nextCube[17] = nextCube[10];
            nextCube[10] = nextCube[16];
            nextCube[16] = temp;

            temp = nextCube[4];
            nextCube[4] = nextCube[19];
            nextCube[19] = nextCube[9];
            nextCube[9] = nextCube[14];
            nextCube[14] = temp;
        } else if(cmd == 11) {
            int temp = nextCube[1];
            nextCube[1] = nextCube[13];
            nextCube[13] = nextCube[11];
            nextCube[11] = nextCube[18];
            nextCube[18] = temp;

            temp = nextCube[2];
            nextCube[2] = nextCube[15];
            nextCube[15] = nextCube[12];
            nextCube[12] = nextCube[20];
            nextCube[20] = temp;
        } else {
            int temp = nextCube[1];
            nextCube[1] = nextCube[18];
            nextCube[18] = nextCube[11];
            nextCube[11] = nextCube[13];
            nextCube[13] = temp;

            temp = nextCube[2];
            nextCube[2] = nextCube[20];
            nextCube[20] = nextCube[12];
            nextCube[12] = nextCube[15];
            nextCube[15] = temp;
        }
        return isAvail(nextCube);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 1; i <= 24; i++) cube[i] = sc.nextInt();
        boolean ans = false;
        for(int i = 1; i <= 12; i++) {
            ans = turnCube(i);
            if(ans) break;
        }
        int answer = ans ? 1:0;
        System.out.println(answer);
    }
}
