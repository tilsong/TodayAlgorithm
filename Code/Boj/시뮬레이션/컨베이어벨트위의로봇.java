package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 풀이
// 처음에는 이중 배열을 통해 로봇 유무와 내구도를 한 번에 체크하려고 했다(클래스는 일부러 쓰지 않았다.)
// 그런데 로직이 복잡해지니까 풀어내질 못했다.
// 결국에는 블로그를 참고했는데, belt와 robot을 다른 자료구조로 나누어 생각하고 있었다.
// 이렇게 분리를 하는 게 좋을 것 같다. 클래스로 하면 더 간단했겠지만, 클래스를 쓰지 않는다면 이렇게 별개의 자료 구조를 쓰는 것이 좋은 방법이 될 수 있을 것 같다.

public class 컨베이어벨트위의로봇 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int beltCount = Integer.parseInt(st.nextToken())*2;
        int k = Integer.parseInt(st.nextToken());

        int[] belt = new int[beltCount];
        boolean[] robot = new boolean[beltCount/2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < beltCount; i++) {
            belt[i] = Integer.parseInt(st.nextToken()); // 0 로봇 유무, 1 내구도
        }

        int count = 0;

        while (true) {
            count ++;

//          1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            int last = belt[beltCount - 1]; // 마지막 칸 내용 저장
            for (int i = beltCount-1; i > 0; i--) {
                belt[i] = belt[i-1];
            }
            belt[0] = last;

            // 로봇 회전
            for (int i = robot.length -1; i > 0 ; i--) {
                robot[i] = robot[i-1];
            }
            robot[0] = false;

//          2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
//             로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.

            robot[robot.length - 1] = false;
            for (int i = robot.length-1; i > 0; i--) {
                if (robot[i-1] && !robot[i] && belt[i] != 0) {
                    robot[i] = true;
                    robot[i-1] = false;
                    belt[i] --;
                }
            } // n-1번째 칸의 로봇 하차


//          3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
            if (belt[0] != 0) {
                robot[0] = true;
                belt[0] --;
            }

//          4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
            int check = 0;
            for (int i = 0; i < belt.length; i++) {
                if (belt[i] == 0) {
                    check ++;
                }
            }
            if (check >= k) {
                break;
            }
        }

        System.out.println(count);
    }
}
