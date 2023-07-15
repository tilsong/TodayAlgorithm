package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열돌리기17276 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (d >= 0) {
                d = (360-d) / 45 % 8;
            } else {
                 d = (d*-1) /45 % 8;
            }

            while (d-- > 0) {
                for (int i = 0; i < n / 2; i++) {
                    int start = i;
                    int end = n-i-1;
                    int mid = (start + end) / 2;

                    int temp = arr[start][start];
                    arr[start][start] = arr[start][mid];
                    arr[start][mid] = arr[start][end];
                    arr[start][end] = arr[mid][end];
                    arr[mid][end] = arr[end][end];
                    arr[end][end] = arr[end][mid];
                    arr[end][mid] = arr[end][start];
                    arr[end][start] = arr[mid][start];
                    arr[mid][start] = temp;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
