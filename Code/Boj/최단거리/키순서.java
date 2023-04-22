package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 25m (Success)
// 입력: 첫째 줄에 학생들의 수 N (2 ≤ N ≤ 500)과 두 학생 키를 비교한 횟수 M (0 ≤ M ≤ N(N-1)/2)이 주어진다.
//      다음 M개의 각 줄에는 두 학생의 키를 비교한 결과를 나타내는 두 양의 정수 a와 b가 주어진다. 이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다.
// 출력: 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다.
// 풀이 방향: 플로이드 워셜 실행. 이후 각 학생 마다 다른 학생과의 관계를 모두 가지고 있는지 확인하고, 모두 있으면 체크하여 계수한다.
// 시간 복잡도: O(E^3) => 약 1.2억

public class 키순서 {
    static int n, m;
    static int[][] matrix = new int[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            Arrays.fill(matrix[i], 0);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            matrix[a][b] = -1; // a < b
            matrix[b][a] = 1;  // b > a
        }


        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (matrix[a][i] == 1 && matrix[i][b] == 1) {
                        matrix[a][b] = 1;
                    } else if (matrix[a][i] == -1 && matrix[i][b] == -1) {
                        matrix[a][b] = -1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= n ; i++) {
            boolean rel = true;
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j] == 0 && i != j) {
                    rel = false;
                    break;
                }
            }
            if (rel) result ++;
        }

        System.out.println(result);
    }
}
