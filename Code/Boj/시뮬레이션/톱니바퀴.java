package Boj.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 개선된 코드
// 참고: https://dding9code.tistory.com/4
//- 개선 방향
//        - 전체 코드량의 감소
//          - 기존 WheelDirection를 제거
//          - 각 바퀴를 돌리는 시간복잡도를 포함해도 양호하기 때문에 바퀴 돌리기를 실제로 수행시킴
//        - 재귀 함수 제거
//          - 기존에는 재귀로 탐색하여 돌려야 하는 바퀴의 목록을 구했는데, 단순히 for문을 통해 이를 해결했다..ㅎ

public class 톱니바퀴 {
    static int[][] wheels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        wheels = new int[5][8];
        StringTokenizer st;
        for (int i = 1; i <= 4; i++) {
            String [] list = br.readLine().split("");

            for (int j = 0; j < 8; j++) {
                wheels[i][j] = Integer.parseInt(list[j]);
            }
        }

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int[] command = new int[5];
            int num = Integer.parseInt(st.nextToken()); // 회전 바퀴 번호
            int dir = Integer.parseInt(st.nextToken()); // 바퀴 방향 ( 1 시계 , -1 반시계 )
            command[num] = dir;

            getCommands(command, num);

            for (int j = 0; j < command.length; j++) {
                if (command[j] != 0) {
                    rotate(j, command[j]==1);
                }
            }
        }

        int sum = 0;
        for (int i = 1; i <= 4; i++) {
            if (wheels[i][0] == 1) {
                sum += Math.pow(2, i-1);
            }
        }

        System.out.println(sum);
    }

    private static void getCommands(int[] command, int start) {
        //좌측 톱니 회전 방향 검사
        for(int i=start; i>1; i--) {
            if(wheels[i][6] != wheels[i-1][2]) {
                if (command[i] == 1) {
                    command[i-1] = -1;
                } else {
                    command[i-1] = 1;
                }
            }else {
                break;
            }
        }
        //우측 톱니 회전 방향 검사
        for(int i=start; i<4; i++) {
            if(wheels[i][2] != wheels[i+1][6]) {
                if (command[i] == 1) {
                    command[i+1] = -1;
                } else {
                    command[i+1] = 1;
                }
            }else {
                break;
            }
        }
    }

    private static void rotate(int num, boolean clockD) {
        if (clockD) {
            int temp = wheels[num][7];
            for (int i = 7; i > 1; i--) {
                wheels[num][i] = wheels[num][i-1];
            }
            wheels[num][0] = temp;
        } else {
            int temp= wheels[num][0];
            for (int i = 0; i < 6; i++) {
                wheels[num][i] = wheels[num][i+1];
            }
            wheels[num][7] = temp;
        }
    }
}

// 120m(Success)
//- 풀이
//        - 각 회전 요청 시 마다 회전해야 하는 바퀴 목록을 구하고, 이들을 회전시켰다.
//        - 각 바퀴의 상태를 저장하는 배열을 만들어서 사용했고, 각 바퀴의 top, left, right를 저장하는 클래스를 만들어서 사용했다.
//        - 사용한 스킬 2가지
//        1. index 초과, 미만 처리 로직
//        - 각 바퀴의 top, left, right를 회전시켰을 때, 이를 처리해줄 수 있도록 식을 만들었다.
//        - 마법사 상어와 비바라기 문제에서 배웠던 것을 활용한 것이다.
//        2. 회전해야 하는 바퀴 목록을 찾을 때, 재귀를 사용했다.
//        - 아쉬웠던 점
//        - 회전해야 하는 바퀴 목록을 찾는 로직에서 if문이 매우 많았음
//        - 전체적인 코드 줄이 매우 김..
//        - 시간이 많이 들었던 이유
//        - 처음에는 목록을 찾지 않고, 재귀 형태로 바퀴를 회전시키면서 추가적인 회전해야 하는 바퀴를 찾는 로직을 작성했다.
//           그러나 그렇게 할 경우 이전 바퀴와 상태가 달라지므로 필요한 바퀴들을 모두 회전시킬 수 없다.
//           이 포인트를 잡느라 생각보다 시간이 들었다.
//
//        - 재귀를 사용할 때, 상태가 변하면서 재귀 로직에 영향을 주는지를 주의해야 하겠다.
//
//public class 톱니바퀴 {
//    static WheelDirection[] directions;
//    static int[][] wheels;
//    static List<int[]> commandList;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        wheels = new int[5][8];
//        directions = new WheelDirection[5];
//        StringTokenizer st;
//        for (int i = 1; i <= 4; i++) {
//            String [] list = br.readLine().split("");
//
//            for (int j = 0; j < 8; j++) {
//                wheels[i][j] = Integer.parseInt(list[j]);
//            }
//            directions[i] = new WheelDirection();
//            directions[i].top = 0;
//            directions[i].right = 2;
//            directions[i].left = 6;
//        }
//
//        st = new StringTokenizer(br.readLine());
//        int t = Integer.parseInt(st.nextToken());
//        for (int i = 0; i < t; i++) {
//            st = new StringTokenizer(br.readLine());
//            int[] command = new int[2];
//            command[0] = Integer.parseInt(st.nextToken()); // 회전 바퀴 번호
//            command[1] = Integer.parseInt(st.nextToken()); // 바퀴 방향 ( 1 시계 , -1 반시계 )
//
//            commandList = new ArrayList<>(); // 초기화
//            commandList.add(command);
//
//            getCommands(command[0], command[1] == 1,0);
//
//            for (int j = 0; j < commandList.size(); j++) {
//                rotate(commandList.get(j)[0], commandList.get(j)[1] == 1);
//            }
//        }
//
//        int sum = 0;
//        for (int i = 1; i <= 4; i++) {
//            if (wheels[i][directions[i].top] == 1) {
//                sum += Math.pow(2, i-1);
//            }
//        }
//
//        System.out.println(sum);
//    }
//
//    private static void getCommands(int num, boolean clockD, int tranD) { //tranD 0 - 왼, 오 | 1 - 왼 | 2 - 오
//        if (tranD == 0) {
//           if (num > 1) {
//                if (wheels[num][directions[num].left] != wheels[num-1][directions[num -1].right]) {
//                    if (clockD) {
//                        commandList.add(new int[]{num-1, -1});
//                        getCommands(num-1, false, 1);
//                    } else {
//                        commandList.add(new int[]{num-1, 1});
//                        getCommands(num-1, true, 1);
//                    }
//                }
//           }
//           if (num < 4) {
//               if (wheels[num][directions[num].right] != wheels[num+1][directions[num + 1].left]) {
//                   if (clockD) {
//                       commandList.add(new int[]{num+1, -1});
//                       getCommands(num+1, false, 2);
//                   } else {
//                       commandList.add(new int[]{num+1, 1});
//                       getCommands(num+1, true, 2);
//                   }
//               }
//           }
//        } else if (tranD == 1 && num > 1) {
//            if (wheels[num][directions[num].left] != wheels[num-1][directions[num -1].right]) { // num = 2
//                if (clockD) {
//                    commandList.add(new int[]{num-1, -1});
//                    getCommands(num-1, false, 1);
//                } else {
//                    commandList.add(new int[]{num-1, 1});
//                    getCommands(num-1, true, 1);
//                }
//            }
//        } else if (tranD == 2 && num < 4) {
//            if (wheels[num][directions[num].right] != wheels[num+1][directions[num + 1].left]) {
//                if (clockD) {
//                    commandList.add(new int[]{num+1, -1});
//                    getCommands(num+1, false, 2);
//                } else {
//                    commandList.add(new int[]{num+1, 1});
//                    getCommands(num+1, true, 2);
//                }
//            }
//        }
//    }
//
//    private static void rotate(int num, boolean clockD) { //tranD 0 - 왼, 오 | 1 - 왼 | 2 - 오
//        if (clockD) {
//            directions[num].clockRotate();
//        } else {
//            directions[num].reverseClockRotate();
//        }
//    }
//
//    private static class WheelDirection {
//        int top;
//        int left;
//        int right;
//
//        public void clockRotate() {
//            this.top = ((this.top - 1) + 8) % 8;
//            this.left = ((this.left - 1) + 8) % 8;
//            this.right = ((this.right - 1) + 8) % 8;
//        }
//
//        public void reverseClockRotate() {
//            this.top = ((this.top + 1) + 8) % 8;
//            this.left = ((this.left + 1) + 8) % 8;
//            this.right = ((this.right + 1) + 8) % 8;
//        }
//    }
//}