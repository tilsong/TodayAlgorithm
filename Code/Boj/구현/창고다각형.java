package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 창고다각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[1001];
        int max = 0;
        int highIdx = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[l] = h;

            if (max < h) {
                max = h;
                highIdx = l;
            }

            end = Math.max(l, end);
        }
        int total = 0;
        int before = 0;
        for (int i = 0; i < highIdx; i++) {
            int cur = arr[i];

            if (before >= cur) {
                total += before;
            } else {
                total += cur;
                before = cur;
            }
        }

        before = 0;
        for (int i = end; highIdx < i; i--) {
            int cur = arr[i];

            if (before >= cur) {
                total += before;
            } else {
                total += cur;
                before = cur;
            }
        }

        System.out.println(total+max);
    }
}