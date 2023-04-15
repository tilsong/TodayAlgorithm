package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 70m (Fail -> Success)
// 입력: 첫 줄에는 물건의 개수 N 이 주어지고, 둘째 줄에는 미리 측정된 물건 쌍의 개수 M이 주어진다. 단, 5 ≤ N ≤ 100 이고, 0 ≤ M ≤ 2,000이다.
//      다음 M개의 줄에 미리 측정된 비교 결과가 한 줄에 하나씩 주어진다.
//      각 줄에는 측정된 물건 번호를 나타내는 두 개의 정수가 공백을 사이에 두고 주어지며, 앞의 물건이 뒤의 물건보다 더 무겁다.
// 출력: 여러분은 N개의 줄에 결과를 출력해야 한다. i 번째 줄에는 물건 i 와 비교 결과를 알 수 없는 물건의 개수를 출력한다.
// 풀이 방향: 단방향 그래프를 문제에 맞게 풀어나갈 수 있는 능력이 필요했다.
// 시간 복잡도: O(N^3) => 백만.


public class 저울 {
    static final int INF = (int) 1e9;
    static int n;
    static int m;
    static int[][] compare;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        compare = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(compare[i], INF);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            compare[a][b] = 1;
            compare[b][a] = 2;
        }

        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (compare[a][k] == 1 && compare[k][b] == 1) {
                        compare[a][b] = 1;
                    }
                    if (compare[a][k] == 2 && compare[k][b] == 2) {
                        compare[a][b] = 2;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (i != j && compare[i][j] == INF) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
