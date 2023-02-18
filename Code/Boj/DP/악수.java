package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 악수 {
    // Input/Output Sample
    // 4
    // 5
    //
    // 20m (Success)
    // 입력: 첫째 줄에 회의에 참석한 사람의 수 n (1 ≤ n ≤ 10,000,000)이 주어진다.
    // 출력: 첫째 줄에 악수를 하는 방법의 수를 출력한다. 수가 매우 커질 수 있기 때문에, 마지막 자리만 출력한다.
    // 풀이 방향: 직접 그려 보고, 규칙을 적용했다. 마지막 자리만 출력하는 조건을 충족시켰다.
    // 시간 복잡도: O(N)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        if (n == 1 | n == 2) {
            System.out.println(n);
            return;
        }

        int[] handShake = new int[n+1];
        handShake[1] = 1;
        handShake[2] = 2;
        for (int i = 3; i <= n; i++) {
            handShake[i] = handShake[i-1] + handShake[i-2];
            if (handShake[i] > 10) {
               handShake[i] -= 10;
            }
        }

        System.out.println(handShake[n]);
    }
}
