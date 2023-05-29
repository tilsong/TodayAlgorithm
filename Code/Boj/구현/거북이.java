package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 거북이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            int direction = 0;

            int maxL = 0;
            int maxR = 0;
            int maxU = 0;
            int maxD = 0;

            int x = 0;
            int y = 0;

            for (int j = 0; j < command.length(); j++) {
                char c = command.charAt(j);

                if (c == 'F') {
                    x += dx[direction];
                    y += dy[direction];
                } else if (c == 'B') {
                    if (direction < 2) {
                        x += dx[direction+2];
                        y += dy[direction+2];
                    } else {
                        x += dx[direction-2];
                        y += dy[direction-2];
                    }
                } else if (c == 'L') {
                    if (direction == 0) {
                        direction = 3;
                    } else {
                        direction --;
                    }
                } else if (c == 'R') {
                    if (direction == 3) {
                        direction = 0;
                    } else {
                        direction ++;
                    }
                }

                maxL = Math.min(maxL, x);
                maxR = Math.max(maxR, x);
                maxU = Math.max(maxU, y);
                maxD = Math.min(maxD, y);
            }

            int w = Math.abs(maxR - maxL);
            int h = Math.abs(maxU - maxD);

            System.out.println(w*h);
        }
    }
    // direction      0  1 2  3
    //                상 우 하 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
}
