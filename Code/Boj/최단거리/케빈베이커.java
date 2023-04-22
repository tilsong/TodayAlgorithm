package Boj.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 15m (Success)
// 입력: 첫째 줄에 유저의 수 N (2 ≤ N ≤ 100)과 친구 관계의 수 M (1 ≤ M ≤ 5,000)이 주어진다.
//      둘째 줄부터 M개의 줄에는 친구 관계가 주어진다. 친구 관계는 A와 B로 이루어져 있으며, A와 B가 친구라는 뜻이다. A와 B가 친구이면, B와 A도 친구이며, A와 B가 같은 경우는 없다.
//      친구 관계는 중복되어 들어올 수도 있으며, 친구가 한 명도 없는 사람은 없다.
//      또, 모든 사람은 친구 관계로 연결되어져 있다. 사람의 번호는 1부터 N까지이며, 두 사람이 같은 번호를 갖는 경우는 없다.
// 출력: 첫째 줄에 BOJ의 유저 중에서 케빈 베이컨의 수가 가장 작은 사람을 출력한다. 그런 사람이 여러 명일 경우에는 번호가 가장 작은 사람을 출력한다.
// 풀이 방향: 플로이드 워셜 실행. 이후 각 행 마다의 총합 중 가장 작은 행의 번호를 출력한다.
// 시간 복잡도: O(E^3) => 약 10만

public class 케빈베이커 {
    static int INF = (int) 1e9;
    static int n, m;
    static int[][] matrix = new int[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            Arrays.fill(matrix[i], INF);
            matrix[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    matrix[a][b] = Math.min(matrix[a][b], matrix[a][i] + matrix[i][b]);
                }
            }
        }

        int minTotal = INF;
        int minNumber = -1;
        for (int i = 1; i <= n; i++) {
            int total = 0;
            for (int j = 1; j <= n; j++) {
                total += matrix[i][j];
            }
            if (total < minTotal) {
                minTotal = total;
                minNumber = i;
            }
        }

        System.out.println(minNumber);
    }
}
