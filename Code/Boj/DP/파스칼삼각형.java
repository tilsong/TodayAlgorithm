package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파스칼삼각형 {
    // Input/Output Sample
    // 3 1 4
    // 42
    //
    // 20m (Success)
    // 입력: 첫째 줄에 양의 정수 R, C, W가 공백을 한 칸씩 두고 차례로 주어진다. (단, 2 ≤ R+W ≤ 30, 2 ≤ C+W ≤ 30, 1 ≤ W ≤ 29, C ≤ R)
    // 출력: 첫째 줄에 R번째 줄, C번째 수를 위 꼭짓점으로 하는 한 변이 포함하는 수의 개수가 W인 정삼각형과 그 내부에 있는 수들의 합을 출력한다.
    // 풀이 방향: 세로 30, 가로 30의 파스칼 삼각형을 만든다. 이 때, 삼각형의 정수는 [C][R] = [C-1][R-1] + [C-1][R] (C >= 2)
    //           를 만족한다.
    // 시간 복잡도: O(N^2), 최대 9000.


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] triangle = new int[31][31];

        // 삼각형 만들기
        for (int i = 1; i <= 30; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 | j == i) {
                    triangle[i][j] = 1;
                } else {
                    triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
                }
            }
        }

        int count = 0;
        for (int i = R; i <= R+W-1; i++) {
            for (int j = C; j <= C+i-R; j++) {
                count += triangle[i][j];
            }
        }

        System.out.println(count);
    }
}
