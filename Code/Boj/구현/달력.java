package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 70m (Fail -> Success)
// 입력: 첫째 줄에 일정의 개수 N이 주어진다. (1 ≤ N ≤ 1000)
//      둘째 줄부터 일정의 개수만큼 시작 날짜 S와 종료 날짜 E가 주어진다. (1 ≤ S ≤ E ≤ 365)
// 출력: 코팅지의 면적을 출력한다.
// 풀이 방향: 휴.. 코드 100줄 쓰다가 아닌 거 같애서 다시 풀었다. 문제의 핵심을 알면, 문제는 풀리게 되어 있다. 나는 모르겠다ㅠ
// 시간 복잡도: O(N)

public class 달력 {
    public static void main(String[] args) throws IOException {
        int[] year = new int[366];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            for (int j = start; j <= end; j++) {
                year[j] ++;
            }
        }

        int total = 0;
        int maxH = 0;
        int l = 0;
        for (int i = 1; i <= 365; i++) {
            if (year[i] == 0) {
                total += maxH * l;
                maxH = 0;
                l = 0;
            } else {
                maxH = Math.max(maxH, year[i]);
                l++;
            }
        }
        total += maxH * l;

        System.out.println(total);
    }


}