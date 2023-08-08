package Boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//
//- 처음에는 각 팀의 인원을 구하고, arr로부터 팀원들의 시너지를 모두 합하는 식으로 로직을 진행했다.
//  그러나 코드도 너무 길어졌고, 결국 타임 초과 에러가 떴다.
//
// - 유일한 조합을 어떻게 생성할 것인가에 대한 문제이다.
//   - 블로그를 통해 본 내용은, 재귀 함수를 사용할 때, 이전 값+1을 for문의 시작점으로 전달해주면 해결할 수 있었다.
//   - 또한 조합의 요소가 여러 개일 때, 각 조합에 대한 값들을 모두 더하는 방법도 매우 간단하면서 좋았다.
//     - 이중 for문을 쓰면서 이 역시 유일한 조합이 될 수 있도록 j = i+1 으로 설정하고 i와 j를 통해 유일한 조합을 구해냈다.
//     - 추가로, i,j 둘 다 true이면 start 팀, 둘 다 false이면 link 팀으로 설정해줌으로써 각각의 유일한 조합으로 이루어진 경우의 수를 모두 구할 수 있도록 한 점이 인상적이었다.

public class 스타트와링크 {
    static int n;
    static int[][] arr;
    static boolean[] visited;
    static int min = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        search(0,0);

        System.out.println(min);
    }

    private static void search(int depth, int start) {
        if (depth == n/2) {
            diff();
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            search(depth+1, i+1);
            visited[i] = false;
        }
    }

    private static void diff() {
        int startCount = 0;
        int linkCount = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i+1; j < n; j++) {
                if (visited[i] && visited[j]) {
                    startCount += (arr[i][j] + arr[j][i]);
                } else if (!visited[i] && !visited[j]) {
                    linkCount += (arr[i][j] + arr[j][i]);
                }
            }
        }

        int abs = Math.abs(startCount - linkCount);
        min = Math.min(min, abs);
    }
}
