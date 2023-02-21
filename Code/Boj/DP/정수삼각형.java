package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정수삼각형 {
// Bottom up 방식으로 문제를 풀었습니다. N을 1씩 늘려가며, 위 배열의 요소를 아래 배열의 요소에 누적합시켰습니다.
// 마지막 줄의 요소 중 최대의 값이 발생할 것이므로, 마지막 줄의 최댓값을 구했습니다.
    // 시간 복잡도는 삼각형의 요소만큼 소요되므로, 1+2+...+500(N은 500이하) -> O(약 12만)입니다.
    // 동시에 같은 크기의 공간 복잡도도 요구됩니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] triangle = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i <= n; i++) {
            triangle[i][1] += triangle[i-1][1];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 2; j <= i-1; j++) {
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, triangle[n][i]);
        }

        System.out.println(max);
    }
}
