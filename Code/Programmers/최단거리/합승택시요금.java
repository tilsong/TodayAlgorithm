package Programmers.최단거리;

import java.util.Arrays;

public class 합승택시요금 {
    // Input/Output Sample
    // 6	4	6	2	[[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]	82
    // 7	3	4	1	[[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]]	14
    // 6	4	5	6	[[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]]	18

    // 40m (Success)
    // 입력: 노드 개수(int n), 출발 노드(int s), a도착 노드(int a), b 도착 노드(int b)
    //      노드간 연결 정보(int[][] fares)
    // 출력: 최저 택시 요금(int result)
    // 풀이 방향: 총 노드의 수가 200이하이므로, 플루이드 워셜을 사용.
    //           (s -> i) + (i-> a) + (i -> b) 비교하여 최저 요금 도출하기 (i는 s, a, b일 수 있음)
    // 시간 복잡도: O(N^3)

    final int INF = (int) 1e9;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int [][] d = new int[201][201]; // 최단 거리 테이블

            final int INF = 10000000;

            // 최단 거리 테이블 초기화
            for (int i = 1; i <= n; i++) {
                Arrays.fill(d[i], INF); // 전체 무한 넣기
                d[i][i] = 0; // 자기 자신은 거리가 0
            }
            for (int i = 0; i < fares.length; i++) { // 간선 거리 넣기
                d[fares[i][0]][fares[i][1]] = fares[i][2];
                d[fares[i][1]][fares[i][0]] = fares[i][2];
            }

            // 플루이드 워셜. 모든 노드 간의 최단 거리
            for (int i = 1; i <= n ; i++) {
                for (int c = 1; c <= n; c++) {
                    for (int e = 1; e <= n; e++) {
                        // a(ab) = min(a(ab), a(ai)+a(ib)
                        if (d[c][e] > (d[c][i] + d[i][e])) {
                            d[c][e] = d[c][i] + d[i][e];
                        }
                    }
                }
            }

            int minFare =  10000000;
            for (int i = 1; i <= n; i++) {
                int fare = d[s][i] + d[i][a] + d[i][b];
                if (fare < minFare) minFare = fare;
            }

            return minFare;
    }
}
