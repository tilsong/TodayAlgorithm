package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물 {
    static int[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        blocks = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int allRain = 0;

        for (int i = 1; i < w; i++) { // i == current
            int leftMax = 0;
            int rightMax = 0;

            for (int j = 0; j <= i-1; j++) {
                leftMax = Math.max(leftMax, blocks[j]);
            }
            for (int j = i+1; j < w; j++) {
                rightMax = Math.max(rightMax, blocks[j]);
            }

            if (leftMax > blocks[i] && rightMax > blocks[i]) {
                allRain += (Math.min(leftMax, rightMax) -blocks[i]);
            }
        }

        System.out.println(allRain);
    }
}
