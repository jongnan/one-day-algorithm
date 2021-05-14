package backjoon.may_12;

import java.util.Scanner;

class Color {
    int r, g, b;

    Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}

public class N_20950 {
    public static Color[] colors;
    public static int R, G, B, N;
    public static int ans = Integer.MAX_VALUE;

    public static void selectColor(int idx, int cnt, Color cur) {
        if(cnt >= 2) {
            int diff = Math.abs(cur.r / cnt - R) + Math.abs(cur.g / cnt - G) + Math.abs(cur.b / cnt - B);
            ans = Math.min(ans, diff);
            if(cnt == 7) return;
        }

        for(int i = idx; i < N; i++) {
            selectColor(i + 1, cnt + 1, new Color(cur.r + colors[i].r, cur.g + colors[i].g, cur.b + colors[i].b));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        colors = new Color[N];

        for(int i = 0; i < N; i++) {
            int r = scanner.nextInt();
            int g = scanner.nextInt();
            int b = scanner.nextInt();
            colors[i] = new Color(r, g, b);
        }
        R = scanner.nextInt();
        G = scanner.nextInt();
        B = scanner.nextInt();

        selectColor(0,0, new Color(0,0,0));

        System.out.println(ans);
    }
}

