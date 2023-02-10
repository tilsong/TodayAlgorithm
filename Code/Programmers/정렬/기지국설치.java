package Programmers.정렬;

public class 기지국설치 {

    public int solution(int n, int[] stations, int w) {
        int answer =0;
        int stationPower = 2*w + 1;

        int start = 1;
        for (int i = 0; i < stations.length; i++) {
            int stationBegin = stations[i] - w;
            if (start < stationBegin) {
                int space = stationBegin - start;

                while (space > 0) {
                    space -= stationPower;
                    answer ++;
                }
            }
            start = stations[i] + w + 1;
        }

        // for 문 종료되었는데 남았을 경우
        if (start <= n) {
            int space = n - start;
            while (space >= 0) {
                space -= stationPower;
                answer ++;
            }
        }

        return answer;
    }
    // - 전파가 전달되야 하는 전체 구간이 1~N이므로
    // 이중 station의 전파가 닿지 않는 각 구간들을 파악하고,
    // 전파가 닿지 않는 각 구간에 몇 개의 기지국이 있어야 하는지를 위주로 문제를 풀었습니다.
    // - 시간 복잡도는 for문을 stations 크기 만큼 반복했기 때문에 O(N) 입니다.
    //   그런데 for문 중간의 while문은 최악의 경우(N=2억, stations의 크기=1, w=1) 2억번까지도 돌 수 있을 것 같네요..

}
