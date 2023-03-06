package Boj.미분류;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 문자열게임2 {
    // 30m (Success)
    // 입력: 문자열 게임의 수 T가 주어진다. (1 ≤ T ≤ 100) 다음 줄부터 2개의 줄 동안 문자열 W와 정수 K가 주어진다. (1 ≤ K ≤ |W| ≤ 10,000)
    // 출력: T개의 줄 동안 문자열 게임의 3번과 4번에서 구한 연속 문자열의 길이를 공백을 사이에 두고 출력한다. 만약 만족하는 연속 문자열이 없을 시 -1을 출력한다.
    // 풀이 방향: 완전 탐색이나 다름 없었으나 시간 복잡도가 충분했기 때문에 이 방법을 사용했다.
    //           핵심은 큐를 사용하는 것인데, 음.. 그냥 인덱스 값만 기억하고 있었어도 되었을 것 같다.
    // 시간 복잡도: O(T * W * 26) = 100 * 10000 * 26, 26백만

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            String w = st.nextToken();
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int max = 0;
            int min = 123456789;
            for (char j = 'a'; j <= 'z'; j++) {
                Queue<Integer> q = new LinkedList<>();
                for (int l = 0; l < w.length(); l++) {
                    if (w.charAt(l) == j) {
                        q.offer(l);
                        if (q.size() == k) {
                            int v = l - q.poll();
                            max = Math.max(max, v+1);
                            min = Math.min(min, v+1);
                        }
                    }
                }
            }

            if (max == 0 | min == 123456789) {
                System.out.println(-1);
            } else {
                System.out.println(min + " " + max);
            }
        }

    }
}
