package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 20m (Success)
// 입력: 첫째 줄에 첫 줄에 사건의 개수 n(400 이하의 자연수)과 알고 있는 사건의 전후 관계의 개수 k(50,000 이하의 자연수)가 주어진다.
//      다음 k줄에는 전후 관계를 알고 있는 두 사건의 번호가 주어진다. 이는 앞에 있는 번호의 사건이 뒤에 있는 번호의 사건보다 먼저 일어났음을 의미한다.
//      물론 사건의 전후 관계가 모순인 경우는 없다. 다음에는 사건의 전후 관계를 알고 싶은 사건 쌍의 수 s(50,000 이하의 자연수)이 주어진다.
//      다음 s줄에는 각각 서로 다른 두 사건의 번호가 주어진다. 사건의 번호는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
// 출력: s줄에 걸쳐 물음에 답한다. 각 줄에 만일 앞에 있는 번호의 사건이 먼저 일어났으면 -1, 뒤에 있는 번호의 사건이 먼저 일어났으면 1, 어떤지 모르면(유추할 수 없으면) 0을 출력한다.
// 풀이 방향: 플로이드 워셜 실행. 단방향 관계임을 고려하여, 관계를 추가 설정해준다.
// 시간 복잡도: O(E^3) => 약 64백만

public class 역사 {
    static int n, m;
    static int INF = (int) 1e9;
    static int[][] matrix = new int[401][401];

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

            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            matrix[front][back] = -1;
            matrix[back][front] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (matrix[a][i] == -1 && matrix[i][b] == -1) {
                        matrix[a][b] = -1;
                    } else if (matrix[a][i] == 1 && matrix[i][b] == 1) {
                        matrix[a][b] = 1;
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int iter = Integer.parseInt(st.nextToken());
        for (int i = 0; i < iter; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(matrix[a][b]);
        }
    }
}
