package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그리디 문제였다. 단순 구현 문제와의 차이점은, 각 반복 로직마다 최선의 결과를 도출하는 로직으로 작성해야 하므로
// 최소한의 반복을 해야 답으로 다가선 것이라고 볼 수 있다. 그러나 첫 로직은 반복으로 해결하려고 했던 것 같다. 어렵다..
// 문제 접근 자체가 어려웠던 것 같다.
public class 한줄로서기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        for (int i = 1; i <= n; i++) { // 배치할 번호
            int front = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= n; j++) {
                if (front == 0 && arr[j] == 0) {
                    arr[j] = i;
                    break;
                } else if (arr[j] == 0) {
                    front--;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
