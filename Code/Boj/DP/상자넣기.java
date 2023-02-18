package Boj.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 상자넣기 {

    // Input/Output Sample
    // 8
    // 1 6 2 5 7 3 5 6
    //
    // 5
    //
    // 60m (Success)
    // 입력: 파일의 첫 번째 줄은 상자의 개수 n (1 ≤ n ≤ 1000)을 나타낸다. 두 번째 줄에는 각 상자의 크기가 순서대로 주어진다. 상자의 크기는 1,000을 넘지 않는 자연수이다.
    // 출력: 첫째 줄에 한 줄에 넣을 수 있는 최대의 상자 개수를 출력한다.
    // 풀이 방향: 생각보다 여러 조건을 만족시키느라 시간이 많이 갔다.
    // 현재 상자 크기 보다 작은 이전 상자 이면서, 최대의 dp 값을 가지고 있는 상자를 찾아, 해당 dp 값에 +1을 한 값을 현재 상자 count 배열에 넣는다.
    // 그리고 최대 상자 개수를 계속 업데이트한다.
    // 시간 복잡도: O(N^2),N=1000, 백만.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] boxes = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.println(1);
            return;
        }

        int[] count = new int[n+1];
        Arrays.fill(count, 1);
        int max = 1;

        for (int i = 2; i <= n; i++) {
            int curMax = 0;

            for (int j = i - 1; j >= 1; j--) {
                if (boxes[j] < boxes[i] && count[curMax] <= count[j]) { // + 그중에서는 가장 커야 함.
                    curMax = j;
                }
            }
            if (curMax > 0) {
                count[i] += count[curMax];
            }

            if (max < count[i]) {
                max = count[i];
            }
        }

        System.out.println(max);
    }
}
