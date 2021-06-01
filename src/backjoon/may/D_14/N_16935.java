package backjoon.may.D_14;

import java.util.*;

public class N_16935 {

    static int[][] arr;
    static int N, M;

    public static void calculator(int cmd) {
        if(cmd == 1) {
            for(int r = 0; r < N / 2; r++) {
                for(int c = 0; c < M; c++) {
                    int temp = arr[r][c];
                    arr[r][c] = arr[N-1-r][c];
                    arr[N-1-r][c] = temp;
                }
            }
        }else if(cmd == 2) {
            for(int c = 0; c < M / 2; c++) {
                for(int r = 0; r < N; r++) {
                    int temp = arr[r][c];
                    arr[r][c] = arr[r][M-1-c];
                    arr[r][M-1-c] = temp;
                }
            }
        }else if(cmd == 3) {
            int[][] newArr = new int[M][N];
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) {
                    newArr[c][N-1-r] = arr[r][c];
                }
            }
            int temp = N;
            N = M;
            M = temp;
            arr = newArr;
        }else if(cmd == 4) {
            int[][] newArr = new int[M][N];
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) {
                    newArr[M-1-c][r] = arr[r][c];
                }
            }
            int temp = N;
            N = M;
            M = temp;
            arr = newArr;
        }else if(cmd == 5) {
            for(int r = 0; r < N/2; r++) {
                for(int c = 0; c < M/2; c++) {
                    int temp = arr[r][c];
                    arr[r][c] = arr[r+N/2][c];
                    arr[r+N/2][c] = arr[r+N/2][c+M/2];
                    arr[r+N/2][c+M/2] = arr[r][c+M/2];
                    arr[r][c+M/2] = temp;
                }
            }
        }else {
            for(int r = 0; r < N/2; r++) {
                for(int c = 0; c < M/2; c++) {
                    int temp = arr[r][c];
                    arr[r][c] = arr[r][c+M/2];
                    arr[r][c+M/2] = arr[r+N/2][c+M/2];
                    arr[r+N/2][c+M/2] = arr[r+N/2][c];
                    arr[r+N/2][c] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int R = sc.nextInt();

        arr = new int[N][M];
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                arr[r][c] = sc.nextInt();
            }
        }

        while(R > 0) {
            int cmd = sc.nextInt();
            calculator(cmd);
            R--;
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
