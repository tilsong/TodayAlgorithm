package Boj.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 오리 {

    // 50m (Success)
    // 입력: 첫째 줄에 영선이가 녹음한 소리가 주어진다. 소리의 길이는 5보다 크거나 같고, 2500보다 작거나 같은 자연수이고, 'q','u','a','c','k'로만 이루어져 있다.
    // 출력: 영선이 방에 있을 수 있는 오리의 최소 수를 구하는 프로그램을 작성하시오. 녹음한 소리가 올바르지 않은 경우에는 -1을 출력한다.
    // 풀이 방향: 확실히 내가 구현 문제에 약하다는 것을 알게 되었다.
    // 시간 복잡도: O(N^2 / 5) = N(문자열의 개수) * 오리의 최대 마리 수(N/5)
    static ArrayList<Integer> ducks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String sound = st.nextToken();

        ducks = new ArrayList<>();

        for (int i = 0; i < sound.length(); i++) {
            char c = sound.charAt(i);

            if (c == 'q') {
                if (!insert(0)) {
                    ducks.add(1); // 추가
                }
            } else if (c == 'u') {
                if (!insert(1)) {
                    System.out.println(-1);
                    return;
                }
            } else if (c == 'a') {
                if (!insert(2)) {
                    System.out.println(-1);
                    return;
                }
            } else if (c == 'c') {
                if (!insert(3)) {
                    System.out.println(-1);
                    return;
                }
            } else if (c == 'k') {
                if (!insert(4)) {
                    System.out.println(-1);
                    return;
                }
            }


        }

        for (int j = 0; j < ducks.size(); j++) {
            if((ducks.get(j) % 5) != 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(ducks.size());
    }
    static boolean insert(int seq) {
        for (int i = 0; i < ducks.size(); i++) {
            if (ducks.get(i)%5 == seq) {
                ducks.set(i, seq+1); // 기존 오리 활용
                return true;
            }
        }
        return false;
    }
}
