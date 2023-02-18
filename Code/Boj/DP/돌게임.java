package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 돌게임 {
    // Input/Output Sample
    // 5
    // CY
    //
    // 5m (Success)
    // 입력: 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 1000)
    // 출력: 상근이가 게임을 이기면 SK를, 창영이가 게임을 이기면 CY을 출력한다.
    // 풀이 방향: i번째 게임의 승자는 i-1번째 게임 승자가 아니다. 따라서 win[i] = !(win[i-1]) 이라는 점화식이 가능했다.
    //           이를 메모이제이션과 함께 사용함.
    // 시간 복잡도: O(N), 최대 1000.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        //
        boolean[] win = new boolean[n+1];
        win[1] = true;
        for (int i = 2; i <= n; i++) {
            win[i] = !win[i-1];
        }

        if (win[n]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
